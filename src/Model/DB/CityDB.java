package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.DBIF.CityIF;
import Model.Model.MessagesEnum;

public class CityDB implements CityIF {
	private CountryDB countryDb = new CountryDB();
	
	private long getCity(String postCode, String name) throws SQLException {
		long id = -1;

		String sqlCountry = ("SELECT * FROM City WHERE name = ? AND post_code = ?" );
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCountry);
			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, postCode);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getLong("id");
			}

		} catch (SQLException e) {
			throw e;
		}
		
		return id;
	}

	@Override
	public long createCity(String postCode, String name, String countryName) throws SQLException {
		long id = getCity(postCode, name);
		if(id != -1) {
			return id;
		}
		
		long countryId = countryDb.createCountry(countryName);
		
		String sqlCity = ("INSERT INTO City (post_code, name, country_id) VALUES (?,?,?)" );
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCity, Statement.RETURN_GENERATED_KEYS);
			
			preparedStmt.setString(1, postCode);
			preparedStmt.setString(2, name);
			preparedStmt.setLong(3, countryId);
			
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

}
