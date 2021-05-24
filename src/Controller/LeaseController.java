package Controller;

import java.sql.SQLException;

import Model.DB.LeaseDB;
import Model.Model.Lease;

public class LeaseController {
	private LeaseDB leaseDb = new LeaseDB();
	
	public Lease createLease(Lease lease) throws SQLException {
		try {
			return leaseDb.createLease(lease);
		} catch (SQLException e) {
			throw e;
		}
	}
}
