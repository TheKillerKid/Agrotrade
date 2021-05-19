package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Unit;

public interface UnitIF {
	Unit getUnit(long id) throws SQLException;
	
	ArrayList<Unit> getUnit() throws SQLException;
}
