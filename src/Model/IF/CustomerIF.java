package Model.IF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Customer;

public interface CustomerIF {
	public Customer getCustomer(String cvrNo) throws SQLException;
	
	public Customer getCustomerById(long id) throws SQLException;
	
	public Customer createCustomer(Customer customer) throws SQLException;
	
	public void updateCustomer(Customer customer) throws SQLException;
	
	public ArrayList<Customer> getCustomerList() throws SQLException;
	
}
