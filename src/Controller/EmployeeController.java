package Controller;

import java.security.SecureRandom;
import java.sql.SQLException;

import Model.DB.EmployeeDB;
import Model.Model.Employee;

public class EmployeeController {
	
	private EmployeeDB employeeDB = new EmployeeDB();
	
	public Employee getEmployee(long cprNo) throws SQLException{
		try {
			return employeeDB.getEmployee(cprNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;	
	}
	
	public Employee getEmployee(String email) throws SQLException {
		try {
			return employeeDB.getEmployee(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// needs create, update, delete and build object
	
	public String generateRandomPassword(int len, int randNumOrigin, int randNumBound) {
		 	SecureRandom random = new SecureRandom();
	        return random.ints(randNumOrigin, randNumBound + 1)
	                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
	                .limit(len)
	                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
	                        StringBuilder::append)
	                .toString();
	    }
	 
	public void printPassword(String[] args) {
	        int len = 10;
	        int randNumOrigin = 48, randNumBound = 122;
	 
	        System.out.println(generateRandomPassword(len, randNumOrigin, randNumBound));
	} 
}
