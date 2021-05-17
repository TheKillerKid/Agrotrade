package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Model.Address;

public class AddressDB {

	public Address getAddress(long id) throws SQLException {
		Address res = null;
		String sqlAddress = ("SELECT * FROM AddressView WHERE id = ?" );
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlAddress);
				
				preparedStmt.setLong(1, id);
				
				ResultSet rsAddress = preparedStmt.executeQuery();
			
			if(rsAddress.next()) {
				res = buildAddress(rsAddress);
			}

		} catch (SQLException e) {
			throw e;
		}
		return res;
	}
	
	private Address buildAddress(ResultSet rsAddress) throws SQLException {
		return new Address(rsAddress.getLong("id"), 
						   rsAddress.getString("street"), 
						   rsAddress.getString("street_no"), 
						   rsAddress.getString("city_name"),
						   rsAddress.getString("post_code"),
						   rsAddress.getString("country_name"));
	}
}
