package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Address;

public interface AddressIF {
	Address getAddress(long id) throws SQLException;
	
	long createAddress(Address address) throws SQLException;
}
