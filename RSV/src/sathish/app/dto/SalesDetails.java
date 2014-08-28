/**
 * 
 */
package sathish.app.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author ps
 * 
 */
@Entity
@Table(name = "SALES_DETAILS")
public class SalesDetails {

	public SalesDetails() {

	}	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SALES_ID")	
	private long salesId;
	@Column(name = "SALES_QUANTITY")
	private int quantity;
	@Column(name = "SALES_UNIT_RATE")
	private float unitRate;
	@Column(name = "SALES_AMOUNT")
	private float amount;
	@Column(name = "PRODUCT_ID")
	private int productId;
	
	@ManyToOne
	@JoinColumn(name="SALES_ENTRY_ID")
	private SalesEntry salesEntry;
	
	@OneToOne
	@JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
	private ProductDetails productDetails;
	

	public SalesEntry getSalesEntry() {
		return salesEntry;
	}
	public void setSalesEntry(SalesEntry salesEntry) {
		this.salesEntry = salesEntry;
	}
	public long getSalesId() {
		return salesId;
	}
	public void setSalesId(long salesId) {
		this.salesId = salesId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	public float getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(float unitRate) {
		this.unitRate = unitRate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	
}