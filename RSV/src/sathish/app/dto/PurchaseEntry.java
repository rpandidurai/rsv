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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

/**
 * @author root
 * 
 */
@Entity
@Table(name = "PURCHASE_ENTRY")
public class PurchaseEntry {

	public PurchaseEntry() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "PURCHASE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long purchaseId;
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	@Column(name = "INVOICE_DATE")
	@Type(type = "date")
	private Date invoiceDate;
	@Column(name = "TOTAL_QUANTITY")
	private int totalQuantity;
	@Column(name = "TOTAL_AMOUNT")
	private float totalAmount;

	@Column(name = "COMPANY_ID")
	private int companyId;

	@OneToOne
	@JoinColumn(name = "COMPANY_ID", insertable = false, updatable = false)
	private Company company;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "PURCHASE_ID")
	@Fetch(FetchMode.SUBSELECT)
	private Collection<PurchaseDetails> purchaseDetailsList = new ArrayList<PurchaseDetails>();

	public Collection<PurchaseDetails> getPurchaseDetailsList() {
		return purchaseDetailsList;
	}

	public void setPurchaseDetailsList(Collection<PurchaseDetails> purchaseDetailsList) {
		this.purchaseDetailsList = purchaseDetailsList;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
