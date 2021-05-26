package Model.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.Model.Purchase;
import Model.Model.Sale;
import Model.IF.PurchaseIF;
import Model.Model.Lease;
import Model.Model.MessagesEnum;
import Model.Model.OrderLine;
import Model.Model.OrderPageType;

public class PurchaseDB implements PurchaseIF{
	private OrderDB orderDb = new OrderDB();
	private StockProductDB stockProductDb = new StockProductDB();
	
	@Override
	public Purchase createPurchase(Purchase purchase) throws Exception {
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
	
	@Override
	public Purchase getPurchase(long id) throws SQLException {
		Purchase res = null;
		
		String sqlPurchase = "SELECT * FROM Purchase WHERE id = ?";
		
	    Connection con = DBConnection.getInstance().getConnection();
	    
	    try {	    	
	    	PreparedStatement preparedStmt = con.prepareStatement(sqlPurchase);	  
	    	
	    	preparedStmt.setLong(1, id);
	    	
	    	ResultSet rs = preparedStmt.executeQuery();
	    	
	    	if (rs.next()) {
	    		Purchase purchase = buildPurchase(rs);
	    		
	    		purchase.setOrder(orderDb.getOrder(purchase.getId(), OrderPageType.PURCHASE));
	    		
	    		res = purchase;
	    	}
	    } catch (SQLException e) {
	    	throw e;
	    }
	    return res;
	}

	@Override
	public void setAsReceived(Purchase purchase) throws Exception {
		String sql = "UPDATE Purchase SET delivery_date = ? WHERE id = ?";

		LocalDate deliveryDate = LocalDate.now();
		
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, deliveryDate != null ? java.sql.Date.valueOf(deliveryDate) : null);
			preparedStmt.setLong(2, purchase.getId());
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (!rs.next()) {
            	throw new Exception(MessagesEnum.DBUPDATEERROR.text);
            }
            else {
            	for(OrderLine ol : purchase.getOrderLines()) {
            		stockProductDb.returnLeaseOrPurchaseStockProduct(ol.getStockProduct().getId(), ol.getAmount(), ol.getStockProduct().getWarehouseId());
            	}
            }
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Purchase buildPurchase(ResultSet rs) throws SQLException {
		Date sqlDeliveryDate = rs.getDate("delivery_date");
		return new Purchase(
				-1,
				-1,
				null,
				null,
				null,
				null,
				null,
				rs.getLong("id"),
				sqlDeliveryDate != null ? sqlDeliveryDate.toLocalDate() : null
		);
	}
}
