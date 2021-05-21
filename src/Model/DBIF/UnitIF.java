package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Unit;

public interface UnitIF {
	
	ArrayList<Unit> getUnits() throws SQLException;
}
