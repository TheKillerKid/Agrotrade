package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Product;

public interface ProductDBIF {
	Product getProduct(long barcode) throws SQLException;
	
	
}
