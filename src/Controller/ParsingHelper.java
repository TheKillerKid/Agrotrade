package Controller;

public class ParsingHelper {

	public static Integer tryParseInt(String value) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public static Float tryParseFloat(String value) throws NumberFormatException {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
    
    public static Long tryParseLong(String value) throws NumberFormatException {
    	try {
    		return Long.parseLong(value);
    	} catch (NumberFormatException e) {
    		throw e;
    	}
    }
    
    public static Double tryParseDouble(String value) throws NumberFormatException {
    	try {
    		return Double.parseDouble(value);
    	} catch (NumberFormatException e) {
    		throw e;
    	}
    }
}
