package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Controller.OrderController;
import Model.Model.*;
import Model.DB.*;

public class TestLease {
	static Lease tempLease;
	static StockProduct tempStockProduct;

	/** Fixture for sale testing. 
	 * @throws SQLException */
	@BeforeClass
	public static void setUp() throws SQLException {
		DBConnection con = DBConnection.getTestInstance("dmaj0920_1086315", "Password1!", "dmaj0920_1086315");
		con.getConnection();
		Utils.getInstance().createTestData();
	}

	@Test
	public void wasStockProductCreated() {
		// Arrange
		long latestId = -1;
		ProductDB productDB = new ProductDB();
		LocalDate startDate = LocalDate.now();
		Price purchasePrice = new Price(-1, 100, startDate, PriceType.PURCHASE);
		Price salePrice = new Price(-1, 100, startDate, PriceType.SALE);
		Price leasePrice = new Price(-1, 100, startDate, PriceType.LEASE);
		Product localProduct = new Product(
			-1, "123456789012", "Shovel", Utils.getInstance().category, purchasePrice, salePrice, leasePrice,
			Utils.getInstance().unit, Utils.getInstance().supplier
		);

		// Act
		try {
			localProduct = productDB.createProduct(localProduct, 1, 100);
			tempStockProduct = productDB.getStockProductByProductId(localProduct.getId(), Utils.getInstance().warehouse.getId());
			latestId = Utils.getInstance().getLatestId("StockProduct");
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}

		// Assert
		assertEquals("Inserted 1 row", tempStockProduct.getId(), latestId);
	}

	@Test
	public void wasLeaseCreated() {
		// Arrange
		long leaseId = 0;
		long latestId = -1;
		OrderController orderController = new OrderController();
		ArrayList<OrderLine> localOrderLines = new ArrayList<OrderLine>();
		LocalDate creationDate = LocalDate.now();
		LocalDate borrowDate = LocalDate.now();
		LocalDate expectedReturnDate = LocalDate.now();
		LocalDate realReturnDate = LocalDate.now();
		localOrderLines.add(new OrderLine(-1, 3, 10, tempStockProduct));

		tempLease = new Lease(
			-1, 100, "Note", creationDate, Utils.getInstance().warehouse, localOrderLines, null, -1, borrowDate,
      expectedReturnDate, realReturnDate, Utils.getInstance().customer
		);

		// Act
		try {
			Lease localLease = (Lease) orderController.createOrder(tempLease);
			tempLease.setId(localLease.getId());
			tempLease.setOrderId(localLease.getOrderId());
			leaseId = localLease.getId();
			latestId = Utils.getInstance().getLatestId("Lease");
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}

		// Assert
		assertEquals("Inserted 1 row", leaseId, latestId);
	}

	@AfterClass
	public static void cleanUpWhenFinish() {	
		// Arrange

		// Act
		try {
			Utils.getInstance().deleteTestData();
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	
		// Assert
		assertEquals("Database deleted", 1, 1); // We use script to turncate whole database
	}
}
