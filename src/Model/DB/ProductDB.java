package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Model.Product;
import Model.DBIF.ProductDBIF;

public class ProductDB implements ProductDBIF {
	
	public Product getProduct(long barcode) throws SQLException {
		Product res = null;
		String sqlProduct = String.format("SELECT * FROM Product WHERE barcode = '%s'");
		
		try (Statement s = DBConnection.getInstance().getConnection().createStatement()){
			ResultSet rsProduct = s.executeQuery(sqlProduct);
			if (rsProduct.next()) {
				res = buildProduct(rsProduct);
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}

	private Product buildProduct(ResultSet rsProduct) {
		return null;
	}

	
	String sqlCreate = "INSERT INTO Product (barcode, name) VALUES (?,?)";    				//
	@Override
	public long createProduct(Product product) throws SQLException {
		
		long rowID = 0;
		long barcode = product.getBarcode();
		String name = product.getName();
		//int category_id = 1;			missing method 										
		//int unit_id
		//int supplier_id
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setLong(1, barcode);
			preparedStmt.setString(2, name);
		
			rowID = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return rowID;
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
		
		
	}

	@Override
	public ArrayList<Product> getProductList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
