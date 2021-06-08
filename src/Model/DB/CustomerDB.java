package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Model.Address;
import Model.IF.CustomerIF;
import Model.Model.Customer;
import Model.Model.MessagesEnum;

public class CustomerDB implements CustomerIF {
	
	private AddressDB addressDb = new AddressDB();
	
	public Customer getCustomer(String cvrNo) throws SQLException {
		Customer res = null;
		String sqlCustomer = ("SELECT * FROM Employee WHERE cvr_no = ?");
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCustomer);
			
				preparedStmt.setString(1, cvrNo);
				
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

	public Customer getCustomerById(long id) throws SQLException {
		Customer res = null;
		String sqlCustomer = ("SELECT * FROM Customer WHERE id = ?");
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCustomer);
	
			preparedStmt.setLong(1, id);
	
			ResultSet rsCustomer = preparedStmt.executeQuery();
			
			if(rsCustomer.next()) {
				res = buildCustomer(rsCustomer);
				res.setAddress(addressDb.getAddress(rsCustomer.getLong("address_id")));
			}
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return res;
	}

	public Customer createCustomer(Customer customer) throws SQLException {
		String sqlCreate = "INSERT INTO Customer (first_name, last_name, address_id, phone, email, cvr_no, static_discount) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		customer.getAddress().setId(addressDb.createAddress(customer.getAddress()));
		Address address = customer.getAddress();
		String phone = customer.getPhone();
		String email = customer.getEmail();
		String cvrNo = customer.getCvrNo();
		double staticDiscount = customer.getStaticDiscount();
		
	    Connection con = DBConnection.getInstance().getConnection();
	
	    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setLong(3, address.getId());
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, cvrNo);
			preparedStmt.setDouble(7, staticDiscount);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getLong(1));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }

            con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return customer;
	}
	
	public void updateCustomer(Customer customer) throws SQLException {
		Customer oldCustomer = getCustomer(customer.getCvrNo());
		
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		addressDb.createAddress(customer.getAddress());
		Address address = customer.getAddress();
		String phone = customer.getPhone();
		String email = customer.getEmail();
		double staticDiscount = customer.getStaticDiscount();
		
		Connection con = DBConnection.getInstance().getConnection();
		try {		
		
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
				 preparedStmt.executeUpdate();
		  }
		  else
		  {
		    System.out.println( "Nothing to do to update Employee CprNo: " + 
		                        customer.getCvrNo());
		  }
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public ArrayList<Customer> getCustomerList() throws SQLException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sqlCustomer = ("SELECT * FROM Customer");
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCustomer);
			
			ResultSet rsCustomer = preparedStmt.executeQuery();
			
			while(rsCustomer.next()) {
				Customer res = buildCustomer(rsCustomer);
				res.setAddress(addressDb.getAddress(rsCustomer.getLong("address_id")));
				customers.add(res);
			}
			
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}			
		return customers;
	}

	private Customer buildCustomer(ResultSet rs) throws SQLException {
		return new Customer(rs.getLong("id"), 
							rs.getString("first_name"), 
							rs.getString("last_name"), 
							null, 
							rs.getString("phone"), 
							rs.getString("email"), 
							rs.getString("cvr_no"), 
							rs.getDouble("static_discount"));
	}
}
