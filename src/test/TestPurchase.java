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

public class TestPurchase {
	static Purchase tempPurchase;
	static StockProduct tempStockProduct;

	/** Fixture for sale testing. 
	 * @throws SQLException 
	 * @throws InterruptedException
	 * */
	@BeforeClass
	public static void setUp() throws SQLException, InterruptedException {
		DBConnection con = DBConnection.getTestInstance("dmaj0920_1086315", "Password1!", "dmaj0920_1086315");
		System.out.println(con.getConnection().getMetaData());
		
		Utils.getInstance().deleteTestData();
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
			localProduct = productDB.createProduct(localProduct, 1, 100, "4F");
			tempStockProduct = productDB.getStockProductByProductId(localProduct.getId(), Utils.getInstance().warehouse.getId());
			latestId = Utils.getInstance().getLatestId("StockProduct");
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}

		// Assert
		assertEquals("Inserted 1 row", tempStockProduct.getId(), latestId);
	}

	@Test
	public void wasPurchaseCreated() {
		// Arrange
		long purchaseId = 0;
		long latestId = -1;
		OrderController orderController = new OrderController();
		ArrayList<OrderLine> localOrderLines = new ArrayList<OrderLine>();
		LocalDate creationDate = LocalDate.now();
		LocalDate shippingDate = LocalDate.now();
		localOrderLines.add(new OrderLine(-1, 3, 10, tempStockProduct));

		tempPurchase = new Purchase(
			0, 100, "Note", creationDate, Utils.getInstance().warehouse, localOrderLines, null, -1, shippingDate
		);

		// Act
		try {
			Purchase localPurchase = (Purchase) orderController.createOrder(tempPurchase);
			tempPurchase.setId(localPurchase.getId());
			tempPurchase.setOrderId(localPurchase.getOrderId());
			purchaseId = localPurchase.getId();
			latestId = Utils.getInstance().getLatestId("Purchase");
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}

		// Assert
		assertEquals("Inserted 1 row", purchaseId, latestId);
	}

	@AfterClass
	public static void cleanUpWhenFinish() {	
			DBConnection.closeConnection();
	}
}
