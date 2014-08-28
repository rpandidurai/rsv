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
import javax.persistence.Table;

/**
 * @author root
 * 
 */
@Entity
@Table(name = "STOCK")
public class Stock {

	public Stock() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STOCK_ID")
	private long stockId;
	@Column(name = "DATE", nullable = false)
	private Date date;
	@Column(name = "INVOICE_NO", nullable = false)
	private String invoiceNo;
	@Column(name = "PRODUCT_ID", nullable = false)
	private int productId;
	@Column(name = "PURCHASE")
	private int purchase = 0;
	@Column(name = "SALE")
	private int sale = 0;
	@Column(name = "IN_STOCK", nullable = false)
	private long inStock = 0;

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
	private ProductDetails productDetails;

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPurchase() {
		return purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

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
