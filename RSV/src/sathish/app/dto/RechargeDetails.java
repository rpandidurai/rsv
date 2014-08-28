/**
 * 
 */
package sathish.app.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author root
 *
 */
//`ID` INT NOT NULL AUTO_INCREMENT,
//`RECHARGE_VALUES` VARCHAR(10),
//`PURCHASE_DATE` DATE DEFAULT NULL,
//`STOCK_SERIAL_FROM` INT(11) DEFAULT NULL,
//`STOCK_SERIAL_TO` INT(11) DEFAULT NULL,
//`PURCHASE_QUANTITY` INT(11) DEFAULT NULL,
//`AVAILABLE` INT(11) DEFAULT NULL,
//`DELIVERY_BOY_NAME` VARCHAR(50) DEFAULT NULL,
//`DELIVERY_DATE` DATE DEFAULT NULL,
//`DELIVERED_QUANTITY` INT(11) DEFAULT NULL,
//`DELIVERED_QUANTITY_AMOUNT` DECIMAL(8,2) DEFAULT NULL,
//`RECEIVED_AMOUNT` DECIMAL(8,2) DEFAULT NULL,
//`SOLD` INT(11) DEFAULT NULL,
//`RETURNED` INT(11) DEFAULT NULL,
//PRIMARY KEY(ID)
@Entity
@Table(name="RECHARGE_DETAILS")
public class RechargeDetails {
	
	public RechargeDetails() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@Column(name="ID")
	private int rechargeId;
	
	@Column(name="RECHARGE_VALUES")
	private String rechargeValues;
	
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;
	
	@Column(name="STOCK_SERIAL_FROM")
	private BigInteger stockSerialFrom;
	
	@Column(name="STOCK_SERIAL_TO")
	private BigInteger stockSerialTo;
	
	@Column(name="PURCHASE_QUANTITY")
	private int purchaseQuantity; 
	
	@Column(name="AVAILABLE")
	private int available;
	
	@Column(name="DELIVERY_DATE")
    private Date deliveryDate;
	
	@Column(name="DELIVERY_BOY_NAME")
    private String deliveryBoyName;
	
	@Column(name="DELIVERED_QUANTITY")
    private int deliveredQuantity;
	
	@Column(name="DELIVERED_QUANTITY_AMOUNT")
    private float deliveredQuantityAmount;
	
	@Column(name="RECEIVED_AMOUNT")
    private float receivedAmount;
	
	@Column(name="SOLD")
    private int sold;
	
	@Column(name="RETURNED")
    private int returned;
    
    
	public int getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(int rechargeId) {
		this.rechargeId = rechargeId;
	}
	public String getRechargeValues() {
		return rechargeValues;
	}
	public void setRechargeValues(String rechargeValues) {
		this.rechargeValues = rechargeValues;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public BigInteger getStockSerialFrom() {
		return stockSerialFrom;
	}
	public void setStockSerialFrom(BigInteger stockSerialFrom) {
		this.stockSerialFrom = stockSerialFrom;
	}
	public BigInteger getStockSerialTo() {
		return stockSerialTo;
	}
	public void setStockSerialTo(BigInteger stockSerialTo) {
		this.stockSerialTo = stockSerialTo;
	}
	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryBoyName() {
		return deliveryBoyName;
	}
	public void setDeliveryBoyName(String deliveryBoyName) {
		this.deliveryBoyName = deliveryBoyName;
	}
	public int getDeliveredQuantity() {
		return deliveredQuantity;
	}
	public void setDeliveredQuantity(int deliveredQuantity) {
		this.deliveredQuantity = deliveredQuantity;
	}
	public float getDeliveredQuantityAmount() {
		return deliveredQuantityAmount;
	}
	public void setDeliveredQuantityAmount(float deliveredQuantityAmount) {
		this.deliveredQuantityAmount = deliveredQuantityAmount;
	}
	public float getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(float receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getReturned() {
		return returned;
	}
	public void setReturned(int returned) {
		this.returned = returned;
	}
    
    

}


