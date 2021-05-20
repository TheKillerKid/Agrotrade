package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Customer;

public interface CustomerIF {
	public Customer getCustomer(long cvrNo) throws SQLException;
	
	public long createCustomer(Customer customer) throws SQLException;
	
	public void updateCustomer(Customer customer) throws SQLException;
	
	public void deleteCustomer(long cvrNo) throws SQLException;
	
//	public Arraylist<Customer> getCustomerList() throws SQLException;
	
}
