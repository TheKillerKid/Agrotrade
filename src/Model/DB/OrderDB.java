package Model.DB;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.DBIF.OrderIF;
import Model.Model.Invoice;
import Model.Model.Lease;
import Model.Model.MessagesEnum;
import Model.Model.Order;
import Model.Model.OrderLine;
import Model.Model.Sale;
import Model.Model.Warehouse;

public class OrderDB implements OrderIF {
	
	public InvoiceDB invoiceDb = new InvoiceDB();
	public OrderLineDB ordeLineDb = new OrderLineDB();
	public WarehouseDB warehouseDb = new WarehouseDB();
	public SaleDB saleDb = new SaleDB();
	
	public Order createOrder(Order order) throws SQLException {
		String sqlCreate = "INSERT INTO Orders (total_price, notes, creation_date, warehouse_id, sale_id, lease_id, purchase_id) VALUES (?,?,?,?,?,?,?)";
		
		double totalPrice = order.getTotalPrice();
		String note = order.getNote();
		LocalDate creationDate = order.getCreationDate();
		long warehouseId = order.getWarehouse().getId();
		
		Long saleId = null;
		Long leaseId = null;
		Long purchaseId = null;
		
		if(order instanceof Sale) {
			Sale sale = (Sale)order;
			saleId = sale.getId();
		}	
		if(order instanceof Lease) {
			Lease lease = (Lease)order;
			leaseId = lease.getId();
		}
		
		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setDouble(1, totalPrice);
			preparedStmt.setString(2, note);
			preparedStmt.setObject(3, java.sql.Date.valueOf(creationDate));
			preparedStmt.setLong(4, warehouseId);
			
			if(order instanceof Sale) {
				preparedStmt.setLong(5, saleId);
				preparedStmt.setNull(6, java.sql.Types.INTEGER);
				preparedStmt.setNull(7, java.sql.Types.INTEGER);
			}
			if(order instanceof Lease) {
				preparedStmt.setNull(5, java.sql.Types.INTEGER);
				preparedStmt.setLong(6, leaseId);
				preparedStmt.setNull(7, java.sql.Types.INTEGER);
			}

			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
            	order.setOrderId(rs.getLong(1));
    			order.setInvoice(invoiceDb.createInvoice(order.getInvoice(), rs.getLong(1)));

    			ArrayList<OrderLine> ols = new ArrayList<OrderLine>(); 
    			
    			for (OrderLine ol : order.getOrderLines()) {
    				ol.setId(ordeLineDb.createOrderLine(ol, order.getOrderId()));
    				ols.add(ol);
    			}
    			order.setOrderLines(ols);
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch(SQLException e) {
			throw e;
		}
		return order;
	}
	
	public ArrayList<Order> getOrderList () throws SQLException {
		ArrayList<Order> res = new ArrayList<Order>();
		String sqlOrder = "SELECT * FROM Orders";
		
		
		Connection con = DBConnection.getInstance().getConnection();
		

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlOrder);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {

				if ((Long) rs.getLong("sale_id") != null) {
					Sale sale = saleDb.getSale(rs.getLong("sale_id"));
					sale.setOrderId(rs.getLong("id"));
					sale.setTotalPrice(rs.getDouble("total_price"));
					sale.setNote(rs.getString("note"));
					sale.setCreationDate(rs.getDate("creation_date").toLocalDate());
					sale.setWarehouse(warehouseDb.getWarehouse(rs.getLong("warehouse_id")));
					sale.setOrderLines(null);
					sale.setInvoice(null);

					res.add(sale);  
				}

				if ((Long) rs.getLong("lease_id") != null) {
					
				}

				if ((Long) rs.getLong("purchase_id") != null) {
				
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		
		
		return res;
	}
}
