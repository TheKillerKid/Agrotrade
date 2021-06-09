package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.*;

import Model.DB.*;
import Model.Model.*;

public class TestDatabaseAccess {
	
	static DBConnection con = null;
	static Customer tempCustomer;

	@BeforeClass 
	public static void beforeAll() throws SQLException, InterruptedException {
		con = DBConnection.getTestInstance("dmaj0920_1086315", "Password1!", "dmaj0920_1086315");
		Utils.getInstance().deleteTestData();
		Utils.getInstance().createTestData();
	}

	/** Fixture for database access testing. 
	 * @throws SQLException
	 * */
	@Before
	public void setUp() throws SQLException {
		con = DBConnection.getTestInstance("dmaj0920_1086315", "Password1!", "dmaj0920_1086315");
	}

	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);

		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);

		con = DBConnection.getTestInstance("dmaj0920_1086315", "Password1!", "dmaj0920_1086315");
		assertNotNull("Connected - connection cannot be null", con);		
	}
	
	
	@Test
	public void wasInsertedCustomer() {
		// Arrange
		CustomerDB customerDB = new CustomerDB();
		long insertedCustomerId = -1;
		long latestCustomerId = 0;
		tempCustomer = new Customer(-1, "John", "Doe", null, "+451 912 345 678", "john@doe.com", "87654321", 0.0);

		// Act
		try {
			Address address = Utils.getInstance().address;
			tempCustomer.setAddress(address);
			tempCustomer = customerDB.createCustomer(tempCustomer);
			insertedCustomerId = tempCustomer.getId();
			latestCustomerId = Utils.getInstance().getLatestId("Customer");
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}

		// Assert
		assertEquals("One row inserted", insertedCustomerId, latestCustomerId);
	}

	@Test
	public void wasRetrievedCustomer() {
		// Arrange
		CustomerDB customerDB = new CustomerDB();
		long retreivedId = -1;
		long insertedId = tempCustomer.getId();
		
		// Act
		try {
			Customer retreivedCustomer = customerDB.getCustomerById(insertedId);
			con.getConnection().commit();
			retreivedId = retreivedCustomer.getId();
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}

		// Assert
		assertEquals("Retrived customer", insertedId, retreivedId);
	}

	/** Fixture for customer testing. */
	
	@AfterClass
	public static void cleanUpWhenFinish() {	
		DBConnection.closeConnection();
	}
}
