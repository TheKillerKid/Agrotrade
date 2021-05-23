package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.SupplierDB;
import Model.Model.Customer;
import Model.Model.Supplier;

public class SupplierController {
	
	SupplierDB supplierDb = new SupplierDB();
	
	public ArrayList<Supplier> getSuppliers() throws SQLException {
		try {
			return supplierDb.getSupplierList();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Supplier getSupplierByCVRNumber(String cvrNo) throws SQLException{
		try {
			return supplierDb.getSupplierByCVRNumber(cvrNo);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Supplier getSupplierById(long id) throws SQLException{
		try {
			return supplierDb.getSupplierById(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Supplier createSupplier(Supplier supplier) throws SQLException{
		try {
			return supplierDb.createSupplier(supplier);
		} catch (SQLException e) {
			throw e;
		}
	}
}
