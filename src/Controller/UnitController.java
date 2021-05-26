package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.UnitDB;
import Model.IF.UnitIF;
import Model.Model.Unit;

public class UnitController {

	private UnitIF unitDb = new UnitDB();
	
	public ArrayList<Unit> getUnits() throws SQLException{
		try {
			return unitDb.getUnits();
		} catch (SQLException e) {
			throw e;
		}
	}
}
