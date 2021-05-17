package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DBIF.WarehouseIF;
import Model.Model.Warehouse;

public class WarehouseDB implements WarehouseIF {
	
	private AddressDB addressDb = new AddressDB();

	@Override
	public Warehouse getWarehouse(long id) throws SQLException {
		Warehouse res = null;
		String sqlWarehouse = ("SELECT * FROM Warehouse WHERE id = ?" );
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlWarehouse);
				
				preparedStmt.setLong(1, id);
				
				ResultSet rsWarehouse = preparedStmt.executeQuery();
			
			if(rsWarehouse.next()) {
				res = buildWarehouse(rsWarehouse);
				res.setAddress(addressDb.getAddress(rsWarehouse.getLong("address_id")));
			}

		} catch (SQLException e) {
			throw e;
		}
		return res;
	}
	
	@Override
	public ArrayList<Warehouse> getWarehouses() throws SQLException {
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
		String sqlWarehouse = ("SELECT * FROM Warehouse" );
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
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
	
	private Warehouse buildWarehouse(ResultSet rsWarehouse) throws SQLException {
		return new Warehouse(rsWarehouse.getLong("id"), null);
	}

}
