package Model.Model;

public enum MessagesEnum {
	//ERROR
	DBERROR("Something went wrong with database, please try again."),
	PARSEERROR("Cannot parse values from fields. Write values in correct format."),
	CPRLENGHTERROR("CPR number should has 10 digits. (example: 0209972128)"),
	CVRLENGHTERROR("CVR number should has 8 digits. (example: 02099721)"),
	STATICDISCOUNTAMOUNTERROR("Static discount needs to be between 0 an 1. (example: 0.2 -> 20% discount)"),
	DBSAVEERROR("Creating object failed, no ID obtained. Contact administrator and try it later."),
	DBUPDATEERROR("Object wasn't updated! Contact administrator and try it later."),
	VALUEHIGHERTHANZEROERROR("Value should be higher than 0!"),
	EMPTYBORROWDATE("Borrow date cannot be empty!"),
	EMPTYEXPECTEDRETURNDATE("Expected return date cannot be empty!"),
	PURCHASEREACHMAXPRODUCTS("System cannot add there more product because warehouse doesn't have capacity for that! Maximum amount is: "),
	FIRSTNAMEREQUIREDERROR("First name is required!"),
	LASTNAMEREQUIREDERROR("Last name is required!"),
	EMAILREQUIREDERROR("Email is required!"),
	PASSWORDREQUIREDERROR("Password is required!"),
	ADDRESSREQUIREDERROR("Information about address are required!"),
	STATICDISCOUNTREQUIREDERROR("Static discount is required!"),
	CVRNUMBERREQUIREDERROR("CVR number is required!"),
	MINSTOCKREQUIREDERROR("Min stock is required"),
	MAXSTOCKREQUIREDERROR("Max stock is required"),
	//SUCCESS
	PRODUCTSAVED("Product saved! You can go and create purchase to fill your warehouse."),
	EMPLOYEESAVED("Employee saved!"),
	CUSTOMERSAVED("Customer saved!"),
	SUPPLIERSAVED("Supplier saved!"),
	PRODUCTADDEDTOORDER("Product was added to order!"),
	SALECREATED("Sale was created!"),
	LEASECREATED("Lease was created!"),
	PURCHASECREATED("Purchase was created!"),
	SENDSALESUCCESS("Sale was send to customer!"),
	SALEDELIVEREDSUCCESS("Sale was delivered to customer!"),
	PURCHASERECIEVED("Purchase was recieved!"),
	LEASERETURNEDSUCCESS("Lease was returned!")
	;
	
	public final String text;

    private MessagesEnum(String text) {
        this.text = text;
    }		
}
