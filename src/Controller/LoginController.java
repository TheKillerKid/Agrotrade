package Controller;

import Model.Model.Employee;

public class LoginController {

	private String email;
	private String password;
	//private EmployeeController employeeController = new EmployeeController();


	public Employee login(String email, String password) {
		this.email = email;
		this.password = password;
		
		return null;
	}
}
