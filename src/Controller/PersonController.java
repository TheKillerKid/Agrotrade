package Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Model.Employee;
import Model.Model.MessagesEnum;
import Model.Model.Person;
import Model.Model.PersonFilter;
import Model.Model.Customer;
import Model.Model.Supplier;

public class PersonController {

	private EmployeeController employeeCtrl = new EmployeeController();
	private CustomerController customerCtrl = new CustomerController();
	private SupplierController supplierCtrl = new SupplierController();
	
	public Employee getEmployeeByCprNo(String cprNo) throws SQLException {
		try {
			return employeeCtrl.getEmployeeByCprNo(cprNo);
		} catch (SQLException e) {
			throw e;
		}	
	}
	
	public Employee getEmployeeByEmail(String email) throws SQLException {
		try {
			return employeeCtrl.getEmployeeByEmail(email);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Customer getCustomer(String cvrNo) throws SQLException {
		try {
			return customerCtrl.getCustomer(cvrNo);
		} catch (SQLException e) {
			throw e;
		}	
	}
	
	public Supplier getSupplierByCVRNumber(String cvrNo) throws SQLException {
		try {
			return supplierCtrl.getSupplierByCVRNumber(cvrNo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	public Supplier getSupplierById(long id) throws SQLException {
		try {
			return supplierCtrl.getSupplierById(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public Person createPerson(Person person) throws SQLException {
		try {
			if(person instanceof Employee) {
				return this.employeeCtrl.createEmployee((Employee)person);
			}
			
			if (person instanceof Customer) {
				return this.customerCtrl.createCustomer((Customer)person);
			}
			
			if (person instanceof Supplier) {
				return this.supplierCtrl.createSupplier((Supplier)person);
			}
			return null;
			
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
			ArrayList<Customer> customers = customerCtrl.getCustomers();
			for(Customer customer : customers) {
				list.add(customer);
			}
		}
		
		if(filter.isEmployee()) {
			ArrayList<Employee> employees = employeeCtrl.getEmployees();
			for(Employee employee : employees) {
				list.add(employee);
			}
		}
		
		return list;
	}
	
	public LocalDate getDateOfBirth(String cprNo) throws Exception {
		try {
			if(cprNo.length() == 10) {
				int day = ParsingHelper.tryParseInt(cprNo.substring(0, 2));
				int month = ParsingHelper.tryParseInt(cprNo.substring(2, 4));
				int year = ParsingHelper.tryParseInt(cprNo.substring(4, 6));
				
				int currentYearLastTwoDigits = ParsingHelper.tryParseInt(String.valueOf(LocalDate.now().getYear()).substring(2,4));
				
				if(currentYearLastTwoDigits < year) {
					year += 1900;
				}
				if(currentYearLastTwoDigits >= year) {
					year += 2000;
				}
				
				return LocalDate.of(year, month, day);
			}
			else {
				throw new Exception(MessagesEnum.CPRLENGHTERROR.text);
			}
		} catch(NumberFormatException e1) {
			throw e1;
		}
		catch(Exception e2) {
			throw e2;
		}
	}
}