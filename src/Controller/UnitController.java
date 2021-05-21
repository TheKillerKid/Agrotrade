package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.UnitDB;
import Model.Model.Unit;

public class UnitController {

	private UnitDB unitDb = new UnitDB();
	
	public ArrayList<Unit> getUnits() throws SQLException{
		try {
			return unitDb.getUnits();
		} catch (SQLException e) {
			throw e;
		}
	}
}
