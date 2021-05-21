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
import Model.Model.StockProduct;
import Model.Model.Supplier;
import Model.Model.Unit;
import Model.Model.Warehouse;
import Model.DBIF.ProductIF;

public class ProductDB implements ProductIF {
	
	private PriceDB priceDb = new PriceDB();
	private SupplierDB supplierDb = new SupplierDB();
	private WarehouseDB warehouseDb = new WarehouseDB();
	
	public Product getProductByBarcode(long barcode) throws SQLException {
		Product product = null;
		String sqlProduct = "SELECT * FROM Product WHERE barcode = ?";
		Connection con = DBConnection.getInstance().getConnection();
		 
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
			preparedStmt.setLong(1, barcode);
			ResultSet rsProduct = preparedStmt.executeQuery();
			if (rsProduct.next()) {
				product = buildProduct(rsProduct);
				product.setSalePrice(priceDb.getPrice(product.getId(), PriceType.SALE));
				product.setLeasePrice(priceDb.getPrice(product.getId(), PriceType.LEASE));
				product.setPurchasePrice(priceDb.getPrice(product.getId(), PriceType.PURCHASE));
				product.setSupplier(supplierDb.getSupplierById(product.getSupplier().getId()));
			}
		} catch (SQLException e) {
			throw e;
		}
		return product;
	}
	
	public Product getProductById(long id) throws SQLException {
		Product product = null;
		String sqlProduct = "SELECT * FROM Product WHERE id = ?";
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
			preparedStmt.setLong(1, id);
			ResultSet rsProduct = preparedStmt.executeQuery();
			if (rsProduct.next()) {
				product = buildProduct(rsProduct);
				product.setSalePrice(priceDb.getPrice(product.getId(), PriceType.SALE));
				product.setLeasePrice(priceDb.getPrice(product.getId(), PriceType.LEASE));
				product.setPurchasePrice(priceDb.getPrice(product.getId(), PriceType.PURCHASE));
				product.setSupplier(supplierDb.getSupplierById(product.getSupplier().getId()));
			}
		} catch (SQLException e) {
			throw e;
		}
		return product;
	}

	@Override
	public long createProduct(Product product, int minStock, int maxStock) throws SQLException {
		
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
			
			
			createStockProducts(id, minStock, maxStock);
			
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			  
		}
	}
	
	@Override
	public ArrayList<StockProduct> createStockProducts(long productId, int minStock, int maxStock) throws SQLException {

		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
		
		String sqlCreate = "INSERT INTO StockProducts (amount, min_stock, max_stock, product_id, warehouse_id) VALUES (?,?,?,?,?)";
		
		
		Connection con = DBConnection.getInstance().getConnection();

    try {
			ArrayList<Warehouse>warehouses = warehouseDb.getWarehouses();
			
			for(Warehouse warehouse : warehouses) {
				PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
				preparedStmt.setInt(1, 0);
				preparedStmt.setInt(2, minStock);
				preparedStmt.setInt(3, maxStock);
				preparedStmt.setLong(4, productId);
				preparedStmt.setLong(5, warehouse.getId());
				
				long id = preparedStmt.executeUpdate();
				stockProducts.add(new StockProduct(id, 0, minStock, maxStock, null, warehouse.getId()));
			}
			
		} catch(SQLException e) {
			throw e;
		}
		
		return stockProducts;
	}

	@Override
	public void getProductList() throws SQLException {
		String sqlGet = "SELECT p.barcode, p.name, s.first_name AS supplier_name, s.last_name AS supplier_last_name, c.name AS category_name, u.name AS unit_name from Product p LEFT JOIN Supplier s ON p.supplier_id = s.id LEFT JOIN Category c ON p.category_id = c.id LEFT JOIN Unit u ON p.unit_id = u.id";
		ArrayList<Product> res = null;
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlGet);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				System.out.println(rs);
			}
		}
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
}
