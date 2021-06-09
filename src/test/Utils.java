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
		AddressDB addressDB = new AddressDB();
		address = new Address(-1, "Street", "1", "Poprad", "059 51", "Slovakia");

		long id = addressDB.createAddress(address);
		address.setId(id);
	}

	public void createWarehouse() throws SQLException {
		WarehouseDB warehouseDB = new WarehouseDB();

		warehouse = new Warehouse(-1, address);
		warehouse = warehouseDB.createWarehouse(warehouse);
	}

	public void createCustomer() throws SQLException {
		CustomerDB customerDB = new CustomerDB();

		customer = new Customer(-1, "Jane", "Drew", address, "+451 912 345 679", "jane@drew.com", "12345678", 0.0);
		customer = customerDB.createCustomer(customer);
	}

	public void createEmployee() throws SQLException {
		EmployeeDB employeeDB = new EmployeeDB();
		LocalDate dateOfBirth = LocalDate.of(1999, 4, 20);

		employee = new Employee(-1, "Hugh", "Mungus", dateOfBirth, address, "+451 912 345 673", "hugh@mungus.com", "admin", "1234567890", DepartmentType.WAREHOUSE, PositionType.ADMIN, warehouse);
		employee = employeeDB.createEmployee(employee);
	}

	public void createSupplier() throws SQLException {
		SupplierDB supplierDB = new SupplierDB();
		
		supplier = new Supplier(-1, "Mathew", "Smith", address, "+421 943 333 222", "mathew@smith.com", "53634632", "Mathew supplies");
		supplier = supplierDB.createSupplier(supplier);
	}

	public void createUnit() throws SQLException {
		UnitDB unitDB = new UnitDB();
		unit = new Unit(-1, "pcs");
		unit = unitDB.createUnit(unit);
	}

	public void createCategory() throws SQLException {
		CategoryDB categoryDB = new CategoryDB();
		category = new Category(-1, "Tools");
		category = categoryDB.createCategory(category);
	}

	public void createTestData () throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();

		createAddress();
		createWarehouse();
		createCustomer();
		createEmployee();
		createSupplier();
		createCategory();
		createUnit();
		con.commit();
	}
	
	public void deleteTestData() throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();

		String deleteSQL = 
					"EXEC sp_MSForEachTable 'ALTER TABLE ? NOCHECK CONSTRAINT all';" +
					"EXEC sp_MSForEachTable 'DELETE FROM ?';" +
					"EXEC sp_MSForEachTable 'ALTER TABLE ? WITH CHECK CHECK CONSTRAINT all';";
		try {
			PreparedStatement preparedStmt = con.prepareStatement(deleteSQL);
			preparedStmt.executeUpdate();
			con.commit();

			customer = null;
			employee = null;
			address = null;
			warehouse = null;
			supplier = null;
			unit = null;
			category = null;
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public int getLatestId(String tableName) throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		int latestID = 0;

		String sqlGet = String.format("SELECT TOP 1 %s.id FROM %s ORDER BY id DESC", tableName, tableName);

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlGet);
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				latestID = rs.getInt(1);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return latestID;
	}
}
