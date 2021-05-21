package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DBIF.EmployeeIF;
import Model.Model.Address;
import Model.Model.Employee;
import Model.Model.Warehouse;

public class EmployeeDB implements EmployeeIF{
	
	private AddressDB addressDb = new AddressDB();
	private WarehouseDB warehouseDb = new WarehouseDB();

	@Override
	public Employee getEmployee(long cprNo) throws SQLException {
		Employee res = null;
		String sqlEmployee = ("SELECT * FROM Employee WHERE cpr_no = ?" );
		
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlEmployee);

			preparedStmt.setLong(1, cprNo);

			ResultSet rsEmployee = preparedStmt.executeQuery();

			if(rsEmployee.next()) {
				res = buildEmployee(rsEmployee);
				// res.setAddress(addressDb.getAddress(rsEmployee.getLong("address_id")));
				// res.setWarehouse(warehouseDb.getWarehouse(rsEmployee.getLong("warehouse_id")));
			}
		}

		catch (SQLException e) {
			throw e;
		}
		return res;
	}

	@Override
	public Employee getEmployee(String email) throws SQLException {
		Employee res = null;

		String sqlEmployee = ("SELECT * FROM Employee WHERE email = ?" );
		long warehouseId = -1;
		long addressId = -1;
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlEmployee);
				
			preparedStmt.setString(1, email);
				
			ResultSet rsEmployee = preparedStmt.executeQuery();
			
			if(rsEmployee.next()) {
				res = buildEmployee(rsEmployee);					
				warehouseId = rsEmployee.getInt("warehouse_id");
				addressId = rsEmployee.getInt("address_id");
			}
		} catch (SQLException e) {
			throw e;
		}
	
		if (res != null) {
			Warehouse warehouse = warehouseDb.getWarehouse(warehouseId);
			Address address = addressDb.getAddress(addressId);
			
			res.setWarehouse(warehouse);			
			res.setAddress(address);
		}
		
		return res;
	}

	String sqlCreate = "INSERT INTO Employee (firstName, lastName, address, phone, email, password, cpr_no, department, position, warehouse_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
	@Override
	public long createEmployee(Employee employee) throws SQLException {
		long id = 0;
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
			preparedStmt.setLong(3, address.getId());
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, password);
			preparedStmt.setLong(7, cprNo);
			preparedStmt.setString(8, department);
			preparedStmt.setString(9, position);
			preparedStmt.setLong(10, warehouse.getId());
			
			id = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return id;
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
		    String sqlString = "UPDATE Employees SET " + columns.toString() + 
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
            throw e;
        }finally {
 try {   
                stmt.close();
                connection.close();
            } catch (Exception e) {
                throw e;
            	}
        	}
		}*/
		
	}

	@Override
	public ArrayList<Employee> getEmployeeList(long warehouseId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	private Employee buildEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), null, rs.getString("phone"), rs.getString("email"), rs.getString("password"), rs.getLong("cpr_no"), rs.getString("department"), rs.getString("position"), null);
	}
}

