package Controller;

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

}
