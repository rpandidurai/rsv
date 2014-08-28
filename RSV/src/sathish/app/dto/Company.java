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
@Table(name = "COMPANY")
public class Company {

	public Company() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "COMPANY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int companyId;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "COMPANY_DESCRIPTION")
	private String companyDesc;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

}
