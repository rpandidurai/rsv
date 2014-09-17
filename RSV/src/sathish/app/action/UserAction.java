/**
 * 
 */
package sathish.app.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import sathish.app.dto.Company;
import sathish.app.dto.CustomerDetails;
import sathish.app.dto.DeliveryBoys;
import sathish.app.dto.EasyMobileNo;
import sathish.app.dto.EasyRecharge;
import sathish.app.dto.EasyRechargeBalance;
import sathish.app.dto.ProductDetails;
import sathish.app.dto.ProductGroup;
import sathish.app.dto.PurchaseDetails;
import sathish.app.dto.PurchaseEntry;
import sathish.app.dto.Recharge;
import sathish.app.dto.RechargeDetails;
import sathish.app.dto.SalesDetails;
import sathish.app.dto.SalesEntry;
import sathish.app.dto.Stock;
import sathish.app.service.UserService;
import sathish.app.service.UserServiceManager;
import sathish.app.util.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * @author root
 * 
 */
public class UserAction extends ActionSupport implements SessionAware, Preparable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 707747804953526346L;
	private static final Logger logger = Logger.getLogger("RSVtraders");
	private Utilities utils = new Utilities();

	private Map<String, Object> session;
	private UserService userService;

	private InputStream responseMsg;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void prepare() throws Exception {
		// creating user service instance
		userService = new UserServiceManager(session);
	}

	// product methods

	public String product() {
		logger.info("In Product action");

		this.productGroupList = userService.productGroup();
		// this.setProductList(userService.getProductList(0));

		return "SUCCESS";
	}

	public String productList() {
		logger.info("In Product List action");

		this.setProductList(userService.getProductList(0, this.sortId));

		return "SUCCESS";
	}

	public String addProduct() {
		logger.info("in addProduct");

		if (this.filterId != 0) {
			this.productList = userService.getProductList((int) filterId, 0);
		} else {
			ProductDetails product = new ProductDetails();
			product.setStock(new Stock());
			this.productList.add(product);
		}
		this.productGroupList = userService.getAllRecords(ProductGroup.class);
		System.out.println(" this.productList   " + this.productList);
		return "SUCCESS";
	}

	public String productRegister() {
		logger.info("Add/Update New Product");

		String status = "";
		String message = "Product Added Successfully";

		if (this.product.getProductId() > 0) {
			message = "Product Updated Successfully";
		}

		status = userService.addProduct(this.product);

		this.productGroupList = userService.getAllRecords(ProductGroup.class);
		this.productList.add(this.product);
		if ("added".equals(status)) {
			logger.info(message);
			addActionMessage(message);
			return "SUCCESS";
		} else {
			addActionError("Product Adding Failed");
			return "FAIL";
		}
	}

	public String customer() {
		logger.info("action Customer");

		return "SUCCESS";
	}

	public String customerList() {
		logger.info("action Customer List");

		this.setCustomerList(userService.getAllRecords(CustomerDetails.class));

		return "SUCCESS";
	}

	public String addCustomer() {
		logger.info("in addCustomer ");

		if (this.filterId > 0) {
			this.setCustomerList(userService.getCustomerList((int) filterId));
		} else {
			this.customerList.add(new CustomerDetails());
		}

		return "SUCCESS";
	}

	public String customerRegister() {
		logger.info("customerRegister");
		boolean status = false;
		String message = "Customer Added Successfully";

		if (this.customer.getCustomerId() > 0) {
			message = "Customer Updated Successfully";
		}
		status = userService.saveOrUpdateEntity(this.customer);

		this.customerList.add(this.customer);
		if (status) {
			logger.info(message);
			addActionMessage(message);
			return "SUCCESS";
		} else {
			addActionError("Customer add/update Failed");
			return "FAIL";
		}
	}

	// sales details method
	public String sales() {
		logger.info("In Sales action");

		return "SUCCESS";
	}

	public String salesBook() {
		logger.info("In Sales Book action");
		try {

			if (this.fromFilterDate == null) {
				this.setFromFilterDate(new Date());
			}
			if (this.toFilterDate == null) {
				this.setToFilterDate(new Date());
			}

			// UserService userService = new UserServiceManager(session);
			this.salesEntryList = userService.getSalesBook(this.getFromFilterDate(), this.getToFilterDate());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error in Sales Book action");
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String addSalesDetails() {
		logger.info("In Add Sales action");
		UserService userService = new UserServiceManager(session);
		try {

			this.setCustomerList(userService.getCustomerList(0));
			this.productList = userService.getProductList(0, 0);
			this.deliveryBoysList = userService.getAllRecords(DeliveryBoys.class);
			if (this.filterId > 0) {
				// this.salesEntryList = userService.getById(SalesEntry.class,
				// filterId);
				this.getSalesEntryList().add((SalesEntry) userService.getById(SalesEntry.class, filterId));
			} else {
				salesEntry = new SalesEntry();
				salesDetails = new SalesDetails();
				salesEntry.setBillNo(utils.generateBillNo(userService.getLastBillNo()));
				salesEntry.getSalesDetailsList().add(salesDetails);
				this.salesEntryList.add(salesEntry);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String salesRegister() {
		logger.info("Sales salesRegister");
		boolean status = false;
		String message = "";

		if (this.salesEntry.getSalesEntryId() > 0) {
			message = "Sales Entry Updated Successfully";
		} else {
			message = "Sales Entry Added Successfully";
		}

		// salesEntryId = userService.addSalesEntry(this.salesEntry);

		status = userService.saveOrUpdateEntity(this.salesEntry);

		this.setCustomerList(userService.getCustomerList(0));
		this.productList = userService.getProductList(0, 0);

		if (status) {
			logger.info(message);
			addActionMessage(message);

			salesEntry = new SalesEntry();
			salesDetails = new SalesDetails();
			salesEntry.setBillNo(utils.generateBillNo(userService.getLastBillNo()));
			salesEntry.getSalesDetailsList().add(salesDetails);
			this.salesEntryList.add(0, salesEntry);

		} else {
			addActionError("Sales Entry Adding Failed");
			logger.info("Sales Entry Adding Failed");

			this.salesEntryList.add(0, salesEntry);
		}
		return "SUCCESS";
	}

	// recharge details method addPurchaseEntry
	public String rechargeDetails() {
		logger.info("Recharge rechargeDetails");

		return "SUCCESS";
	}

	public String addRechargeDetails() {
		logger.info("UserAction : addRechargeDetails");

		this.productList = userService.getProductList(0, 0);

		return "SUCCESS";
	}

	public String rechargeRegister() {
		logger.info("Recharge rechargeRegister");

		return "SUCCESS";
	}

	// purchase details method
	public String purchaseEntry() {
		logger.info("purchaseEntry");

		return "SUCCESS";
	}

	public String purchaseDetails() {
		logger.info("In Purchase Book action");

		if (this.fromFilterDate == null) {
			this.setFromFilterDate(new Date());
		}
		if (this.toFilterDate == null) {
			this.setToFilterDate(new Date());
		}

		UserService userService = new UserServiceManager(session);
		this.purchaseEntryList = userService.getPurchaseDetails(this.getFromFilterDate(), this.getToFilterDate());

		return "SUCCESS";
	}

	public String addPurchaseEntry() {
		logger.info("purchaseEntry addPurchaseEntry");
		try {
			UserService userService = new UserServiceManager(session);

			this.productList = userService.getProductList(0, 0);
			this.companyList = userService.getCompanyList();
			if (this.filterId > 0) {
				this.purchaseEntryList = userService.getPurchaseDetails(filterId);
			} else {
				this.purchaseEntry = new PurchaseEntry();
				this.purchaseDetails = new PurchaseDetails();

				this.purchaseEntry.getPurchaseDetailsList().add(purchaseDetails);

				purchaseEntryList.add(purchaseEntry);

			}

		} catch (Exception e) {
			logger.info("error in addPurchaseEntry()");
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String purchaseRegister() {
		logger.info("action : adding purchase Entry");
		boolean status = false;
		PurchaseEntry lastPurchase;
		String message = "Purchase Entry Added Successfully";

		if (this.purchaseEntry.getPurchaseId() > 0) {
			message = "Purchase Entry Updated Successfully";
		}

		status = userService.saveOrUpdateEntity(this.purchaseEntry);

		if (status) {
			lastPurchase = (PurchaseEntry) userService.getLastRecord(PurchaseEntry.class, "purchaseId");
		}

		this.productList = userService.getProductList(0, 0);
		this.companyList = userService.getCompanyList();
		purchaseEntryList.add(this.purchaseEntry);

		if (status) {
			logger.info(message);
			addActionMessage(message);
			return "SUCCESS";
		} else {
			addActionError("Purchase Entry adding failed");
			return "FAIL";
		}

	}

	public Object updateStock(List<PurchaseDetails> list) {
		logger.info("action : updateStock");
		Stock stock;
		try {

			for (PurchaseDetails purchaseDetails : list) {
				stock = new Stock();
				// stock.setProductId(purchaseDetails.getProductId());
				stock.setInStock(purchaseDetails.getPurchseQuantity());
				stock.setLastTxnDate(new Date());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deletePurchaseEntry() {
		logger.info("action delete PurchaseEntry");
		String message = "Deleting Purchase Entry Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(PurchaseEntry.class, this.filterId);
			if (isDeleted) {
				message = "Purchase Entry deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Purchase Entry";
			logger.error("error delete PurchaseEntry");
			e.printStackTrace();
		}
		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String deletePurchaseDetails() {
		logger.info("action delete PurchaseDetails");
		String message = "Deleting Purchase Detail Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(PurchaseDetails.class, this.filterId);
			if (isDeleted) {
				message = "Purchase Detail deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Purchase Detail";
			logger.error("error delete PurchaseDetails");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteSalesEntry() {
		logger.info("action delete SalesEntry");
		String message = "Deleting Sales Entry Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(SalesEntry.class, this.filterId);
			if (isDeleted) {
				message = "Sales Entry deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Sales Entry";
			logger.error("error delete SalesEntry");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteSalesDetails() {
		logger.info("action delete SalesDetails");
		String message = "Deleting Sales Detail Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(SalesDetails.class, this.filterId);
			if (isDeleted) {
				message = "Sales Detail deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Sales Detail";
			logger.error("error delete SalesDetails");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteProduct() {
		logger.info("action delete Product");
		String message = "Deleting Product Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(ProductDetails.class, (int) this.filterId);
			if (isDeleted) {
				message = "Product deleted successfully!";
			}

		} catch (Exception e) {
			message = "Something went wrong while deleting product";
			logger.error("error delete Product");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteCustomer() {
		logger.info("action delete Customer");
		String message = "Deleting Customer Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(CustomerDetails.class, (int) this.filterId);

			if (isDeleted) {
				message = "Customer deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting customer";
			logger.error("error delete Customer");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String getProductFareStock() {
		logger.info("UserAction : getProductStock");
		String value = "";
		try {
			value = userService.getPropertyValue(ProductDetails.class, "productUnitRate", "productId", (int) filterId);
			value = value + "," + userService.getPropertyValue(Stock.class, "inStock", "stockId", filterId);
			System.out.println("value ------------------- " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(value);

		return "stream";
	}

	/* Easy recharge methods */
	public String easyRecharge() {
		logger.info("UserAction : easyRecharge");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String easyRechargeDetails() {
		logger.info("UserAction : easyRechargeDetails");
		String returnPage = "";
		try {
			if (this.filterId > 0) {
				easyRechargeBalanceList = new ArrayList<EasyRechargeBalance>();
				easyRechargeBalanceList.add(new EasyRechargeBalance());
				returnPage = "loadCash";
			} else {
				easyRechargeBalanceList = userService.getAllRecords(EasyRechargeBalance.class);
				returnPage = "SUCCESS";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPage;
	}

	public String addEasyMobileNo() {
		logger.info("UserAction : addEasyMobileNo");
		String message = "Mobile Number Added Successfully";
		boolean status = false;
		try {
			status = userService.saveOrUpdateEntity(easyMobileNo);

			if (this.easyMobileNo.getEasyMobileId() > 0) {
				message = "Mobile Number Updated Successfully";
			}

			if (status) {
				logger.info(message);
				addActionMessage(message);
				return "SUCCESS";
			} else {
				addActionError("Mobile Number adding failed");
				return "FAIL";
			}
		} catch (Exception e) {
			message = "Something went wrong while adding mobile no";
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String addEasyRecharge() {
		logger.info("UserAction : addEasyMobileNo");
		String message = "Load Cash Successfully";
		boolean status = false;
		try {
			status = userService.saveOrUpdateEntity(easyRecharge);

			if (this.easyRecharge.getEasyRechargeId() > 0) {
				message = "Load Cash Updated Successfully";
			}

			if (status) {
				logger.info(message);
				addActionMessage(message);
				return "SUCCESS";
			} else {
				addActionError("Load Cash adding failed");
				return "FAIL";
			}
		} catch (Exception e) {
			message = "Something went wrong while Load Cash";
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String deleteEasyMobileNo() {
		logger.info("action delete Mobile No");
		String message = "Deleting Mobile No Failed";
		boolean isDeleted = false;

		try {
			isDeleted = userService.deleteEntityById(EasyMobileNo.class, this.filterId);

			if (isDeleted) {
				message = "Mobile No deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Mobile No";
			logger.error("error delete Mobile No");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	private Date filterDate;
	private Date fromFilterDate;
	private Date toFilterDate;
	private long filterId;
	private int sortId;

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public long getFilterId() {
		return filterId;
	}

	public void setFilterId(long filterId) {
		this.filterId = filterId;
	}

	public Date getFromFilterDate() {
		return fromFilterDate;
	}

	public void setFromFilterDate(Date fromFilterDate) {
		this.fromFilterDate = fromFilterDate;
	}

	public Date getToFilterDate() {
		return toFilterDate;
	}

	public void setToFilterDate(Date toFilterDate) {
		this.toFilterDate = toFilterDate;
	}

	public Date getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(Date filterDate) {
		this.filterDate = filterDate;
	}

	private ProductDetails product;
	private CustomerDetails customer;

	private RechargeDetails rechargeDetails;
	private SalesDetails salesDetails;
	private SalesEntry salesEntry;
	private PurchaseEntry purchaseEntry;
	private PurchaseDetails purchaseDetails;
	private DeliveryBoys deliveryBoys;

	private EasyMobileNo easyMobileNo;
	private EasyRechargeBalance easyRechargeBalance;
	private EasyRecharge easyRecharge;

	private Stock stock;

	private List<DeliveryBoys> deliveryBoysList = new ArrayList<DeliveryBoys>();

	private List<ProductDetails> productList = new ArrayList<ProductDetails>();
	private List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
	private List<ProductGroup> productGroupList = new ArrayList<ProductGroup>();
	private List<Company> companyList = new ArrayList<Company>();
	private List<Recharge> rechargeList = new ArrayList<Recharge>();

	private List<RechargeDetails> rechargeDetailsList;
	private List<SalesDetails> salesDetailsList;

	private List<SalesEntry> salesEntryList = new ArrayList<SalesEntry>();
	private List<PurchaseEntry> purchaseEntryList = new ArrayList<PurchaseEntry>();

	private List<EasyMobileNo> easyMobileNoList = new ArrayList<EasyMobileNo>();
	private List<EasyRechargeBalance> easyRechargeBalanceList = new ArrayList<EasyRechargeBalance>();
	private List<EasyRecharge> easyRechargeList = new ArrayList<EasyRecharge>();

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public List<SalesDetails> getSalesDetailsList() {
		return salesDetailsList;
	}

	public void setSalesDetailsList(List<SalesDetails> salesDetailsList) {
		this.salesDetailsList = salesDetailsList;
	}

	public SalesEntry getSalesEntry() {
		return salesEntry;
	}

	public void setSalesEntry(SalesEntry salesEntry) {
		this.salesEntry = salesEntry;
	}

	public List<SalesEntry> getSalesEntryList() {
		return salesEntryList;
	}

	public void setSalesEntryList(List<SalesEntry> salesEntryList) {
		this.salesEntryList = salesEntryList;
	}

	public ProductDetails getProduct() {
		return product;
	}

	public void setProduct(ProductDetails product) {
		this.product = product;
	}

	/**
	 * @return the productList
	 */
	public List<ProductDetails> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public List<CustomerDetails> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDetails> customerList) {
		this.customerList = customerList;
	}

	public List<ProductGroup> getProductGroupList() {
		return productGroupList;
	}

	public void setProductGroupList(List<ProductGroup> productGroupList) {
		this.productGroupList = productGroupList;
	}

	public RechargeDetails getRechargeDetails() {
		return rechargeDetails;
	}

	public void setRechargeDetails(RechargeDetails rechargeDetails) {
		this.rechargeDetails = rechargeDetails;
	}

	public SalesDetails getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(SalesDetails salesDetails) {
		this.salesDetails = salesDetails;
	}

	public List<RechargeDetails> getRechargeDetailsList() {
		return rechargeDetailsList;
	}

	public void setRechargeDetailsList(List<RechargeDetails> rechargeDetailsList) {
		this.rechargeDetailsList = rechargeDetailsList;
	}

	public List<Recharge> getRechargeList() {
		return rechargeList;
	}

	public void setRechargeList(List<Recharge> rechargeList) {
		this.rechargeList = rechargeList;
	}

	public PurchaseEntry getPurchaseEntry() {
		return purchaseEntry;
	}

	public void setPurchaseEntry(PurchaseEntry purchaseEntry) {
		this.purchaseEntry = purchaseEntry;
	}

	public PurchaseDetails getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(PurchaseDetails purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public List<PurchaseEntry> getPurchaseEntryList() {
		return purchaseEntryList;
	}

	public void setPurchaseEntryList(List<PurchaseEntry> purchaseEntryList) {
		this.purchaseEntryList = purchaseEntryList;
	}

	public InputStream getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(InputStream responseMsg) {
		this.responseMsg = responseMsg;
	}

	public DeliveryBoys getDeliveryBoys() {
		return deliveryBoys;
	}

	public void setDeliveryBoys(DeliveryBoys deliveryBoys) {
		this.deliveryBoys = deliveryBoys;
	}

	public List<DeliveryBoys> getDeliveryBoysList() {
		return deliveryBoysList;
	}

	public void setDeliveryBoysList(List<DeliveryBoys> deliveryBoysList) {
		this.deliveryBoysList = deliveryBoysList;
	}

	public EasyMobileNo getEasyMobileNo() {
		return easyMobileNo;
	}

	public void setEasyMobileNo(EasyMobileNo easyMobileNo) {
		this.easyMobileNo = easyMobileNo;
	}

	public EasyRecharge getEasyRecharge() {
		return easyRecharge;
	}

	public void setEasyRecharge(EasyRecharge easyRecharge) {
		this.easyRecharge = easyRecharge;
	}

	public List<EasyMobileNo> getEasyMobileNoList() {
		return easyMobileNoList;
	}

	public void setEasyMobileNoList(List<EasyMobileNo> easyMobileNoList) {
		this.easyMobileNoList = easyMobileNoList;
	}

	public List<EasyRecharge> getEasyRechargeList() {
		return easyRechargeList;
	}

	public void setEasyRechargeList(List<EasyRecharge> easyRechargeList) {
		this.easyRechargeList = easyRechargeList;
	}

	public EasyRechargeBalance getEasyRechargeBalance() {
		return easyRechargeBalance;
	}

	public void setEasyRechargeBalance(EasyRechargeBalance easyRechargeBalance) {
		this.easyRechargeBalance = easyRechargeBalance;
	}

	public List<EasyRechargeBalance> getEasyRechargeBalanceList() {
		return easyRechargeBalanceList;
	}

	public void setEasyRechargeBalanceList(List<EasyRechargeBalance> easyRechargeBalanceList) {
		this.easyRechargeBalanceList = easyRechargeBalanceList;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
