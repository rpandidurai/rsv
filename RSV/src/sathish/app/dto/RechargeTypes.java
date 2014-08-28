/**
 * 
 */
package sathish.app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author root
 * 
 */
@Entity
@Table(name = "RECHARGE_TYPES")
public class RechargeTypes {

	public RechargeTypes() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "RC_ID")
	private int rcId;
	@Column(name = "RC_TYPE")
	private String rcType;

	public int getRcId() {
		return rcId;
	}

	public void setRcId(int rcId) {
		this.rcId = rcId;
	}

	public String getRcType() {
		return rcType;
	}

	public void setRcType(String rcType) {
		this.rcType = rcType;
	}

}
