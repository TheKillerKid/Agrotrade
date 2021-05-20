package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.SupplierDB;
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
}
