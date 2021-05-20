package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Customer;
import Model.Model.Employee;
import Model.Model.Person;
import Model.Model.PersonFilter;
import Model.Model.Supplier;

public class PersonController {

	SupplierController supplierCtrl = new SupplierController();
	CustomerController customerCtrl = new CustomerController();
	EmployeeController employeeCtrl = new EmployeeController();
	
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
