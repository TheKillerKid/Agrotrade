package Model.IF;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Model.Product;
import Model.Model.ProductView;
import Model.Model.StockProduct;

public interface ProductIF {
	ArrayList<ProductView> getProductsForView(long warehouseId) throws SQLException;
	
	Product getProductByBarcode(String barcode) throws SQLException;
	
	Product getProductById(long id) throws SQLException;
	
	Product createProduct(Product product, int minStock, int maxStock, String productLocation) throws SQLException;
	
	void createStockProducts(long productId, int minStock, int maxStock, String productLocation) throws SQLException;
	
	StockProduct getStockProductByProductId(long productId, long warehouseId) throws SQLException;
	
	void updateProduct(Product product) throws SQLException;
}
