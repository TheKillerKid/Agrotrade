package Model.Model;

public class PersonFilter {

	private boolean isSupplier;
	private boolean isCustomer;
	private boolean isEmployee;
	
	public PersonFilter() {	
	}

	public boolean isSupplier() {
		return isSupplier;
	}

	public void setSupplier(boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
}
