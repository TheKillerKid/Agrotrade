package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DBIF.SupplierIF;
import Model.Model.Address;
import Model.Model.MessagesEnum;
import Model.Model.Supplier;

public class SupplierDB implements SupplierIF{
	
private AddressDB addressDb =  new AddressDB();

	@Override
	public Supplier getSupplierByCVRNumber(String cvrNo) throws SQLException {
		Supplier res = null;
		String sqlSupplier = "SELECT * FROM Supplier WHERE cvrNo = ?";
		
     Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlSupplier);
			preparedStmt.setString(1, cvrNo);
			ResultSet rsSupplier = preparedStmt.executeQuery();
			if (rsSupplier.next()) {
				res = buildSupplier(rsSupplier);
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}
	
	@Override
	public Supplier getSupplierById(long id) throws SQLException {
		Supplier res = null;
		String sqlSupplier = "SELECT * FROM Supplier WHERE id = ?";
		
     Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlSupplier);
			preparedStmt.setLong(1, id);
			ResultSet rsSupplier = preparedStmt.executeQuery();
			if (rsSupplier.next()) {
				res = buildSupplier(rsSupplier);
				
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}
	
	@Override
	public Supplier createSupplier(Supplier supplier) throws SQLException {
		
		String sqlCreate = "INSERT INTO Supplier (first_name, last_name, address_id, phone, email, cvr_no, company_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		String firstName = supplier.getFirstName();
		String lastName = supplier.getLastName();
		supplier.getAddress().setId(addressDb.createAddress(supplier.getAddress()));
		Address address = supplier.getAddress();
		String phone = supplier.getPhone();
		String email = supplier.getEmail();
		String cvrNo = supplier.getCvrNo();
		String companyName = supplier.getSupplierName();
		
		Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setLong(3, address.getId());
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, cvrNo);
			preparedStmt.setString(7, companyName);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()) {
                supplier.setId(rs.getLong(1));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch (SQLException e) {
			throw e;
		}
		return supplier;
	}

	@Override
	public void updateSupplier(Supplier supplier) throws SQLException {
		//Supplier oldSupplier = getSupplier(supplier.getCvrNo());
	}

	@Override
	public void deleteSupplier(String cvrNo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Supplier> getSupplierList() throws SQLException {
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		String sqlSupplier = ("SELECT * FROM Supplier");

		Connection con = DBConnection.getInstance().getConnection();

    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlSupplier);

			ResultSet rsSupplier = preparedStmt.executeQuery();
			
			while(rsSupplier.next()) {
				Supplier res = buildSupplier(rsSupplier);
				res.setAddress(addressDb.getAddress(rsSupplier.getLong("address_id")));
				suppliers.add(res);
			}

		} catch (SQLException e) {
			throw e;
		}

		return suppliers;
	}

	public Supplier buildSupplier(ResultSet rsSupplier) throws SQLException{
		return new Supplier(rsSupplier.getLong("id"),
							rsSupplier.getString("first_name"),
							rsSupplier.getString("last_name"),
							null,
							rsSupplier.getString("phone"),
							rsSupplier.getString("email"),
							rsSupplier.getString("cvr_no"),
							rsSupplier.getString("company_name"));
	}
}
