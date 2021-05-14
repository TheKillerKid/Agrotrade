package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Sale;

public interface SaleIF {
	public Sale createSale(Sale sale) throws SQLException;
	 
	//Sale update(Sale sale) throws SQLException;
	 
	//void delete(long id) throws SQLException;
}
