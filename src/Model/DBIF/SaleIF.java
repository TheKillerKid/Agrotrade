package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Sale;

public interface SaleIF {
	Sale createSale(Sale sale) throws Exception;
	
	void sendSale(long id) throws Exception;
	
	void saleDelivered(long id) throws Exception;
	
	Sale getSale(long id) throws SQLException;
	 
	//Sale update(Sale sale) throws SQLException;
}
