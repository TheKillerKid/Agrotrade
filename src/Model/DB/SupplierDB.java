package Model.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DBIF.SupplierIF;
import Model.Model.Supplier;

public class SupplierDB implements SupplierIF{

	@Override
	public Supplier getSupplier(long cvrNo) throws SQLException {
		Supplier res = null;
		String sqlSupplier = String.format("SELECT * FROM Supplier WHERE barcode = '%s'");
		
		try (Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			ResultSet rsSupplier = s.executeQuery(sqlSupplier);
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
		
		String sqlCreate = "INSERT INTO Product (barcode, name) VALUES (?,?)";
		
		long rowID = 0;
		//, String last_name, int address_id, String phone, String email, String cvr_no, String company_name
		String firstName = supplier.getFirstName();
		String lastName = supplier.getLastName();
		//int addressID = supplier.getAddress();
		String phone = supplier.getPhone();
		String email = supplier.getEmail();
		long cvrNo = supplier.getCvrNo();
		String companyName = "1";
		
		return rowID;
	}

	@Override
	public void updateSupplier(Supplier supplier) throws SQLException {
		// TODO Auto-generated method stub
		
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
