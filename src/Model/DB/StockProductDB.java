package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DBIF.StockProductIF;
import Model.Model.StockProduct;
import Model.Model.Warehouse;

public class StockProductDB implements StockProductIF{
	
	private WarehouseDB warehouseDb = new WarehouseDB();
	private ProductDB productDb = new ProductDB();

	@Override
	public ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException {
		
		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
		
		String sqlWarehouse = "";
		if(warehouseId != 0) {
			 sqlWarehouse = ("SELECT * FROM StockProduct WHERE product_id = ?" );
		}
		else {
			sqlWarehouse = ("SELECT * FROM StockProduct");
		}
		
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlWarehouse);
			
			if(warehouseId != 0) {
				preparedStmt.setLong(1, warehouseId);
			}
			
			ResultSet rsStockProduct = preparedStmt.executeQuery();
			
			while(rsStockProduct.next()) {
				StockProduct res = buildStockProduct(rsStockProduct);
				res.setProduct(productDb.getProductById(rsStockProduct.getLong("product_id")));
				stockProducts.add(res);
			}

		} catch (SQLException e) {
			throw e;
		}
		return stockProducts;
	}

	@Override
	public ArrayList<StockProduct> createStockProducts(long productId, int minStock, int maxStock) throws SQLException {

		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
		
		String sqlCreate = "INSERT INTO StockProducts (amount, min_stock, max_stock, product_id, warehouse_id) VALUES (?,?,?,?,?)";
		
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
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
	
	private StockProduct buildStockProduct(ResultSet rsStockProduct) throws SQLException {
		return new StockProduct(rsStockProduct.getLong("id"),
				rsStockProduct.getInt("amount"),
				rsStockProduct.getInt("min_stock"),
				rsStockProduct.getInt("max_stock"),
				null,
				rsStockProduct.getLong("warehouse_id"));
	}

}
