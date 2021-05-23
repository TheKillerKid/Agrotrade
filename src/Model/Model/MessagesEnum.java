package Model.Model;

public enum MessagesEnum {
	DBERROR("Something went wrong with database, please try again."),
	PARSEERROR("Cannot parse values from fields. Write values in correct format.")
	;
	
	public final String text;

    private MessagesEnum(String text) {
        this.text = text;
    }		
}
