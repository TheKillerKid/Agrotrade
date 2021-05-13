package Model.DB;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DBIF.EmployeeIF;
import Model.Model.Address;
import Model.Model.Employee;

public class EmployeeDB implements EmployeeIF{

	@Override
	public Employee getEmployee(long cprNo) throws SQLException {
		Employee res = null;
		String sqlEmployee = String.format("SELECT * FROM Employee WHERE cprNo = '%s'", cprNo);
		System.out.println(sqlEmployee);
		
		try(Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			ResultSet rsEmployee = s.executeQuery(sqlEmployee);
			
			if(rsEmployee.next()) {
				Address address = AddressDB.getAddress(rsEmployee.getLong("address_id"));
				res = buildEmployee(rsEmployee, address);
			}

		} catch (SQLException e) {
			throw e;
		}
		return res;
	}

	@Override
	public Employee getEmployee(String email) throws SQLException {
		Employee res = null;
		String sqlEmployee = String.format("SELECT * FROM Employee WHERE email = '%s'", email);
		System.out.println(sqlEmployee);
		
		try(Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			ResultSet rsEmployee = s.executeQuery(sqlEmployee);
			
			if(rsEmployee.next()) {
				Address address = AddressDB.getAddress(rsEmployee.getLong("address_id"));
				res = buildEmployee(rsEmployee, address);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}

	@Override
	public long createEmployee(Employee employee) throws SQLException {
		
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


	private Employee buildEmployee(ResultSet rs, Address address) throws SQLException {
		return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), address, rs.getString("phone"), rs.getString("email"), null, rs.getLong("cprNo"), rs.getString("department"), rs.getString("position"), null);
	}
}

