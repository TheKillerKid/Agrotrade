package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.IF.StockProductIF;
import Model.Model.Product;
import Model.Model.StockProduct;

public class StockProductDB implements StockProductIF {
	private ProductDB productDb = new ProductDB();

	@Override
	public ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException {

		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();

		String sqlWarehouse = "";
		if(warehouseId != 0) {
			 sqlWarehouse = ("SELECT * FROM StockProduct WHERE warehouse_id = ?");
		}
		else {
			sqlWarehouse = ("SELECT * FROM StockProduct");
		}

		Connection con = DBConnection.getInstance().getConnection();

		try {
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
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return stockProducts;
	}

	@Override
	public StockProduct getStockProduct(long id, long warehouseId) throws SQLException {
		StockProduct stockProduct = null;
		String sqlProduct = "SELECT * FROM StockProduct WHERE id = ? AND warehouse_id = ?";

		Connection con = DBConnection.getInstance().getConnection();

		try {
				PreparedStatement preparedStmt = con.prepareStatement(sqlProduct);
				preparedStmt.setLong(1, id);
				preparedStmt.setLong(2, warehouseId);

				ResultSet rsStockProduct = preparedStmt.executeQuery();

				if (rsStockProduct.next()) {
					stockProduct = buildStockProduct(rsStockProduct);

					Product product = productDb.getProductById(rsStockProduct.getLong("product_id"));
					stockProduct.setProduct(product);
				}
				con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}

		return stockProduct;
	}

	@Override
	public void sellOrLeaseStockProduct(long stockProductId, int amount, long warehouseId) throws SQLException {
		String sqlUpdate = "UPDATE StockProduct SET amount = ? WHERE id = ? AND warehouse_id = ?";

		StockProduct stockProduct = getStockProduct(stockProductId, warehouseId);
		amount = stockProduct.getAmount() - amount;

	    Connection con = DBConnection.getInstance().getConnection();

	    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlUpdate);
			preparedStmt.setLong(1, amount);
			preparedStmt.setLong(2, stockProductId);
			preparedStmt.setLong(3, warehouseId);

			preparedStmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void returnLeaseOrPurchaseStockProduct(long stockProductId, int amount, long warehouseId) throws SQLException {
		String sqlUpdate = "UPDATE StockProduct SET amount = ? WHERE id = ? AND warehouse_id = ?";

		StockProduct stockProduct = getStockProduct(stockProductId, warehouseId);
		amount = stockProduct.getAmount() + amount;

	    Connection con = DBConnection.getInstance().getConnection();

	    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlUpdate);
			preparedStmt.setLong(1, amount);
			preparedStmt.setLong(2, stockProductId);
			preparedStmt.setLong(3, warehouseId);

			preparedStmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		}
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
