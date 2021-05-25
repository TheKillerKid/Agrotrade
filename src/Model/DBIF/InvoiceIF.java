package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Invoice;

public interface InvoiceIF {
	public Invoice createInvoice(Invoice invoice, long orderId) throws SQLException;
	public Invoice getInvoice(long invoiceId) throws SQLException;
	
}
