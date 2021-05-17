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
		try {
			employee = this.employeeController.getEmployee(email);
			
			if(employee != null && password.equals(employee.getPassword())) {
				loginContainer.login(employee);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public void logout() {
		loginContainer.logout();
	}
}
