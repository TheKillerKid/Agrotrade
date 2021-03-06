package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.IF.PriceIF;
import Model.Model.Price;
import Model.Model.PriceType;

public class PriceDB implements PriceIF {

	@Override
	public Price getPrice(long productId, PriceType type) throws SQLException {
		
		Price price = null;
		String sqlPrice = "SELECT * FROM Price WHERE product_id = ? AND price_type = ?";
		
     Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlPrice);
			preparedStmt.setLong(1, productId);
			preparedStmt.setString(2, type.toString());

			ResultSet rsPrice = preparedStmt.executeQuery();
			if (rsPrice.next()) {
				price = buildPrice(rsPrice);
			}
		} catch (SQLException e) {
			throw e;
		}
		return price;
	}

	@Override
	public long createPrice(Price price, long productId) throws SQLException {
		
		String sqlCreate = "INSERT INTO Price (product_id, amount, start_date, price_type) VALUES (?,?,?,?)";
				
		long id;
		double amount = price.getAmount();
		LocalDate startDate = price.getStartDate();
		PriceType priceType = price.getPriceType();
		
     Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			
			preparedStmt.setLong(1, productId);
			preparedStmt.setDouble(2, amount);
			preparedStmt.setDate(3, java.sql.Date.valueOf(startDate));
			preparedStmt.setString(4, priceType.toString());
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            else {
                throw new SQLException("Creating price failed, no ID obtained.");
            }
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}

	public Price buildPrice(ResultSet rsPrice) throws SQLException{
		return new Price(rsPrice.getLong("id"),
						 rsPrice.getDouble("amount"),
						 rsPrice.getDate("start_date").toLocalDate(),
						 PriceType.valueOf(rsPrice.getString("price_type")));
	}
}
