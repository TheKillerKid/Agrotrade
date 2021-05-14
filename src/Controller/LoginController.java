package Controller;

import java.sql.SQLException;

import Model.Model.LoginContainer;

public class LoginController {

	private EmployeeController employeeController;
	private LoginContainer loginContainer;
	


	public LoginController() {
		employeeController = new EmployeeController();
		loginContainer = new LoginContainer();
	}
	
	public boolean isLogged() {
		return loginContainer.isLogged();
	}

	public void login(String email, String password) throws SQLException {
		this.employeeController.getEmployee(email);
	}
	
	
	public boolean isPasswordCorrect(String email, String password) throws SQLException {
		if(password.equals(employeeController.generateRandomPassword())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void logout() {
		loginContainer.logout();
	}
}
