package Model.IF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Warehouse;

public interface WarehouseIF {
	Warehouse getWarehouse(long id) throws SQLException;
	
	ArrayList<Warehouse> getWarehouses() throws SQLException;
}
