package Controller;

import java.sql.SQLException;

public class ParsingHelper {

	public static Integer tryParseInt(String value) throws SQLException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public static Float tryParseFloat(String value) throws SQLException {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
    
    public static Long tryParseLong(String value) throws SQLException {
    	try {
    		return Long.parseLong(value);
    	} catch (NumberFormatException e) {
    		throw e;
    	}
    }
    
    public static Double tryParseDouble(String value) throws SQLException {
    	try {
    		return Double.parseDouble(value);
    	} catch (NumberFormatException e) {
    		throw e;
    	}
    }
}
