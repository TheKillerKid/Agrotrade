package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Model.Product;
import Model.Model.StockProduct;

public interface ProductIF {
	
	Product getProductByBarcode(String barcode) throws SQLException;
	
	Product getProductById(long id) throws SQLException;
	
	Product createProduct(Product product, int minStock, int maxStock) throws SQLException;
	
	void createStockProducts(long productId, int minStock, int maxStock) throws SQLException;
	
	void updateProduct(Product product) throws SQLException;
}
