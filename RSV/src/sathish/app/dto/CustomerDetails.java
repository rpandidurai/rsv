/**
 * 
 */
package sathish.app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author root
 *
 */
@Entity
@Table(name="CUSTOMER_DETAILS")
public class CustomerDetails {
	
	public CustomerDetails() {
		// TODO Auto-generated constructor stub
	}
	
	@Id @Column(name="CUSTOMER_ID") @GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	@Column(name="CUSTOMER_AREA")
	private String customerArea;
	@Column(name="CUSTOMER_ADDRESS")
	private String customerAddress;
	@Column(name="CUSTOMER_PHONE_NO", length=10)
	private String customerPhoneNo;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerArea() {
		return customerArea;
	}
	public void setCustomerArea(String customerArea) {
		this.customerArea = customerArea;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}
	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}
	
}
