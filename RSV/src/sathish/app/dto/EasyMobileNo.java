/**
 * 
 */
package sathish.app.dto;

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
@Table(name = "EASY_MOBILE_NO")
public class EasyMobileNo {

	public EasyMobileNo() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EASY_MOBILE_ID")
	private long easyMobileId;
	@Column(name = "EASY_MOBILE_NO")
	private String easyMobileNo;
	@Column(name = "DESCRIPTION")
	private String description;

	@OneToOne
	@JoinColumn(name = "COMPANY_ID")
	private Company company;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "EASY_MOBILE_ID", insertable = false, updatable = false)
	private EasyRechargeBalance easyRechargeBalance;

	public long getEasyMobileId() {
		return easyMobileId;
	}

	public void setEasyMobileId(long easyMobileId) {
		this.easyMobileId = easyMobileId;
	}

	public String getEasyMobileNo() {
		return easyMobileNo;
	}

	public void setEasyMobileNo(String easyMobileNo) {
		this.easyMobileNo = easyMobileNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public EasyRechargeBalance getEasyRechargeBalance() {
		return easyRechargeBalance;
	}

	public void setEasyRechargeBalance(EasyRechargeBalance easyRechargeBalance) {
		this.easyRechargeBalance = easyRechargeBalance;
	}

}
