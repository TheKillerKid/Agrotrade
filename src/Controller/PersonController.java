package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Employee;
import Model.Model.Person;
import Model.Model.PersonFilter;
import Model.Model.Customer;
import Model.Model.Supplier;

public class PersonController {

	private EmployeeController employeeCtrl = new EmployeeController();
	private CustomerController customerCtrl = new CustomerController();
	private SupplierController supplierCtrl = new SupplierController();
	
	public Employee getEmployee(long cprNo) throws SQLException {
		try {
			return employeeCtrl.getEmployee(cprNo);
		} catch (SQLException e) {
			throw e;
		}	
	}
	
	public Employee getEmployee(String email) throws SQLException {
		try {
			return employeeCtrl.getEmployee(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public Customer getCustomer(long cvrNo) throws SQLException {
		try {
			return customerCtrl.getCustomer(cvrNo);
			return null;
		} catch (SQLException e) {
			throw e;
		}	
	}*/
	
	/*public Supplier getSupplier(long cvrNo) throws SQLException {
			try {
		return supplierCtrl.getSupplierByCVRNumber(cvrNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	public long createPerson(Person person) throws SQLException {
		try {
			if(person instanceof Employee) {
				return this.employeeCtrl.createEmployee((Employee)person);
			}
			
			if (person instanceof Customer) {
				return -1;
			}
			
			if (person instanceof Supplier) {
				return -1;
			}
			return -1;
			
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<Person> getPeople(PersonFilter filter) throws SQLException {
		ArrayList<Person> list = new ArrayList<Person>(); 
		
		if(filter.isSupplier()) {
			ArrayList<Supplier> suppliers = supplierCtrl.getSuppliers();
			for(Supplier supplier : suppliers) {
				list.add(supplier);
			}
		}
		if(filter.isCustomer()) {
			/* ArrayList<Customer> customers = customerCtrl.getCustomers();
			for(Customer customer : customers) {
				list.add(customer);
			}*/
		}
		if(filter.isEmployee()) {
			ArrayList<Employee> employees = employeeCtrl.getEmployees();
			for(Employee employee : employees) {
				list.add(employee);
			}
		}
		return list;
	}
}

	

