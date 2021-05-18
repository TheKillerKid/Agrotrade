package Model.DB;

import java.sql.SQLException;

import Model.DBIF.InvoiceIF;

import Model.Model.Invoice;

public class InvoiceDB implements InvoiceIF {

	public long createInvoice(Invoice invoice) throws SQLException {
		String sqlCreate = "INSERT INTO Invoice";
		
		long id = 0;
		
				
		return id;
	}
	
	
}
