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
 * @author root
 * 
 */
@Entity
@Table(name = "PURCHASE_DETAILS")
public class PurchaseDetails {

	public PurchaseDetails() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "PURCHASE_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long purchaseDetailId;
	@Column(name = "SERIAL_NO_FROM")
	private long serialNoFrom;
	@Column(name = "SERIAL_NO_TO")
	private long serialNoTo;
	@Column(name = "QUANTITY")
	private int purchseQuantity;
	@Column(name = "UNIT_RATE")
	private float unitRate;
	@Column(name = "AMOUNT")
	private float amount;
	@Column(name = "PRODUCT_ID")
	private int productId;


	@ManyToOne
	@JoinColumn(name = "PURCHASE_ID")
	private PurchaseEntry purchaseEntry;

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
	private ProductDetails productDetails;

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public PurchaseEntry getPurchaseEntry() {
		return purchaseEntry;
	}

	public void setPurchaseEntry(PurchaseEntry purchaseEntry) {
		this.purchaseEntry = purchaseEntry;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public long getPurchaseDetailId() {
		return purchaseDetailId;
	}

	public void setPurchaseDetailId(long purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}
	public long getSerialNoFrom() {
		return serialNoFrom;
	}

	public void setSerialNoFrom(long serialNoFrom) {
		this.serialNoFrom = serialNoFrom;
	}

	public long getSerialNoTo() {
		return serialNoTo;
	}

	public void setSerialNoTo(long serialNoTo) {
		this.serialNoTo = serialNoTo;
	}

	public int getPurchseQuantity() {
		return purchseQuantity;
	}

	public void setPurchseQuantity(int purchseQuantity) {
		this.purchseQuantity = purchseQuantity;
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

}
