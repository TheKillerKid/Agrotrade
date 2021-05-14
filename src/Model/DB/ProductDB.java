package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Model.Category;
import Model.Model.Product;
import Model.Model.Unit;
//import Model.Model.Supplier;
import Model.DBIF.ProductDBIF;

public class ProductDB implements ProductDBIF {
	
	public Product getProduct(long barcode) throws SQLException {
		Product res = null;
		String sqlProduct = "SELECT * FROM Product WHERE barcode = '?'";
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
			preparedStmt.setLong(1, barcode);
			ResultSet rsProduct = preparedStmt.executeQuery();
			if (rsProduct.next()) {
				res = buildProduct(rsProduct);
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}

	private Product buildProduct(ResultSet rsProduct) throws SQLException {
		return new Product(rsProduct.getLong(0), rsProduct.getLong("barcode"), rsProduct.getString("name"), new Category(rsProduct.getLong("category_id"), rsProduct.getString("category_name")), new Unit(rsProduct.getLong("unit_id"), rsProduct.getString("unit_name")), rsProduct.getInt("discount"), null);
	}

	@Override
	public long createProduct(Product product) throws SQLException {
		
		String sqlCreate = "INSERT INTO Product (barcode, name) VALUES (?,?,?,?,?)";
		
		long id;
		long barcode = product.getBarcode();
		String name = product.getName();
		long categoryId = product.getCategory().getId();													
		long unitId = product.getUnit().getId();
		long supplierId = product.getSupplier().getId();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setLong(1, barcode);
			preparedStmt.setString(2, name);
			preparedStmt.setLong(3, categoryId);
			preparedStmt.setLong(4, unitId);
			preparedStmt.setLong(5, supplierId);
		
			id = preparedStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			  
		}
	//	getProduct()
	}

	@Override
	public ArrayList<Product> getProductList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
