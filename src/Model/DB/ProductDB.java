package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Model.Category;
import Model.Model.MessagesEnum;
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
	private CategoryDB categoryDb = new CategoryDB();
	private UnitDB unitDb = new UnitDB();
	
	public Product getProductByBarcode(String barcode) throws SQLException {
		Product product = null;
		String sqlProduct = "SELECT * FROM Product WHERE barcode = ?";
		Connection con = DBConnection.getInstance().getConnection();
		 
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
			preparedStmt.setString(1, barcode);
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
		
		
		Connection con = DBConnection.getInstance().getConnection();

     try {
				PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
				preparedStmt.setLong(1, id);
				ResultSet rsProduct = preparedStmt.executeQuery();
				if (rsProduct.next()) {
					product = buildProduct(rsProduct);
					product.setSalePrice(priceDb.getPrice(product.getId(), PriceType.SALE));
					product.setLeasePrice(priceDb.getPrice(product.getId(), PriceType.LEASE));
					product.setPurchasePrice(priceDb.getPrice(product.getId(), PriceType.PURCHASE));
					product.setSupplier(supplierDb.getSupplierById(rsProduct.getLong("supplier_id")));
					product.setCategory(categoryDb.getCategory(rsProduct.getLong("category_id")));
					product.setUnit(unitDb.getUnit(rsProduct.getLong("unit_id")));
					
				}
		} catch (SQLException e) {
			throw e;
		}
		return product;
	}

	@Override
	public Product createProduct(Product product, int minStock, int maxStock) throws SQLException {
		
		String sqlCreate = "INSERT INTO Product (barcode, name, category_id, unit_id, supplier_id) VALUES (?,?,?,?,?)";
		
		long id;
		String barcode = product.getBarcode();
		String name = product.getName();
		long categoryId = product.getCategory().getId();													
		Price purchasePrice = product.getPurchasePrice();
		Price salePrice = product.getSalePrice();
		Price leasePrice = product.getLeasePrice();
		long unitId = product.getUnit().getId();
		long supplierId = product.getSupplier().getId();
		
	    Connection con = DBConnection.getInstance().getConnection();
	
	    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, barcode);
			preparedStmt.setString(2, name);
			preparedStmt.setLong(3, categoryId);
			preparedStmt.setLong(4, unitId);
			preparedStmt.setLong(5, supplierId);
		
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getLong(1));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
			
			product.getPurchasePrice().setId(priceDb.createPrice(purchasePrice, product.getId()));
			product.getSalePrice().setId(priceDb.createPrice(salePrice, product.getId()));
			product.getLeasePrice().setId(priceDb.createPrice(leasePrice, product.getId()));
			
			createStockProducts(product.getId(), minStock, maxStock);
			
		} catch (SQLException e) {
			throw e;
		}
		return product;
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
	}
	
	@Override
	public void createStockProducts(long productId, int minStock, int maxStock) throws SQLException {

		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
		
		String sqlCreate = "INSERT INTO StockProduct (amount, min_stock, max_stock, product_id, warehouse_id) VALUES (?,?,?,?,?)";
		
		Connection con = DBConnection.getInstance().getConnection();

		try {
			ArrayList<Warehouse> warehouses = warehouseDb.getWarehouses();
			
			for(Warehouse warehouse : warehouses) {
				PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
				preparedStmt.setInt(1, 0);
				preparedStmt.setInt(2, minStock);
				preparedStmt.setInt(3, maxStock);
				preparedStmt.setLong(4, productId);
				preparedStmt.setLong(5, warehouse.getId());
				
				preparedStmt.executeUpdate();
				
				long id = -1;
				
				ResultSet rs = preparedStmt.getGeneratedKeys();
	            if (rs.next()) {
	                id = rs.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating stock product failed, no ID obtained.");
	            }
				stockProducts.add(new StockProduct(id, 0, minStock, maxStock, null, warehouse.getId()));
			}
			
		} catch(SQLException e) {
			throw e;
		}
	}
	
	private Product buildProduct(ResultSet rsProduct) throws SQLException {
		return new Product(rsProduct.getLong("id"), 
						   rsProduct.getString("barcode"), 
						   rsProduct.getString("name"), 
						   null, 
					       null, 
						   null, 
						   null, 
						   null, 
						   null);
	}
}
