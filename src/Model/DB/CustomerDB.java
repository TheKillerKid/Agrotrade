package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Address;
import Model.DB.AddressDB;
import Model.Model.Customer;
import Model.Model.Employee;
import Model.Model.Warehouse;

public class CustomerDB {
	
	private AddressDB addressDb = new AddressDB();
	
	public Customer getCustomer(long cvrNo) throws SQLException {
		Customer res = null;
		String sqlCustomer = ("SELECT * FROM Employee WHERE cvr_no = ?");
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCustomer);
			
				preparedStmt.setLong(1, cvrNo);
				
				ResultSet rsCustomer = preparedStmt.executeQuery();
				
			if(rsCustomer.next()) {
				res = buildCustomer(rsCustomer);
				res.setAddress(addressDb.getAddress(rsCustomer.getLong("address_id")));
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}
	String sqlCreate = "INSERT INTO Customer (firstName, lastName, address, phone, email, cvrNo, staticDiscount) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public long createCustomer(Customer customer) throws SQLException {
		long id = 0;
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		Address address = customer.getAddress();
		String phone = customer.getPhone();
		String email = customer.getEmail();
		long cvrNo = customer.getCvrNo();
		int staticDiscount = customer.getStaticDiscount();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setLong(3, address.getId());
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setLong(6, cvrNo);
			preparedStmt.setInt(7, staticDiscount);
			
			id = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}
	
	public int updateCustomer(Customer customer) throws SQLException {
		Customer oldCustomer = getCustomer(customer.getCvrNo());
		
		int rs = -1;
		
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		Address address = customer.getAddress();
		String phone = customer.getPhone();
		String email = customer.getEmail();
		long cvrNo = customer.getCvrNo();
		int staticDiscount = customer.getStaticDiscount();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {		
		
		StringBuffer columns = new StringBuffer( 255 );
		 
		if ( firstName != null && 
			     !firstName.equals(oldCustomer.getFirstName() ) )
			  {
			    columns.append( "first name = '" + firstName + "'" );
			  }
		
		if ( lastName != null && 
			      !lastName.equals(oldCustomer.getLastName() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "last name = '" + lastName + "'" );
			  }
		
		if ( address != null && 
			      !address.equals(oldCustomer.getAddress() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "address = '" + address + "'" );
			  }
		
		if ( phone != null && 
			      !phone.equals(oldCustomer.getPhone() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "phone = '" + phone + "'" );
			  }
		
		if ( email != null && 
			      !email.equals(oldCustomer.getEmail() ) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "email = '" + email + "'" );
			  }
		
		if ( staticDiscount != 0 && 
			      !(staticDiscount == (oldCustomer.getStaticDiscount() )) ) {
			    if ( columns.length() > 0 ) {
			      columns.append( ", " );
			    }
			    columns.append( "staticDiscount = '" + staticDiscount + "'" );
			  }
		
		 if ( columns.length() > 0 ) 
		 {
		    String sqlString = "UPDATE Customers SET " + columns.toString() + 
		            " WHERE customer cvrNo = " + customer.getCvrNo();
		    System.out.println("\nExecuting: " + sqlString);
	    	PreparedStatement preparedStmt = con.prepareStatement(sqlString);
				 int res = preparedStmt.executeUpdate();
				 rs = res;
		  }
		  else
		  {
		    System.out.println( "Nothing to do to update Employee CprNo: " + 
		                        customer.getCvrNo());
		  }
		} catch (SQLException e) {
			throw e;
		}
		return rs;
	}
	
//	@Override
//	public void deleteEmployee(long cprNo) throws SQLException {
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
		
//	}

//	@Override
//	public ArrayList<Employee> getCustomerList(long cvrNo) throws SQLException {
		// TODO Auto-generated method stub
//		return null;
//	}


	private Customer buildCustomer(ResultSet rs) throws SQLException {
		return new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), null, rs.getString("phone"), rs.getString("email"), rs.getLong("cvr_no"), 0);
	}
}
