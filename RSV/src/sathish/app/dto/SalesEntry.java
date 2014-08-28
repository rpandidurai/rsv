/**
 * 
 */
package sathish.app.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

/**
 * @author root
 * 
 */
@Entity
@Table(name = "SALES_ENTRY")
public class SalesEntry {

	public SalesEntry() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SALES_ENTRY_ID")
	private long salesEntryId;
	@Column(name = "BILL_NO", unique = true)
	private String billNo;
	@Column(name = "SALES_ENTRY_DATE")
	@Type(type = "date")
	private Date entryDate = new Date();
	@Column(name = "TOTAL_QUANTITY")
	private double totalQuantity;
	@Column(name = "TOTAL_AMOUNT")
	private double totalAmount;
	@Column(name = "CUSTOMER_ID")
	private int customerId;
	@Column(name = "DELIVERY_ID", nullable = false)
	private int deliveryId;

	@OneToOne
	@JoinColumn(name = "DELIVERY_ID", insertable = false, updatable = false)
	private DeliveryBoys deliveryBoys;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID", insertable = false, updatable = false)
	private CustomerDetails customerDetails;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "SALES_ENTRY_ID")
	@Fetch(FetchMode.SUBSELECT)
	private Collection<SalesDetails> salesDetailsList = new ArrayList<SalesDetails>();

	public long getSalesEntryId() {
		return salesEntryId;
	}

	public void setSalesEntryId(long salesEntryId) {
		this.salesEntryId = salesEntryId;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Collection<SalesDetails> getSalesDetailsList() {
		return salesDetailsList;
	}

	public void setSalesDetailsList(Collection<SalesDetails> salesDetailsList) {
		this.salesDetailsList = salesDetailsList;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public DeliveryBoys getDeliveryBoys() {
		return deliveryBoys;
	}

	public void setDeliveryBoys(DeliveryBoys deliveryBoys) {
		this.deliveryBoys = deliveryBoys;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	

}
