package Model.Model;

public class LoginContainer {
private static LoginContainer INSTANCE;
	
	private String email;
	private boolean isLogged;

	public LoginContainer() {
	}
	
    public static LoginContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LoginContainer();
        }
        
        return INSTANCE;
    }
    
    public String getEmailOfLoggedUser() {
    	return email;
    }
    
    public boolean isLogged() {
    	return isLogged;
    }
    
    public boolean login(String email) {
    	this.email = email;
    	this.isLogged = true;
    	return true;
    }
    
    public void logout() {
    	email = null;
    	isLogged = false;
    }
}
