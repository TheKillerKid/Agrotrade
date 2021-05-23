package Model.DBIF;

import java.sql.SQLException; 
import java.util.ArrayList;

import Model.Model.Employee;

public interface EmployeeIF {
	public Employee getEmployeeByCprNo(String cprNo) throws SQLException;
	
	public Employee getEmployeeByEmail(String email) throws SQLException;
	
	public Employee getEmployeeById(long id) throws SQLException;
	
	public Employee createEmployee(Employee employee) throws SQLException;
	
	public int updateEmployee(Employee employee) throws SQLException;
	
	public void deleteEmployee(String cprNo) throws SQLException;
	
	public ArrayList<Employee> getEmployeeList() throws SQLException;

}
