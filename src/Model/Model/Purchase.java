package Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Purchase extends Order {
	private long id;
	private LocalDate shippingDate;
	
	public Purchase(long orderId, double totalPrice, String note, LocalDate creationDate, Warehouse warehouse,
			ArrayList<OrderLine> orderLines, Invoice invoice, LocalDate deliveryDate) {
		super(orderId, totalPrice, note, creationDate, warehouse, orderLines, invoice);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

}
