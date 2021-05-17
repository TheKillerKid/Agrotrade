package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Model.Price;

public interface PriceIF {

	public ArrayList<Price> getCurrentProductPrice(long productId) throws SQLException;
	
	public long createPrice(Price price) throws SQLException;
}
