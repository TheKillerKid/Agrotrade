package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.IF.EmployeeIF;
import Model.Model.Address;
import Model.Model.DepartmentType;
import Model.Model.Employee;
import Model.Model.MessagesEnum;
import Model.Model.PositionType;
import Model.Model.Warehouse;

public class EmployeeDB implements EmployeeIF{
	
	private AddressDB addressDb = new AddressDB();
	private WarehouseDB warehouseDb = new WarehouseDB();

	@Override
	public Employee getEmployeeByCprNo(String cprNo) throws SQLException {
		Employee res = null;
		String sqlEmployee = ("SELECT * FROM Employee WHERE cpr_no = ?" );
		
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlEmployee);

			preparedStmt.setString(1, cprNo);

			ResultSet rsEmployee = preparedStmt.executeQuery();

			if(rsEmployee.next()) {
				res = buildEmployee(rsEmployee);
				res.setAddress(addressDb.getAddress(rsEmployee.getLong("address_id")));
				res.setWarehouse(warehouseDb.getWarehouse(rsEmployee.getLong("warehouse_id")));
			}
		}

		catch (SQLException e) {
			throw e;
		}
		return res;
	}

	@Override
	public Employee getEmployeeByEmail(String email) throws SQLException {
		Employee res = null;

		String sqlEmployee = ("SELECT * FROM Employee WHERE email = ?" );
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlEmployee);

			preparedStmt.setString(1, email);

			ResultSet rsEmployee = preparedStmt.executeQuery();
			
			if(rsEmployee.next()) {
				res = buildEmployee(rsEmployee);	
				res.setWarehouse(warehouseDb.getWarehouse(rsEmployee.getInt("warehouse_id")));			
				res.setAddress(addressDb.getAddress(rsEmployee.getInt("address_id")));
			}
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		
		return res;
	}
	
	@Override
	public Employee getEmployeeById(long id) throws SQLException {
		Employee res = null;

		String sqlEmployee = ("SELECT * FROM Employee WHERE id = ?" );

		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlEmployee);
				
			preparedStmt.setLong(1, id);
				
			ResultSet rsEmployee = preparedStmt.executeQuery();
			
			if(rsEmployee.next()) {
				res = buildEmployee(rsEmployee);	
				res.setWarehouse(warehouseDb.getWarehouse(rsEmployee.getInt("warehouse_id")));			
				res.setAddress(addressDb.getAddress(rsEmployee.getInt("address_id")));
			}
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		
		return res;
	}
	
	@Override
	public Employee createEmployee(Employee employee) throws SQLException {
		String sqlCreate = "INSERT INTO Employee (first_name, last_name, date_of_birth, address_id, phone, email, password, cpr_no, department, position, warehouse_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		LocalDate dateOfBirth = employee.getDateOfBirth();
		employee.getAddress().setId(addressDb.createAddress(employee.getAddress()));
		Address address = employee.getAddress();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		String password = employee.getPassword();
		String cprNo = employee.getCprNo();
		String department = employee.getDepartment().name();
		String position = employee.getPosition().name();
		Warehouse warehouse = employee.getWarehouse();
		
	    Connection con = DBConnection.getInstance().getConnection();
	
	    try {
	    	PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setDate(3, java.sql.Date.valueOf(dateOfBirth));
			preparedStmt.setLong(4, address.getId());
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, password);
			preparedStmt.setString(8, cprNo);
			preparedStmt.setString(9, department);
			preparedStmt.setString(10, position);
			preparedStmt.setLong(11, warehouse.getId());
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
	        if (rs.next()) {
	            employee.setId(rs.getLong(1));
	        }
	        else {
	            throw new SQLException(MessagesEnum.DBSAVEERROR.text);
	        }

            con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return employee;
	}

	
	public int updateEmployee(Employee employee) throws SQLException {
	
		int rs = -1;
		
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		LocalDate dateOfBirth = employee.getDateOfBirth();
		Address address = employee.getAddress();
		String phone = employee.getPhone();
		String email = employee.getEmail();
		String password = employee.getPassword();
		String department = employee.getDepartment().name();
		String position = employee.getPosition().name();
		Warehouse warehouse = employee.getWarehouse();
		
		Connection con = DBConnection.getInstance().getConnection();
		try {		
			Employee oldEmployee = getEmployeeByCprNo(employee.getCprNo());
		
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
			
			if ( dateOfBirth != null && 
				      !dateOfBirth.equals(oldEmployee.getDateOfBirth() ) ) {
				    if ( columns.length() > 0 ) {
				      columns.append( ", " );
				    }
				    columns.append( "date of birth = '" + dateOfBirth + "'" );
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
	public void deleteEmployee(String cprNo) throws SQLException {
		//TODO: Auto-generated method from interface
	}

	@Override
	public ArrayList<Employee> getEmployeeList() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		String sqlEmployee = ("SELECT * FROM Employee");
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlEmployee);
			
			ResultSet rsEmployee = preparedStmt.executeQuery();
			
			while(rsEmployee.next()) {
				Employee res = buildEmployee(rsEmployee);
				res.setAddress(addressDb.getAddress(rsEmployee.getLong("address_id")));
				employees.add(res);
			}
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}			
		return employees;
	}
	


	private Employee buildEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getLong("id"), 
							rs.getString("first_name"), 
							rs.getString("last_name"), 
							rs.getDate("date_of_birth").toLocalDate(), 
							null, 
							rs.getString("phone"), 
							rs.getString("email"), 
							rs.getString("password"), 
							rs.getString("cpr_no"), 
							DepartmentType.valueOf(rs.getString("department")), 
							PositionType.valueOf(rs.getString("position")), 
							null);
	}
}

