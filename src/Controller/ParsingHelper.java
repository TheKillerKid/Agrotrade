package Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
    
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) throws NullPointerException{
    	try {
    		if(dateToConvert != null) {
	            return dateToConvert.toInstant()
	              .atZone(ZoneId.systemDefault())
	              .toLocalDate();
    		}
    		else {
    			return null;
    		}
    	} catch(NullPointerException e) {
    		throw e;
    	}
    	
    }
}
