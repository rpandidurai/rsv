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
@Table(name = "PRODUCT_DETAILS")
public class ProductDetails {

	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private int productId;
	@Column(name = "PRODUCT_NAME", unique = true)
	private String productName;
	@Column(name = "PRODUCT_BENEFITS")
	private String productBenefits;
	@Column(name = "PRODUCT_VALIDITY")
	private int productValidity;
	@Column(name = "GROUP_ID")
	private int groupId;
	@Column(name = "PRODUCT_UNIT_RATE")
	private float productUnitRate;

	@OneToOne
	@JoinColumn(name = "GROUP_ID", updatable = false, insertable = false)
	private ProductGroup productGroup;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "STOCK_ID")
	private Stock stock;

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBenefits() {
		return productBenefits;
	}

	public void setProductBenefits(String productBenefits) {
		this.productBenefits = productBenefits;
	}

	public int getProductValidity() {
		return productValidity;
	}

	public void setProductValidity(int productValidity) {
		this.productValidity = productValidity;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public float getProductUnitRate() {
		return productUnitRate;
	}

	public void setProductUnitRate(float productUnitRate) {
		this.productUnitRate = productUnitRate;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
