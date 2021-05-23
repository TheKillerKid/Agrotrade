package Model.Model;

public enum MessagesEnum {
	//ERROR
	DBERROR("Something went wrong with database, please try again."),
	PARSEERROR("Cannot parse values from fields. Write values in correct format."),
	CPRLENGHTERROR("CPR number should has 10 digits. (example: 0209972128)"),
	
	//SUCCESS
	PRODUCTSAVED("Product saved! You can go and create purchase to fill your warehouse."),
	;
	
	public final String text;

    private MessagesEnum(String text) {
        this.text = text;
    }		
}
