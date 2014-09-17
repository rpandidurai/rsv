/**
 * 
 */
package sathish.app.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author root
 * 
 */
@Entity
@Table(name = "STOCK")
public class Stock {

	public Stock() {
		this.inStock = 0;
		this.lastTxnDate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STOCK_ID")
	private int stockId;
	@Column(name = "LAST_TXN_DATE", nullable = false)
	private Date lastTxnDate;
	// @Column(name = "PRODUCT_ID", nullable = false)
	// private int productId;
	@Column(name = "IN_STOCK", nullable = false)
	private long inStock = 0;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ProductDetails productDetails;

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Date getLastTxnDate() {
		return lastTxnDate;
	}

	public void setLastTxnDate(Date lastTxnDate) {
		this.lastTxnDate = lastTxnDate;
	}

	// public int getProductId() {
	// return productId;
	// }
	//
	// public void setProductId(int productId) {
	// this.productId = productId;
	// }

	public long getInStock() {
		return inStock;
	}

	public void setInStock(long inStock) {
		this.inStock = inStock;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
}
