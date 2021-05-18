package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Invoice;

public interface InvoiceIF {
	public long createInvoice(Invoice invoice, long orderId) throws SQLException;
	
}
