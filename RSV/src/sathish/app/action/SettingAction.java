/**
 * 
 */
package sathish.app.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import sathish.app.dto.Company;
import sathish.app.dto.DeliveryBoys;
import sathish.app.dto.ProductDetails;
import sathish.app.dto.ProductGroup;
import sathish.app.dto.SalesEntry;
import sathish.app.dto.UserDetail;
import sathish.app.service.Settings;
import sathish.app.service.SettingsService;
import sathish.app.service.UserService;
import sathish.app.service.UserServiceManager;
import sathish.app.util.Utilities;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * @author ps
 * 
 */
public class SettingAction extends ActionSupport implements SessionAware, Preparable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1761065583989165570L;
	private static final Logger logger = LogManager.getLogger("RSVtraders");

	private Map<String, Object> session;

	private Settings settingService;
	private UserService userService;

	private Utilities utils = new Utilities();
	private InputStream responseMsg;

	@Override
	public void prepare() throws Exception {
		// preparing with service
		userService = new UserServiceManager(session);
		settingService = new SettingsService(session);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public String settings() {
		logger.info("SettingAction : Settings");
		try {

			this.productGroupList = settingService.getAllRecords(ProductGroup.class);
			this.companyList = settingService.getAllRecords(Company.class);
			this.userDetailList = settingService.getAllRecords(UserDetail.class);
			this.deliveryBoysList = settingService.getAllRecords(DeliveryBoys.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String userDetails() {
		logger.info("SettingAction : userDetails");
		try {
			setUserDetailList(new ArrayList<UserDetail>());
			if (this.filterId > 0) {
				this.userDetailList.add((UserDetail) settingService.getById(UserDetail.class, (int) filterId));
			} else {
				this.userDetailList.add(new UserDetail());
			}
			System.out.println(filterId + " : userDetailList " + userDetailList.get(0).getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String group() {
		logger.info("SettingAction : group");
		try {
			if (this.filterId > 0) {
				this.productGroupList.add((ProductGroup) settingService.getById(ProductGroup.class, (int) filterId));
			} else {
				this.productGroupList.add(new ProductGroup());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String deliveryBoys() {
		logger.info("SettingAction : deliverBoys");
		try {
			if (this.filterId > 0) {
				this.deliveryBoysList.add((DeliveryBoys) settingService.getById(DeliveryBoys.class, (int) filterId));
			} else {
				this.deliveryBoysList.add(new DeliveryBoys());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String company() {
		logger.info("SettingAction : company");
		try {
			if (this.filterId > 0) {
				this.companyList.add((Company) settingService.getById(Company.class, (int) filterId));
			} else {
				this.companyList.add(new Company());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String loadUser() {
		logger.info("SettingAction : loadUser");
		try {
			this.userDetailList = settingService.getAllRecords(UserDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String loadGroup() {
		logger.info("SettingAction : loadGroup");
		try {
			this.productGroupList = settingService.getAllRecords(ProductGroup.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String loadCompany() {
		logger.info("SettingAction : loadCompany");
		try {
			this.companyList = settingService.getAllRecords(Company.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String loadDeliveryBoys() {
		logger.info("SettingAction : loadDeliveryBoys");
		try {
			this.deliveryBoysList = settingService.getAllRecords(DeliveryBoys.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	public String addGroup() {
		logger.info("SettingAction addGroup");
		boolean status = false;
		String message = "Group Add/Updated Successfully";
		try {
			status = settingService.saveOrUpdateEntity(this.productGroup);
		} catch (Exception e) {
			message = "Something went wrong!!!";
			e.printStackTrace();
		}
		if (!status) {
			message = "Group Add/Updated Failed";
		}
		logger.info(message);
		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String addUser() {
		logger.info("SettingAction addUser");
		boolean status = false;
		String message = "User Add/Updated Successfully";
		try {
			status = settingService.saveOrUpdateEntity(this.userDetail);
		} catch (Exception e) {
			message = "Something went wrong!!!";
			e.printStackTrace();
		}
		if (!status) {
			message = "User Add/Updated Failed";
		}
		logger.info(message);
		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String addDeliveryBoys() {
		logger.info("SettingAction addDeliveryBoys");
		boolean status = false;
		String message = "Delivery Boy Add/Updated Successfully";
		try {
			status = settingService.saveOrUpdateEntity(this.deliveryBoys);
		} catch (Exception e) {
			message = "Something went wrong!!!";
			e.printStackTrace();
		}
		if (!status) {
			message = "Delivery Boy Add/Updated Failed";
		}
		logger.info(message);
		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String addCompany() {
		logger.info("SettingAction addCompany");
		boolean status = false;
		String message = "Company Add/Updated Successfully";
		try {
			status = settingService.saveOrUpdateEntity(this.company);
		} catch (Exception e) {
			message = "Something went wrong!!!";
			e.printStackTrace();
		}
		if (!status) {
			message = "Company Add/Updated Failed";
		}
		logger.info(message);
		responseMsg = utils.responseMessage(message);

		return "stream";
	}

	public String deleteGroup() {
		logger.info("SettingAction : deleteGroup");
		String message = "Deleting Product Group Failed";
		boolean isDeleted = false;

		try {
			isDeleted = settingService.deleteEntityById(ProductGroup.class, (int) this.filterId);
			if (isDeleted) {
				message = "Product Group deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Product Group";
			logger.error("error delete Product Group");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteUser() {
		logger.info("SettingAction : deleteUser");
		String message = "Deleting User Failed";
		boolean isDeleted = false;

		try {
			isDeleted = settingService.deleteEntityById(UserDetail.class, (int) this.filterId);
			if (isDeleted) {
				message = "User deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting User";
			logger.error("error delete User");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteDeliveryBoys() {
		logger.info("SettingAction : deleteDeliveryBoys");
		String message = "Deleting Delivery Boy Failed";
		boolean isDeleted = false;

		try {
			isDeleted = settingService.deleteEntityById(DeliveryBoys.class, (int) this.filterId);
			if (isDeleted) {
				message = "Delivery Boy deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Delivery Boy";
			logger.error("error delete Delivery Boy");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	public String deleteCompany() {
		logger.info("SettingAction : deleteDeliveryBoys");
		String message = "Deleting Company Failed";
		boolean isDeleted = false;

		try {
			isDeleted = settingService.deleteEntityById(Company.class, (int) this.filterId);
			if (isDeleted) {
				message = "Company deleted successfully!";
			}
		} catch (Exception e) {
			message = "Something went wrong while deleting Company";
			logger.error("error delete Company");
			e.printStackTrace();
		}

		responseMsg = utils.responseMessage(message);
		return "stream";
	}

	private long filterId;
	private int filterIdInt;

	private List<ProductGroup> productGroupList = new ArrayList<ProductGroup>();
	private List<Company> companyList = new ArrayList<Company>();
	private List<ProductDetails> productList = new ArrayList<ProductDetails>();

	private List<UserDetail> userDetailList = new ArrayList<UserDetail>();
	private List<DeliveryBoys> deliveryBoysList = new ArrayList<DeliveryBoys>();

	private UserDetail userDetail = new UserDetail();
	private Company company = new Company();
	private ProductGroup productGroup = new ProductGroup();
	private DeliveryBoys deliveryBoys = new DeliveryBoys();

	public List<ProductGroup> getProductGroupList() {
		return productGroupList;
	}

	public void setProductGroupList(List<ProductGroup> productGroupList) {
		this.productGroupList = productGroupList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public List<ProductDetails> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}

	public List<UserDetail> getUserDetailList() {
		return userDetailList;
	}

	public void setUserDetailList(List<UserDetail> userDetailList) {
		this.userDetailList = userDetailList;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}

	public List<DeliveryBoys> getDeliveryBoysList() {
		return deliveryBoysList;
	}

	public void setDeliveryBoysList(List<DeliveryBoys> deliveryBoysList) {
		this.deliveryBoysList = deliveryBoysList;
	}

	public DeliveryBoys getDeliveryBoys() {
		return deliveryBoys;
	}

	public void setDeliveryBoys(DeliveryBoys deliveryBoys) {
		this.deliveryBoys = deliveryBoys;
	}

	public long getFilterId() {
		return filterId;
	}

	public void setFilterId(long filterId) {
		this.filterId = filterId;
	}

	public int getFilterIdInt() {
		return filterIdInt;
	}

	public void setFilterIdInt(int filterIdInt) {
		this.filterIdInt = filterIdInt;
	}

	public InputStream getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(InputStream responseMsg) {
		this.responseMsg = responseMsg;
	}

}
