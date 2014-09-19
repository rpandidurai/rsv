/**
 * 
 */
package sathish.app.service;

import java.util.List;
import java.util.Date;

import sathish.app.dto.Company;
import sathish.app.dto.CustomerDetails;
import sathish.app.dto.ProductDetails;
import sathish.app.dto.ProductGroup;
import sathish.app.dto.PurchaseEntry;
import sathish.app.dto.SalesDetails;
import sathish.app.dto.SalesEntry;

/**
 * @author root
 * 
 */
public interface UserService {

	public List<ProductGroup> productGroup();

	public List<Company> getCompanyList();

	public boolean addProduct(ProductDetails product);

	public List<ProductDetails> getProductList(int productId, int groupId);

	public int addCustomer(CustomerDetails product);

	public List<CustomerDetails> getCustomerList(int customerId);

	public int addSalesEntry(SalesEntry salesEntry);

	// public int addSalesDetailLst(List<SalesDetails> salesDetailList, int
	// salesEntryId);

	public List<SalesEntry> getSalesBook(long salesId);

	public List<SalesEntry> getSalesBook(Date fromFilterDate, Date toFilterDate);

	public int addPurchaseEntry(PurchaseEntry purchaseEntry);

	public List<PurchaseEntry> getPurchaseDetails(Date fromFilterDate, Date toFilterDate);

	public List<PurchaseEntry> getPurchaseDetails(long purchaseId);

	public int updatePurchaseEntry(PurchaseEntry purchaseEntry);

	public List getAllRecords(Class entity);

	public Object getById(Class<?> entity, long id);
	
	public Object getLastRecord(Class<?> entity, String property);

	public Object getPropertyValue(Class<?> entity, String property, String propertyId, Object id);

	public boolean saveOrUpdateEntity(Object persistentInstance);

	public boolean deleteEntityById(Class<?> entity, long id);

	public boolean deleteEntityById(Class<?> entity, int id);

	public boolean deleteObject(Object persistentInstance);

	public int deletePurchaseEntry(long id);

	public String getLastBillNo();
}
