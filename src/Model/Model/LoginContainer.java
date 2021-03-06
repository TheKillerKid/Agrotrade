package Model.Model;

public class LoginContainer {
private static LoginContainer INSTANCE;
	
	private Employee employee;
	public boolean betaEnabled; 

	private LoginContainer() {
		betaEnabled = false;
	}
	
    public static LoginContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LoginContainer();
        }
        
        return INSTANCE;
    }
    
    public boolean isLogged() {
    	return employee != null;
    }
    
    public void login(Employee employee) {
    	this.employee = employee;
    }
    
    public void logout() {
    	employee = null;
    }
    
    public Employee getCurrentUser() {
    	return employee;
    }
    
}
