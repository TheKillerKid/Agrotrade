package Model.DB;

import java.security.spec.RSAKeyGenParameterSpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.DBIF.OrderIF;
import Model.Model.Lease;
import Model.Model.MessagesEnum;
import Model.Model.Order;
import Model.Model.OrderLine;
import Model.Model.OrderPageType;
import Model.Model.OrderView;
import Model.Model.Purchase;
import Model.Model.Sale;


public class OrderDB implements OrderIF {

	public InvoiceDB invoiceDb = new InvoiceDB();
	public OrderLineDB orderLineDb = new OrderLineDB();
	public WarehouseDB warehouseDb = new WarehouseDB();
	// public SaleDB saleDb = new SaleDB();

	public Order createOrder(Order order) throws SQLException {
		String sqlCreate = "INSERT INTO Orders (total_price, notes, creation_date, warehouse_id, sale_id, lease_id, purchase_id) VALUES (?,?,?,?,?,?,?)";

		double totalPrice = order.getTotalPrice();
		String note = order.getNote();
		LocalDate creationDate = order.getCreationDate();
		long warehouseId = order.getWarehouse().getId();

		Long saleId = null;
		Long leaseId = null;
		Long purchaseId = null;

		if (order instanceof Sale) {
			Sale sale = (Sale) order;
			saleId = sale.getId();
		}
		if (order instanceof Lease) {
			Lease lease = (Lease) order;
			leaseId = lease.getId();
		}
		if (order instanceof Purchase) {
			Purchase purchase = (Purchase) order;
			purchaseId = purchase.getId();
		}

		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setDouble(1, totalPrice);
			preparedStmt.setString(2, note);
			preparedStmt.setObject(3, java.sql.Date.valueOf(creationDate));
			preparedStmt.setLong(4, warehouseId);

			if (order instanceof Sale) {
				preparedStmt.setLong(5, saleId);
				preparedStmt.setNull(6, java.sql.Types.INTEGER);
				preparedStmt.setNull(7, java.sql.Types.INTEGER);
			}
			if (order instanceof Lease) {
				preparedStmt.setNull(5, java.sql.Types.INTEGER);
				preparedStmt.setLong(6, leaseId);
				preparedStmt.setNull(7, java.sql.Types.INTEGER);
			}
			if (order instanceof Purchase) {
				preparedStmt.setNull(5, java.sql.Types.INTEGER);
				preparedStmt.setNull(6, java.sql.Types.INTEGER);
				preparedStmt.setLong(7, purchaseId);
			}

			preparedStmt.executeUpdate();

			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()) {
				order.setOrderId(rs.getLong(1));
				order.setInvoice(invoiceDb.createInvoice(order.getInvoice(), rs.getLong(1)));

				ArrayList<OrderLine> ols = new ArrayList<OrderLine>();

				for (OrderLine ol : order.getOrderLines()) {
					ol.setId(orderLineDb.createOrderLine(ol, order.getOrderId(), purchaseId != null));
					ols.add(ol);
				}
				order.setOrderLines(ols);
			} else {
				throw new SQLException(MessagesEnum.DBSAVEERROR.text);
			}
		} catch (SQLException e) {
			throw e;
		}
		return order;
	}

	public Order getOrder(long id, OrderPageType type) throws SQLException {
		Order order = null;

		String sqlOrder = "";
		if(type == OrderPageType.SALE) {
			sqlOrder = "SELECT * FROM Orders WHERE sale_id = ?";
		}
		if(type == OrderPageType.LEASE) {
			sqlOrder = "SELECT * FROM Orders WHERE lease_id = ?";
		}
		if(type == OrderPageType.PURCHASE) {
			sqlOrder = "SELECT * FROM Orders WHERE purchase_id = ?";
		}

		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlOrder);
			
			preparedStmt.setLong(1, id);
			
			ResultSet rs = preparedStmt.executeQuery();

			if (rs.next()) {
				order = buildOrder(rs);
				order.setOrderLines(orderLineDb.getOrderLineList(rs.getLong("id"), rs.getLong("warehouse_id")));
				order.setWarehouse(warehouseDb.getWarehouse(rs.getLong("warehouse_id")));
				order.setInvoice(invoiceDb.getInvoice(rs.getLong("id")));
			}

		} catch (SQLException e) {
			throw e;
		}

		return order;
	}

	public ArrayList<OrderView> getOrderList(OrderPageType type) throws SQLException {
		ArrayList<OrderView> res = new ArrayList<OrderView>();
		String sqlOrder = "SELECT * FROM OrderView";

		Connection con = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlOrder);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				res.add(buildOrderView(rs));
			}
		} catch (SQLException e) {
			throw e;
		}

		return res;
	}

	private Order buildOrder(ResultSet rs) throws SQLException {
		Long saleId = (Long) rs.getLong("sale_id");
		Long leaseId = (Long) rs.getLong("lease_id");
		Long purchaseId = (Long) rs.getLong("purchase_id");
		Order order = null;
		if (saleId != null) {
			order = new Sale(rs.getLong("id"), rs.getDouble("total_price"), rs.getString("notes"),
					rs.getDate("creation_date").toLocalDate(), null, null, null, rs.getLong("sale_id"), null, null,
					null);
		}
		if (leaseId != null) {
			order = new Lease(rs.getLong("id"), rs.getDouble("total_price"), rs.getString("notes"),
					rs.getDate("creation_date").toLocalDate(), null, null, null, rs.getLong("lease_id"), null, null,
					null, null);
		}
		if (purchaseId != null) {
			order = new Purchase(rs.getLong("id"), rs.getDouble("total_price"), rs.getString("notes"),
					rs.getDate("creation_date").toLocalDate(), null, null, null, rs.getLong("purchase_id"), null);
		}

		return order;
	}
	
	private OrderView buildOrderView(ResultSet rs) throws SQLException {
		Date purchaseDeliveryDate = rs.getDate("purchase_delivery_date");
		Date leaseBorrowDate = rs.getDate("lease_borrow_date");
		Date leaseExpectedReturnDate = rs.getDate("lease_expected_return_date");
		Date leaseRealReturnDate = rs.getDate("lease_real_return_date");
		Date saleDeliveryDate = rs.getDate("sale_delivery_date");
		Date saleShippingDate = rs.getDate("sale_shipping_date");
		
		return new OrderView(
				rs.getLong("id"),
				rs.getDouble("total_price"),
				leaseBorrowDate != null ? leaseBorrowDate.toLocalDate() : null,
				leaseExpectedReturnDate != null ? leaseExpectedReturnDate.toLocalDate() : null,
				leaseRealReturnDate != null ? leaseRealReturnDate.toLocalDate() : null,
				rs.getString("cvr_no"),
				purchaseDeliveryDate != null ? purchaseDeliveryDate.toLocalDate() : null,
				saleDeliveryDate != null ? saleDeliveryDate.toLocalDate() : null,
				saleShippingDate != null ? saleShippingDate.toLocalDate() : null,
				rs.getLong("order_id"),
				rs.getDate("orders_creation_date").toLocalDate(),
				rs.getLong("sale_id"),
				rs.getLong("purchase_id"),
				rs.getLong("lease_id")
		);
	}
}
