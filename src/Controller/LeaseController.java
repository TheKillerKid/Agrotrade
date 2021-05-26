package Controller;

import java.sql.SQLException;

import Model.DB.LeaseDB;
import Model.IF.LeaseIF;
import Model.Model.Lease;
import Model.Model.Sale;

public class LeaseController {
	private LeaseIF leaseDb = new LeaseDB();
	
	public Lease createLease(Lease lease) throws Exception {
		try {
			return leaseDb.createLease(lease);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void returnLease(Lease lease) throws Exception {
		try {
			leaseDb.returnLease(lease);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Lease getLease(long id) throws SQLException {
		try {
			return leaseDb.getLease(id);
		} catch (SQLException e) {
			throw e;
		}
	}
}
