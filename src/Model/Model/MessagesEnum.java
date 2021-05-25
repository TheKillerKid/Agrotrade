package Model.Model;

public enum MessagesEnum {
	//ERROR
	DBERROR("Something went wrong with database, please try again."),
	PARSEERROR("Cannot parse values from fields. Write values in correct format."),
	CPRLENGHTERROR("CPR number should has 10 digits. (example: 0209972128)"),
	DBSAVEERROR("Creating object failed, no ID obtained. Contact administrator and try it later."),
	VALUEHIGHERTHANZEROERROR("Value should be higher than 0!"),
	EMPTYBORROWDATE("Borrow date cannot be empty!"),
	EMPTYEXPECTEDRETURNDATE("Expected return date cannot be empty!"),
	PURCHASEREACHMAXPRODUCTS("System cannot add there more product because warehouse doesn't have capacity for that! Maximum amount is: "),
	//SUCCESS
	PRODUCTSAVED("Product saved! You can go and create purchase to fill your warehouse."),
	EMPLOYEESAVED("Employee saved!"),
	CUSTOMERSAVED("Customer saved!"),
	SUPPLIERSAVED("Supplier saved!"),
	PRODUCTADDEDTOORDER("Product was added to order!"),
	SALECREATED("Sale was created!"),
	LEASECREATED("Lease was created!"),
	PURCHASECREATED("Purchase was created!"),
	;
	
	public final String text;

    private MessagesEnum(String text) {
        this.text = text;
    }		
}
