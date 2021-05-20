package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.CustomerDB;
import Model.Model.Customer;

public class CustomerController {

	CustomerDB customerDb = new CustomerDB();
	
	public Customer getCustomer(long cvrNo) throws SQLException{
		try {
			return customerDb.getCustomer(cvrNo);
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