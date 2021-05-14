package Controller;

import Model.Model.Employee;

public class LoginController {

	private String email;
	private String password;
	private EmployeeController employeeController;
	


	public LoginController() {
		employeeController = new EmployeeController();
	}
	
	/**
	* Checks if user is loged in
	*/
	public boolean isLogged() {
		return false;
		//return loginContainer.isLogged();
	}
	
	/**
	 * Function for loging to the system
	 * @param email
	 * @param password
	 * @return true if loged on
	 */
	public boolean login(String email, String password) {
		return false;
		/*if(loginContainer.isLogged()) {
			return true;
		}
		
		if(!isPasswordCorrect(email, password)) {
			return false;
		}
		else {
			return this.loginContainer.login(email);
		}*/			
	}
	
	/**
	 * Helper function for checking if password is correct for user
	 * @param email
	 * @param password
	 * @return true if password is correct
	 */
	public boolean isPasswordCorrect(String email, String password) {
		return false;
		/*Person person = PersonController.getPerson(email);
		if(password.equals(person.getPassword())) {
			return true;
		}
		else {
			return false;
		}*/
	}
	
	/**
	* Log out function
	*/
	public void logout() {
		//loginContainer.logout();
	}
}
