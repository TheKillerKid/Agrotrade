package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.Model.Purchase;
import Model.DBIF.PurchaseIF;
import Model.Model.MessagesEnum;

public class PurchaseDB implements PurchaseIF{
	private OrderDB orderDb = new OrderDB();
	
	@Override
	public Purchase createPurchase(Purchase purchase) throws SQLException {
		String sqlCreate = "INSERT INTO Purchase (delivery_date) VALUES (?)";
		
		LocalDate deliveryDate = purchase.getDeliveryDate();
			
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, deliveryDate != null ? java.sql.Date.valueOf(deliveryDate) : null);

			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                purchase.setId(rs.getLong(1)); 
    			purchase.setOrder(orderDb.createOrder(purchase));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch (SQLException e) {
			throw e;
		}
		return purchase;
	}
}
