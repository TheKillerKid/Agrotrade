package Model.Model;

public enum MessagesEnum {
	//ERROR
	DBERROR("Something went wrong with database, please try again."),
	PARSEERROR("Cannot parse values from fields. Write values in correct format."),
	CPRLENGHTERROR("CPR number should has 10 digits. (example: 0209972128)"),
	DBSAVEERROR("Creating object failed, no ID obtained. Contact administrator and try it later."),
	//SUCCESS
	PRODUCTSAVED("Product saved! You can go and create purchase to fill your warehouse."),
	EMPLOYEESAVED("Employee saved!"),
	CUSTOMERSAVED("Customer saved!"),
	SUPPLIERSAVED("Supplier saved!")
	;
	
	public final String text;

    private MessagesEnum(String text) {
        this.text = text;
    }		
}
