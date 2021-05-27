package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Model.DB.*;
import Model.Model.*;

class Utils {
    private static Utils instance = null;
	private UnitDB unitDB = new UnitDB();
	private CategoryDB categoryDB = new CategoryDB();
	private SupplierDB supplierDB = new SupplierDB();
	private CustomerDB customerDB = new CustomerDB();
	private EmployeeDB employeeDB = new EmployeeDB();
	private AddressDB addressDB = new AddressDB();
	private WarehouseDB warehouseDB = new WarehouseDB();
	public Customer customer;
	public Employee employee;
	public Address address;
	public Warehouse warehouse;
	public Supplier supplier;
	public Unit unit;
	public Category category;

	public static Utils getInstance() {
        if (instance == null)
        	instance = new Utils();
  
        return instance;
    }
	
	public void createAddress() throws SQLException {
		address = new Address(-1, "Street", "1", "Poprad", "059 51", "Slovakia");

		long id = addressDB.createAddress(address);
		address.setId(id);
	}
	
	public void createWarehouse() throws SQLException {
		warehouse = new Warehouse(-1, address);
		warehouse = warehouseDB.createWarehouse(warehouse);
	}
	
	public void createCustomer() throws SQLException {
		customer = new Customer(-1, "John", "Doe", address, "+451 912 345 678", "john@doe.com", "12345678", 0.0);
		customer = customerDB.createCustomer(customer);
	}

	public void createEmployee() throws SQLException {
		LocalDate dateOfBirth = LocalDate.of(1999, 4, 20);

		employee = new Employee(-1, "Hugh", "Mungus", dateOfBirth, address, "+451 912 345 673", "hugh@mungus.com", "admin", "1234567890", DepartmentType.WAREHOUSE, PositionType.ADMIN, warehouse);
		employee = employeeDB.createEmployee(employee);
	}
	
	public void createSupplier() throws SQLException {
		supplier = new Supplier(-1, "Mathew", "Smith", address, "+421 943 333 222", "mathew@smith.com", "53634632", "Mathew supplies");
		supplier = supplierDB.createSupplier(supplier);
	}
	
	public void createUnit() throws SQLException {
		unit = new Unit(-1, "pcs");
		unit = unitDB.createUnit(unit);
	}

	public void createCategory() throws SQLException {
		category = new Category(-1, "Tools");
		category = categoryDB.createCategory(category);
	}

	public void createTestData () throws SQLException {
		createAddress();
		createWarehouse();
		createCustomer();
		createEmployee();
		createSupplier();
		createCategory();
		createUnit();
	}
	
	public void deleteTestData() throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		String deleteSQL = 
					"DELETE FROM Invoice " +
					"DBCC CHECKIDENT ('Invoice',RESEED, 0);" +
					"DELETE FROM OrderLine " +
					"DBCC CHECKIDENT ('OrderLine',RESEED, 0);" +
					"DELETE FROM Orders " +
					"DBCC CHECKIDENT ('Orders',RESEED, 0);" +
					"DELETE FROM Lease " +
					"DBCC CHECKIDENT ('Lease',RESEED, 0);" +
					"DELETE FROM Sale " +
					"DBCC CHECKIDENT ('Sale',RESEED, 0);" +
					"DELETE FROM Purchase " +
					"DBCC CHECKIDENT ('Purchase',RESEED, 0);" +
					"DELETE FROM Price " +
					"DBCC CHECKIDENT ('Price',RESEED, 0);" +
					"DELETE FROM Product " +
					"DBCC CHECKIDENT ('Product',RESEED, 0);" +
					"DELETE FROM StockProduct " +
					"DBCC CHECKIDENT ('StockProduct',RESEED, 0);" +
					"DELETE FROM Sale " +
					"DBCC CHECKIDENT ('Sale',RESEED, 0);" +
					"DELETE FROM Customer " +
					"DBCC CHECKIDENT ('Customer',RESEED, 0);" +
					"DELETE FROM Employee " +
					"DBCC CHECKIDENT ('Employee',RESEED, 0);" +
					"DELETE FROM Supplier " +
					"DBCC CHECKIDENT ('Supplier',RESEED, 0);" +
					"DELETE FROM Unit " +
					"DBCC CHECKIDENT ('Unit',RESEED, 0);" +
					"DELETE FROM Category " +
					"DBCC CHECKIDENT ('Category',RESEED, 0);" +
					"DELETE FROM Warehouse " +
					"DBCC CHECKIDENT ('Warehouse',RESEED, 0);" +
					"DELETE FROM Address " +
					"DBCC CHECKIDENT ('Address',RESEED, 0);" +
					"DELETE FROM City " +
					"DBCC CHECKIDENT ('City',RESEED, 0);" +
					"DELETE FROM Country " +
					"DBCC CHECKIDENT ('Country',RESEED, 0);";

		PreparedStatement preparedStmt = con.prepareStatement(deleteSQL);

		preparedStmt.execute();
	}

	public int getLatestId(String tableName) throws SQLException {
		int latestID = 0;

		String sqlGet = String.format("SELECT TOP 1 %s.id FROM %s ORDER BY id DESC", tableName, tableName);

		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlGet);
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				latestID = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return latestID;
	}
}
