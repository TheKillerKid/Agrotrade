package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.CustomerDB;
import Model.Model.Customer;

public class CustomerController {

	private CustomerDB customerDb = new CustomerDB();
	
	public Customer getCustomer(String cvrNo) throws SQLException{
		try {
			return customerDb.getCustomer(cvrNo);
		} catch (SQLException e) {
			throw e;
		}
	}

	public Customer getCustomerById(long id) throws SQLException{
		try {
			return customerDb.getCustomerById(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Customer createCustomer(Customer customer) throws SQLException{
		try {
			return customerDb.createCustomer(customer);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<Customer> getCustomers() throws SQLException {
		try {
			return customerDb.getCustomerList();
		} catch (SQLException e) {
			throw e;
		}
	}
	
}