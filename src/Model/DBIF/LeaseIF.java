package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Lease;

public interface LeaseIF {
	Lease createLease(Lease lease) throws Exception;
	
	Lease getLease(long id) throws SQLException;
}
