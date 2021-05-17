package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.DBIF.PriceIF;
import Model.Model.Price;

public class PriceDB implements PriceIF {

	@Override
	public ArrayList<Price> getCurrentProductPrice(long productId) throws SQLException {
		
		ArrayList<Price> priceArray = new ArrayList<Price>();
		String sqlPrice = "SELECT * FROM Price WHERE product_id = '?'";
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlPrice);
			preparedStmt.setLong(1, productId);
			ResultSet rsPrice = preparedStmt.executeQuery();
			if (rsPrice.next()) {
				priceArray.add(buildPrice(rsPrice));
			}
		} catch (SQLException e) {
			throw e;
		}
		return priceArray;
	}

	@Override
	public long createPrice(Price price) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Price buildPrice(ResultSet rsPrice) throws SQLException{
		return null;
	}
}
