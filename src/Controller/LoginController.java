package Controller;

import java.sql.SQLException;

import Model.Model.Employee;
import Model.Model.LoginContainer;

public class LoginController {

	private EmployeeController employeeController;
	private LoginContainer loginContainer;
	


	public LoginController() {
		employeeController = new EmployeeController();
		loginContainer = LoginContainer.getInstance();
	}
	
	public boolean isLogged() {
		return loginContainer.isLogged();
	}

	public boolean login(String email, String password) throws SQLException{
		Employee employee;
		boolean res = false;

		try {
			employee = this.employeeController.getEmployeeByEmail(email);
			
			if(employee != null && password.equals(employee.getPassword())) {
				loginContainer.login(employee);
				res = true;
			}
			
		} catch (SQLException e) {
			throw e;
		}

		return res;
	}
	
	public void logout() {
		loginContainer.logout();
	}
}
