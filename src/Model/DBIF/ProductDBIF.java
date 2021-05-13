package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Product;

public interface ProductDBIF {
	
	Product getProduct(long barcode) throws SQLException;
	
	long createProduct(Product product) throws SQLException;
	
	void updateProduct(Product product) throws SQLException;
	
	ArrayList<Product> getProductList() throws SQLException;
}
