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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author root
 * 
 */
@Entity
@Table(name = "EASY_RECHARGE_BALANCE")
public class EasyRechargeBalance {

	public EasyRechargeBalance() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EASY_MOBILE_ID")
	private long easyMobileId;
	@Column(name = "EASY_RECHARGE_BALANCE")
	private double easyRechargeBalance;
	@Column(name = "LAST_TXN_DATE")
	private Date lastTxnDate;
	@Column(name = "DESCRIPTION")
	private String descrption;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EASY_MOBILE_ID", insertable = false, updatable = false)
	private EasyMobileNo easyMobileNo;

	public long getEasyMobileId() {
		return easyMobileId;
	}

	public void setEasyMobileId(long easyMobileId) {
		this.easyMobileId = easyMobileId;
	}

	public double getEasyRechargeBalance() {
		return easyRechargeBalance;
	}

	public void setEasyRechargeBalance(double easyRechargeBalance) {
		this.easyRechargeBalance = easyRechargeBalance;
	}

	public Date getLastTxnDate() {
		return lastTxnDate;
	}

	public void setLastTxnDate(Date lastTxnDate) {
		this.lastTxnDate = lastTxnDate;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public EasyMobileNo getEasyMobileNo() {
		return easyMobileNo;
	}

	public void setEasyMobileNo(EasyMobileNo easyMobileNo) {
		this.easyMobileNo = easyMobileNo;
	}

}
