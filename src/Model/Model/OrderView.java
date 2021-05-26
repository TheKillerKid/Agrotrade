package Model.Model;

import java.time.LocalDate;

public class OrderView {
	private long orderLineId;
	private double totalPrice;
	private LocalDate leaseBorrowDate;
	private LocalDate leaseExpectedReturnDate;
	private LocalDate leaseRealReturnDate;
	private String cvrNo;
	private LocalDate purchaseDeliveryDate;
	private LocalDate saleDeliveryDate;
	private LocalDate saleShippingDate;
	private long orderId;
	private LocalDate orderCreationDate;
	private long saleId;
	private long purchaseId;
	private long leaseId;


	public OrderView(
			long orderLineId, double totalPrice, LocalDate leaseBorrowDate, LocalDate leaseExpectedReturnDate, LocalDate leaseRealReturnDate,
			String cvrNo, LocalDate purchaseDeliveryDate, LocalDate saleDeliveryDate, LocalDate saleShippingDate, long orderId, LocalDate orderCreationDate,
			long saleId, long purchaseId, long leaseId
	) {
		this.setOrderId(orderId);
		this.setOrderLineId(orderLineId);
		this.setTotalPrice(totalPrice);
		this.setLeaseBorrowDate(leaseBorrowDate);
		this.setLeaseExpectedReturnDate(leaseExpectedReturnDate);
		this.setLeaseRealReturnDate(leaseRealReturnDate);
		this.setCvrNo(cvrNo);
		this.setPurchaseDeliveryDate(purchaseDeliveryDate);
		this.setSaleDeliveryDate(saleDeliveryDate);
		this.setSaleShippingDate(saleShippingDate);
		this.setOrderCreationDate(orderCreationDate);
		this.setSaleId(saleId);
		this.setPurchaseId(purchaseId);
		this.setLeaseId(leaseId);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(long orderLineId) {
		this.orderLineId = orderLineId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getLeaseBorrowDate() {
		return leaseBorrowDate;
	}

	public void setLeaseBorrowDate(LocalDate leaseBorrowDate) {
		this.leaseBorrowDate = leaseBorrowDate;
	}

	public LocalDate getLeaseExpectedReturnDate() {
		return leaseExpectedReturnDate;
	}

	public void setLeaseExpectedReturnDate(LocalDate leaseExpectedReturnDate) {
		this.leaseExpectedReturnDate = leaseExpectedReturnDate;
	}

	public LocalDate getLeaseRealReturnDate() {
		return leaseRealReturnDate;
	}

	public void setLeaseRealReturnDate(LocalDate leaseRealReturnDate) {
		this.leaseRealReturnDate = leaseRealReturnDate;
	}

	public String getCvrNo() {
		return cvrNo;
	}

	public void setCvrNo(String cvrNo) {
		this.cvrNo = cvrNo;
	}

	public LocalDate getPurchaseDeliveryDate() {
		return purchaseDeliveryDate;
	}

	public void setPurchaseDeliveryDate(LocalDate purchaseDeliveryDate) {
		this.purchaseDeliveryDate = purchaseDeliveryDate;
	}

	public LocalDate getSaleDeliveryDate() {
		return saleDeliveryDate;
	}

	public void setSaleDeliveryDate(LocalDate saleDeliveryDate) {
		this.saleDeliveryDate = saleDeliveryDate;
	}

	public LocalDate getSaleShippingDate() {
		return saleShippingDate;
	}

	public void setSaleShippingDate(LocalDate saleShippingDate) {
		this.saleShippingDate = saleShippingDate;
	}

	public LocalDate getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(LocalDate orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	public long getSaleId() {
		return saleId;
	}

	public void setSaleId(long saleId) {
		this.saleId = saleId;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public long getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(long leaseId) {
		this.leaseId = leaseId;
	}
}
