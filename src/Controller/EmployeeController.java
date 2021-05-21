package Controller;

import java.security.SecureRandom ;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.EmployeeDB;
import Model.Model.Employee;

public class EmployeeController {
	
	private EmployeeDB employeeDb = new EmployeeDB();
	
	public Employee getEmployee(long cprNo) throws SQLException{
		try {
			return employeeDb.getEmployee(cprNo);
		} catch (SQLException e) {
			throw e;
		}	
	}
	
	public Employee getEmployee(String email) throws SQLException {
		try {
			return employeeDb.getEmployee(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long createEmployee(Employee employee) throws SQLException {
		try {
			return employeeDb.createEmployee(employee);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	//update, delete and build object
	
	public String generateRandomPassword() {
		int len = 10;
        int randNumOrigin = 48;
		int randNumBound = 122; 	
		
		SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
	
	public ArrayList<Employee> getEmployees() throws SQLException {
		try {
			return employeeDb.getEmployeeList();
		} catch (SQLException e) {
			throw e;
		}
	}
}
