/**
 * 
 */
package sathish.app.dto;

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
import javax.persistence.Table;

/**
 * @author root
 *
 */
@Entity
@Table(name="EASY_RECHARGE")
public class EasyRecharge {

	public EasyRecharge() {
		// TODO Auto-generated constructor stub
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="EASY_RECHARGE_ID")
	private long easyRechargeId;
	@Column(name="EASY_BALANCE_AMOUNT")
	private double easyBalanceAmount;
	@Column(name="TXN_DATE")
	private Date txnDate;
	@Column(name="TXN_FOR")
	private char txnFor;
	@Column(name="TXN_AMOUNT")
	private  double txnAmount;
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="EASY_MOBILE_ID")
	private EasyMobileNo easyMobileNo;
	
	
	public long getEasyRechargeId() {
		return easyRechargeId;
	}
	public void setEasyRechargeId(long easyRechargeId) {
		this.easyRechargeId = easyRechargeId;
	}
	public double getEasyBalanceAmount() {
		return easyBalanceAmount;
	}
	public void setEasyBalanceAmount(double easyBalanceAmount) {
		this.easyBalanceAmount = easyBalanceAmount;
	}
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	public char getTxnFor() {
		return txnFor;
	}
	public void setTxnFor(char txnFor) {
		this.txnFor = txnFor;
	}
	public double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(double txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public EasyMobileNo getEasyMobileNo() {
		return easyMobileNo;
	}
	public void setEasyMobileNo(EasyMobileNo easyMobileNo) {
		this.easyMobileNo = easyMobileNo;
	}	
}
