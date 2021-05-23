package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.DBIF.AddressIF;
import Model.Model.Address;
import Model.Model.MessagesEnum;

public class AddressDB implements AddressIF{
	private CityDB cityDb = new CityDB();

	public Address getAddress(long id) throws SQLException {
		Address res = null;

		String sqlAddress = ("SELECT * FROM AddressView WHERE id = ?" );
		Connection con = DBConnection.getInstance().getConnection();

		try {
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
	
	public long getAddressId(String street, String streetNo, long cityId) throws SQLException {
		long id = -1;

		String sqlAddress = ("SELECT id FROM Address WHERE street = ? AND street_no = ? AND city_id = ?" );
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlAddress);
			
			preparedStmt.setString(1, street);
			preparedStmt.setString(2, streetNo);
			preparedStmt.setLong(3, cityId);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getLong("id");
			}

		} catch (SQLException e) {
			throw e;
		}
		
		return id;
	}
	
	public long createAddress(Address address) throws SQLException {
		long cityId = cityDb.createCity(address.getPostalCode(), address.getCity(), address.getCountry());
		
		long id = getAddressId(address.getStreet(), address.getStreetNo(), cityId);
		if(id != -1) {
			return id;
		}
		String sqlAddress = ("INSERT INTO Address (street, street_no, city_id) VALUES (?,?,?)");
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlAddress, Statement.RETURN_GENERATED_KEYS);
			
			preparedStmt.setString(1, address.getStreet());
			preparedStmt.setString(2, address.getStreetNo());
			preparedStmt.setLong(3, cityId);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }

		} catch (SQLException e) {
			throw e;
		}
		
		return id;
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
