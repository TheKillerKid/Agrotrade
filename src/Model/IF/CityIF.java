package Model.IF;

import java.sql.SQLException;

public interface CityIF {
	
	long createCity(String postCode, String name, String countryName) throws SQLException;

}
