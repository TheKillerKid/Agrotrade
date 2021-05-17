package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Category;
import Model.Model.Price;
import Model.Model.PriceType;
import Model.Model.Product;
import Model.Model.Supplier;
import Model.Model.Unit;

import Model.DBIF.ProductIF;

public class ProductDB implements ProductIF {
	
	private PriceDB priceDb = new PriceDB();
	private SupplierDB supplierDb = new SupplierDB();
	
	public Product getProduct(long barcode) throws SQLException {
		Product product = null;
		String sqlProduct = "SELECT * FROM Product WHERE barcode = ?";
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
			preparedStmt.setLong(1, barcode);
			ResultSet rsProduct = preparedStmt.executeQuery();
			if (rsProduct.next()) {
				product = buildProduct(rsProduct);
				product.setSalePrice(priceDb.getPrice(product.getId(), PriceType.SALE));
				product.setLeasePrice(priceDb.getPrice(product.getId(), PriceType.LEASE));
				product.setPurchasePrice(priceDb.getPrice(product.getId(), PriceType.PURCHASE));
				product.setSupplier(supplierDb.getSupplierById(product.getSupplier().getSupplierId()));
			}
		} catch (SQLException e) {
			throw e;
		}
		return product;
	}

	private Product buildProduct(ResultSet rsProduct) throws SQLException {
		return new Product(rsProduct.getLong("id"), 
						   rsProduct.getLong("barcode"), 
						   rsProduct.getString("name"), 
						   new Category(rsProduct.getLong("category_id"), rsProduct.getString("category_name")), 
					       null, 
						   null, 
						   null, 
						   new Unit(rsProduct.getLong("unit_id"), rsProduct.getString("unit_name")), 
						   rsProduct.getInt("discount"), 
						   new Supplier(rsProduct.getLong("supplier_id")));
	}

	@Override
	public long createProduct(Product product) throws SQLException {
		
		String sqlCreate = "INSERT INTO Product (barcode, name, category_id, unit_id, supplier_id) VALUES (?,?,?,?,?)";
		
		long id;
		long barcode = product.getBarcode();
		String name = product.getName();
		long categoryId = product.getCategory().getId();													
		Price purchasePrice = product.getPurchasePrice();
		Price salePrice = product.getSalePrice();
		Price leasePrice = product.getLeasePrice();
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
			
			priceDb.createPrice(purchasePrice, id);
			priceDb.createPrice(salePrice, id);
			priceDb.createPrice(leasePrice, id);
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
