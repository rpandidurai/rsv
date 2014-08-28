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
@Table(name="RECHARGE")
public class Recharge {

	public Recharge() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@Column(name="RECHARGE_ID")
	private int rechargeId;
	@Column(name="RECHARGE_NAME")
	private String rechargeName;
	@Column(name="DESCRIPTION")
	private String description;
	
	
	public int getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(int rechargeId) {
		this.rechargeId = rechargeId;
	}
	public String getRechargeName() {
		return rechargeName;
	}
	public void setRechargeName(String rechargeName) {
		this.rechargeName = rechargeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
