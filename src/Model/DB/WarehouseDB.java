package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.IF.WarehouseIF;
import Model.Model.Address;
import Model.Model.MessagesEnum;
import Model.Model.Warehouse;

public class WarehouseDB implements WarehouseIF {
	
	private AddressDB addressDb = new AddressDB();

	@Override
	public Warehouse getWarehouse(long id) throws SQLException {
		Warehouse res = null;
		long addressId = -1;
		String sqlWarehouse = ("SELECT * FROM Warehouse WHERE id = ?" );

		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlWarehouse);
	
			preparedStmt.setLong(1, id);
	
			ResultSet rsWarehouse = preparedStmt.executeQuery();
	
			if(rsWarehouse.next()) {
				res = buildWarehouse(rsWarehouse);
				addressId = rsWarehouse.getLong("address_id");		
			}
		} catch (SQLException e) {
			throw e;
		}

		Address address = addressDb.getAddress(addressId);
		res.setAddress(address);

		return res;
	}
	
	@Override
	public ArrayList<Warehouse> getWarehouses() throws SQLException {
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		String sqlWarehouse = ("SELECT * FROM Warehouse" );

		Connection con = DBConnection.getInstance().getConnection();

    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlWarehouse);

			ResultSet rsWarehouse = preparedStmt.executeQuery();

			while(rsWarehouse.next()) {
				Warehouse res = buildWarehouse(rsWarehouse);
				res.setAddress(addressDb.getAddress(rsWarehouse.getLong("address_id")));
				warehouses.add(res);
			}

		} catch (SQLException e) {
			throw e;
		}
		return warehouses;
	}
	
	public Warehouse createWarehouse(Warehouse warehouse) throws SQLException {
		String sqlCreate = "INSERT INTO Warehouse (address_id) VALUES (?)";
		
	    Connection con = DBConnection.getInstance().getConnection();
	
	    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
	
			preparedStmt.setLong(1, warehouse.getAddress().getId());

			preparedStmt.executeUpdate();						
			ResultSet rs = preparedStmt.getGeneratedKeys();

            if (rs.next()) {
            	warehouse.setId(rs.getLong(1));
            }
            else {
            	throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
            
		} catch (SQLException e) {
			throw e;
		}

		return warehouse;
	}
	
	private Warehouse buildWarehouse(ResultSet rsWarehouse) throws SQLException {
		return new Warehouse(rsWarehouse.getLong("id"), null);
	}

}
