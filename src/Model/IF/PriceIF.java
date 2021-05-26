package Model.IF;

import java.sql.SQLException;
import Model.Model.Price;
import Model.Model.PriceType;

public interface PriceIF {

	public Price getPrice(long productId, PriceType type) throws SQLException;
	
	public long createPrice(Price price, long productId) throws SQLException;
}
