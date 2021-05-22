package Controller;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.DB.ProductDB;
import Model.Model.Product;
import Model.Model.StockProduct;

public class ProductController {

		private ProductDB productDb = new ProductDB();

		public Product getProductByBarcode(String barcode) throws SQLException {
			try {
				return productDb.getProductByBarcode(barcode);
			} catch (SQLException e){
				throw e;
			}
		}
		
		public Product getProductById(long id) throws SQLException {
			try {
				return productDb.getProductById(id);
			} catch (SQLException e){
				throw e;
			}
		}
		
		public Product createProduct(Product product, int minStock, int maxStock) throws SQLException {
			try {
				return productDb.createProduct(product, minStock, maxStock);
			} catch (SQLException e) {
				throw e;
			}
		}

		
		public String generateBarcode() throws SQLException {
			String barcode = "";
			
			try {
				while(barcode.isEmpty()) {
					int len = 12;
			        int randNumOrigin = 48;
					int randNumBound = 122; 	
					
					SecureRandom random = new SecureRandom();
			        String pregeneratedBarcode = random.ints(randNumOrigin, randNumBound + 1)
			                .filter(i -> Character.isDigit(i))
			                .limit(len)
			                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
			                        StringBuilder::append)
			                .toString();
			        
			        Product product = getProductByBarcode(pregeneratedBarcode);
					if (product == null) {
						barcode = pregeneratedBarcode;
					}
				}
				
	        } catch(SQLException e) {
	        	throw e;
	        }
			return barcode;
	    }
		
		public ArrayList<StockProduct> getProductList() {
			return null;
		}
}
