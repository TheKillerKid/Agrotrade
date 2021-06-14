package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.IF.ProductIF;
import Model.Model.MessagesEnum;
import Model.Model.Price;
import Model.Model.PriceType;
import Model.Model.Product;
import Model.Model.ProductView;
import Model.Model.StockProduct;
import Model.Model.Warehouse;

public class ProductDB implements ProductIF {

	private PriceDB priceDb = new PriceDB();
	private SupplierDB supplierDb = new SupplierDB();
	private WarehouseDB warehouseDb = new WarehouseDB();
	private CategoryDB categoryDb = new CategoryDB();
	private UnitDB unitDb = new UnitDB();
	
	@Override 
	public ArrayList<ProductView> getProductsForView(long warehouseId) throws SQLException {

		ArrayList<ProductView> products = new ArrayList<ProductView>();

		String sql = ("SELECT * FROM ProductView WHERE warehouse_id = ?");

		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sql);
	
			preparedStmt.setLong(1, warehouseId);			

			ResultSet rsProducts = preparedStmt.executeQuery();

			while(rsProducts.next()) {
				products.add(buildProductForView(rsProducts));
			}
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return products;
	}

	@Override
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
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}

		return product;
	}

	@Override
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
	public Product createProduct(Product product, int minStock, int maxStock, String productLocation) throws SQLException {

		String sqlCreate = "INSERT INTO Product (barcode, name, category_id, unit_id, supplier_id) VALUES (?,?,?,?,?)";

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

            if(purchasePrice != null) {
            	product.getPurchasePrice().setId(priceDb.createPrice(purchasePrice, product.getId()));
            }
            if(salePrice != null) {
            	product.getSalePrice().setId(priceDb.createPrice(salePrice, product.getId()));
            }
            if(leasePrice != null) {
            	product.getLeasePrice().setId(priceDb.createPrice(leasePrice, product.getId()));
            }

			createStockProducts(product.getId(), minStock, maxStock, productLocation);
			
            con.commit();


		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return product;
	}

	@Override
	public void updateProduct(Product product) throws SQLException {
	}

	@Override
	public void createStockProducts(long productId, int minStock, int maxStock, String productLocation) throws SQLException {

		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();

		String sqlCreate = "INSERT INTO StockProduct (amount, min_stock, max_stock, product_id, warehouse_id, location) VALUES (?,?,?,?,?,?)";

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
				preparedStmt.setString(6, productLocation);

				preparedStmt.executeUpdate();

				long id = -1;

				ResultSet rs = preparedStmt.getGeneratedKeys();
	            if (rs.next()) {
	                id = rs.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating stock product failed, no ID obtained.");
	            }
				stockProducts.add(new StockProduct(id, 0, minStock, maxStock, null, warehouse.getId(), productLocation));
			}

		} catch(SQLException e) {
			throw e;
		}
	}

	@Override
	public StockProduct getStockProductByProductId(long productId, long warehouseId) throws SQLException {
		StockProduct stockProduct = null;
		String sqlProduct = "SELECT * FROM StockProduct WHERE product_id = ? AND warehouse_id = ?";

		Connection con = DBConnection.getInstance().getConnection();

		try {
				PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
				preparedStmt.setLong(1, productId);
				preparedStmt.setLong(2, warehouseId);

				ResultSet rsStockProduct = preparedStmt.executeQuery();

				if (rsStockProduct.next()) {
					stockProduct = buildStockProduct(rsStockProduct);

					Product product = getProductById(productId);
					stockProduct.setProduct(product);
				}
				con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}

		return stockProduct;
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
	
	private ProductView buildProductForView(ResultSet rsProduct) throws SQLException{
		return new ProductView(rsProduct.getLong("stock_product_id"), 
							   rsProduct.getLong("barcode"), 
							   rsProduct.getString("product_name"),
							   rsProduct.getString("category_name"),
							   rsProduct.getInt("amount"),
							   rsProduct.getInt("min_stock"),
							   rsProduct.getInt("max_stock"),
							   rsProduct.getString("supplier"),
							   rsProduct.getString("location")
				);
	}

	private StockProduct buildStockProduct(ResultSet rsStockProduct) throws SQLException {
		return new StockProduct(rsStockProduct.getLong("id"),
				rsStockProduct.getInt("amount"),
				rsStockProduct.getInt("min_stock"),
				rsStockProduct.getInt("max_stock"),
				null,
				rsStockProduct.getLong("warehouse_id"),
				rsStockProduct.getString("location"));
	}
}
