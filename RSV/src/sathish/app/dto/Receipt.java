/**
 * 
 */
package sathish.app.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author root
 * 
 */
//@Entity
//@Table(name = "RECEIPT")
public class Receipt {

	public Receipt() {
		// empty constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RECEIPT_ID")
	private long receiptId;
	@Column(name = "RECEIPT_NO", unique = true)
	private String receiptNo;
	@Column(name = "RECEIPT_DETAIL")
	private String receiptDetail;
	@Column(name = "RECEIPT_TIME")
	private Date receiptTime = new Date();
	// @Column(name = "SALES_ENTRY_ID")
	// private long salesEntryId;

	// @ManyToOne
	// @JoinColumn(name = "SALES_ENTRY_ID", unique=true)
//	@OneToOne(mappedBy="receipt")
	private SalesEntry salesEntry;

	//
	// public long getSalesEntryId() {
	// return salesEntryId;
	// }
	//
	// public void setSalesEntryId(long salesEntryId) {
	// this.salesEntryId = salesEntryId;
	// }

	public SalesEntry getSalesEntry() {
		return salesEntry;
	}

	public void setSalesEntry(SalesEntry salesEntry) {
		this.salesEntry = salesEntry;
	}

	public long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(long receiptId) {
		this.receiptId = receiptId;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptDetail() {
		return receiptDetail;
	}

	public void setReceiptDetail(String receiptDetail) {
		this.receiptDetail = receiptDetail;
	}

	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}

}
