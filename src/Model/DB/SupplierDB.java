package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DBIF.SupplierIF;
import Model.Model.Supplier;

public class SupplierDB implements SupplierIF{

	@Override
	public Supplier getSupplier(long cvrNo) throws SQLException {
		Supplier res = null;
		String sqlSupplier = "SELECT * FROM Supplier WHERE cvrNo = '?'";
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlSupplier);
			preparedStmt.setLong(1, cvrNo);
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
	public long createSupplier(Supplier supplier) throws SQLException {
		
		String sqlCreate = "INSERT INTO Product (barcode, name) VALUES (?,?,?,?,?,?,?)";
		
		long id = 0;
		
		String firstName = supplier.getFirstName();
		String lastName = supplier.getLastName();
		long addressId = supplier.getAddress().getId();
		String phone = supplier.getPhone();
		String email = supplier.getEmail();
		long cvrNo = supplier.getCvrNo();
		String companyName = supplier.getSupplierName();
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setLong(3, addressId);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setLong(6, cvrNo);
			preparedStmt.setString(7, companyName);
			
			id = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}

	@Override
	public void updateSupplier(Supplier supplier) throws SQLException {
		//Supplier oldSupplier = getSupplier(supplier.getCvrNo());
	}

	@Override
	public void deleteSupplier(long cvrNo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Supplier> getSupplierList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Supplier buildSupplier(ResultSet rsSupplier) throws SQLException{
		return null;
	}
}
