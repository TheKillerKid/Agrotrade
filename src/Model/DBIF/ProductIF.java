package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Model.Product;

public interface ProductIF {
	
	Product getProductByBarcode(long barcode) throws SQLException;
	
	Product getProductById(long barcode) throws SQLException;
	
	long createProduct(Product product, int minStock, int maxStock) throws SQLException;
	
	void updateProduct(Product product) throws SQLException;
	
	void getProductList() throws SQLException;
}
