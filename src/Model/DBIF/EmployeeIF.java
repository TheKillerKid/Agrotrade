package Model.DBIF;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Employee;

public interface EmployeeIF {
	public Employee getEmployee(long cprNo) throws SQLException;
	
	public Employee getEmployee(String email) throws SQLException;
	
	public long createEmployee(Employee employee) throws SQLException;
	
	public int updateEmployee(Employee employee) throws SQLException;
	
	public void deleteEmployee(long cprNo) throws SQLException;
	
	public ArrayList<Employee> getEmployeeList(long warehouseId) throws SQLException;

}
