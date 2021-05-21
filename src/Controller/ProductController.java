package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.DB.ProductDB;
import Model.Model.Product;
import Model.Model.StockProduct;

public class ProductController {

		private ProductDB productDb = new ProductDB();

		public Product getProductByBarcode(long barcode) throws SQLException {
			try {
				return productDb.getProductByBarcode(barcode);
			} catch (SQLException e){
				throw e;
			}
		}
		
		public Product getProductById(long id) throws SQLException {
			try {
				return productDb.getProductByBarcode(id);
			} catch (SQLException e){
				throw e;
			}
		}
		
		public long createProduct(Product product) throws SQLException {
			return -1;
		}
		
		public String generateBarcode() throws SQLException{
		long barcode = 0;
			
			try {
				while(barcode == 0) {
					long pregeneratedBarcode = ThreadLocalRandom.current().nextLong(10000,100000);
					Product product = getProductByBarcode(pregeneratedBarcode);
					if (product == null) {
						barcode = pregeneratedBarcode;
					}
				}				
			} catch (SQLException e) {
				throw e;
			} 
			
			return String.valueOf(barcode);
		}
		
		public ArrayList<StockProduct> getProductList() {
			return null;
		}
}
