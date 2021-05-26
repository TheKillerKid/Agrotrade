package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.IF.CountryIF;
import Model.Model.MessagesEnum;

public class CountryDB implements CountryIF{
	
	private long getCountry(String name) throws SQLException {
		long id = -1;

		String sqlCountry = ("SELECT * FROM Country WHERE name = ?" );
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCountry);
			
			preparedStmt.setString(1, name);
			
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
	public long createCountry(String name) throws SQLException {
		long id = getCountry(name);
		if(id != -1) {
			return id;
		}
		
		String sqlCountry = ("INSERT INTO Country (name) VALUES (?)");
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCountry, Statement.RETURN_GENERATED_KEYS);
			
			preparedStmt.setString(1, name);
			
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
