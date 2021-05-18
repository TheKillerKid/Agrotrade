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
			 sqlWarehouse = ("SELECT * FROM StockProduct WHERE product_id = ?");
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

	
	
	private StockProduct buildStockProduct(ResultSet rsStockProduct) throws SQLException {
		return new StockProduct(rsStockProduct.getLong("id"),
				rsStockProduct.getInt("amount"),
				rsStockProduct.getInt("min_stock"),
				rsStockProduct.getInt("max_stock"),
				null,
				rsStockProduct.getLong("warehouse_id"));
	}

}
