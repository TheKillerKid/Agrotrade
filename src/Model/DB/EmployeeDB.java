package Model.DB;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DBIF.EmployeeIF;
import Model.Model.Employee;

public class EmployeeDB implements EmployeeIF{

	@Override
	public Employee getEmployee(long cprNo) throws SQLException {
		Employee res = null;
		String sqlEmployee = String.format("select * from Employee where cprNo = '%s'", cprNo);
		System.out.println(sqlEmployee);
		return res;
	}

	@Override
	public Employee getEmployee(String email) throws SQLException {
		Employee res = null;
		String sqlEmployee = String.format("select * from Employee where cprNo = '%s'", email);
		System.out.println(sqlEmployee);
		return res;
	}

	@Override
	public long createEmployee(Employee employee) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteEmployee(long cprNo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Employee> getEmployeeList(long warehouseId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	private Employee buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
