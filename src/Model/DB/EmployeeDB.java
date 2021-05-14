package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DBIF.EmployeeIF;
import Model.Model.Address;
import Model.Model.Employee;
import Model.Model.Warehouse;

public class EmployeeDB implements EmployeeIF{
	
	private AddressDB addressDB = new AddressDB();

	@Override
	public Employee getEmployee(long cprNo) throws SQLException {
		Employee res = null;
		String sqlEmployee = String.format("SELECT * FROM Employee WHERE cprNo = '%s'", cprNo);
		System.out.println(sqlEmployee);
		
		try(Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			ResultSet rsEmployee = s.executeQuery(sqlEmployee);
			
			if(rsEmployee.next()) {
				Address address = addressDB.getAddress(rsEmployee.getLong("address_id"));
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
				Address address = addressDB.getAddress(rsEmployee.getLong("address_id"));
				res = buildEmployee(rsEmployee, address);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}

	String sqlCreate = "INSERT INTO Employee (firstName, lastName, address, phone, email, password, cprNo, department, position, warehouse) VALUES (?,?,?,?,?,?,?,?,?,?)";
	@Override
	public long createEmployee(Employee employee) throws SQLException {
		long rowID = 0;
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		Address address = employee.getAddress();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		String password = employee.getPassword();
		long cprNo = employee.getCprNo();
		String department = employee.getDepartment();
		String position = employee.getPosition();
		Warehouse warehouse = employee.getWarehouse();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			//preparedStmt.setAddress(3, address);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, password);
			preparedStmt.setLong(7, cprNo);
			preparedStmt.setString(8, department);
			preparedStmt.setString(9, position);
			//preparedStmt.setWarehouse(10, warehouse);
			
			rowID = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return rowID;
	}

	
	public int updateEmployee(Employee employee) throws SQLException {
		Employee oldEmployee = getEmployee(employee.getCprNo());
		
		int rs = -1;
		
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		Address address = employee.getAddress();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		String password = employee.getPassword();
		String department = employee.getDepartment();
		String position = employee.getPosition();
		Warehouse warehouse = employee.getWarehouse();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
		
		
		StringBuffer columns = new StringBuffer( 255 );
		 
		if ( firstName != null && 
			     !firstName.equals(oldEmployee.getFirstName() ) )
			  {
			    columns.append( "first name = '" + firstName + "'" );
			  }
		
		if ( lastName != null && 
			      !lastName.equals(oldEmployee.getLastName() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "last name = '" + lastName + "'" );
			  }
		
		if ( address != null && 
			      !address.equals(oldEmployee.getAddress() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "address = '" + address + "'" );
			  }
		
		if ( phone != null && 
			      !phone.equals(oldEmployee.getPhone() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "phone = '" + phone + "'" );
			  }
		
		if ( email != null && 
			      !email.equals(oldEmployee.getEmail() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "email = '" + email + "'" );
			  }
		
		if ( password != null && 
			      !password.equals(oldEmployee.getPassword() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "password = '" + password + "'" );
			  }
		
		if ( department != null && 
			      !department.equals(oldEmployee.getDepartment() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "department = '" + department + "'" );
			  }
		
		if ( position != null && 
			      !position.equals(oldEmployee.getPosition() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "position = '" + position + "'" );
			  }
		
		if ( warehouse != null && 
			      !warehouse.equals(oldEmployee.getWarehouse() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "warehouse = '" + warehouse + "'" );
			  }
		
		 if ( columns.length() > 0 )
		  {
		    String sqlString = "update Employees SET " + columns.toString() + 
		            " WHERE employee cprNo = " + employee.getCprNo();
		    System.out.println("\nExecuting: " + sqlString);
		PreparedStatement preparedStmt = con.prepareStatement(sqlString);
				 int res = preparedStmt.executeUpdate();
				 rs = res;
		  }
		  else
		  {
		    System.out.println( "Nothing to do to update Employee CprNo: " + 
		                        employee.getCprNo());
		  }
		} catch (SQLException e) {
			throw e;
		}
		return rs;
	}
	
	@Override
	public void deleteEmployee(long cprNo) throws SQLException {
		 /* Connection connection = null;
	       Statement stmt = null;
		
		try
		{ (Statement s = DBConnection.getInstance().getConnection().createStatement()) {
			
			stmt.execute("DELETE FROM EMPLOYEE WHERE CPRNO >= 1");
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {   
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            	}
        	}
		}*/
		
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

