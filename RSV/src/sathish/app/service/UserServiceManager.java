/**
 * 
 */
package sathish.app.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.opensymphony.xwork2.Preparable;

import sathish.app.dto.Company;
import sathish.app.dto.CustomerDetails;
import sathish.app.dto.ProductDetails;
import sathish.app.dto.ProductGroup;
import sathish.app.dto.PurchaseDetails;
import sathish.app.dto.PurchaseEntry;
import sathish.app.dto.SalesDetails;
import sathish.app.dto.SalesEntry;
import sathish.app.dto.Stock;
import sathish.app.dto.UserDetail;

/**
 * @author root
 * 
 */
public class UserServiceManager implements UserService {

	private SessionFactory sessionFactory;
	private static final Logger logger = LogManager.getLogger("RSVtraders");

	// private Session session = null;
	// private Transaction tx = null;

	public UserServiceManager(Map<String, Object> session) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = (SessionFactory) session.get("SessionFactory");
	}

	// public void prepareSession() {
	// System.out.println("in service prepare");
	// try {
	// session = sessionFactory.openSession();
	// tx = session.beginTransaction();
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	//
	// }

	@Override
	public List<ProductGroup> productGroup() {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<ProductGroup> productGroupList = new ArrayList<ProductGroup>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			productGroupList = session.createQuery("FROM ProductGroup").list();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("error in service : productGroup");
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return productGroupList;
	}

	@Override
	public List<Company> getCompanyList() {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		List<Company> companyList = new ArrayList<Company>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			companyList = session.createQuery("FROM Company").list();
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("Error in getCompanyList()");
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return companyList;
	}

	@Override
	public boolean addProduct(ProductDetails product) {
		// TODO Auto-generated method stub
		logger.info("service : add product");
		Session session = null;
		Transaction tx = null;
		boolean status = false;
		int productId = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(product);
			productId = 1;
			tx.commit();
			if (productId > 0) {
				status = true;
			}
		} catch (HibernateException e) {
			logger.info("Error in adding product");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sathish.app.service.UserService#getProductList(int, int) product id
	 * group id
	 */
	@Override
	public List<ProductDetails> getProductList(int productId, int groupId) {
		// TODO Auto-generated method stub
		Session session = null;

		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(ProductDetails.class);

			if (productId > 0) {// by product id
				criteria.add(Restrictions.eq("productId", productId));
			}
			if (groupId > 0) {// sort by group id
				criteria.add(Restrictions.eq("groupId", groupId));
			}

			productList = criteria.list();

		} catch (HibernateException e) {
			logger.error("Service error getProductList");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return productList;
	}

	@Override
	public int addCustomer(CustomerDetails customer) {
		// TODO Auto-generated method stub
		logger.info("adding product");
		Session session = null;
		Transaction tx = null;

		int status = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(customer);
			status = 1;
			tx.commit();
		} catch (HibernateException e) {
			logger.info("Error in adding Customer");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;
	}

	@Override
	public List<CustomerDetails> getCustomerList(int customerId) {
		// TODO Auto-generated method stub
		List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
		Session session = null;
		session = sessionFactory.openSession();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(CustomerDetails.class);
			if (customerId > 0) {
				criteria.add(Restrictions.eq("customerId", customerId));
			}

			customerList = criteria.list();
			// tx.commit();
		} catch (HibernateException e) {
			System.out.println("String getCustomerList() { error");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customerList;
	}

	@Override
	public int addSalesEntry(SalesEntry salesEntry) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		int status = -1;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx = session.beginTransaction();
			session.saveOrUpdate(salesEntry);
			status = 1;
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error in addSalesEntry");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<SalesEntry> getSalesBook(long salesEntryId) {
		// TODO Auto-generated method stub
		Session session = null;

		List<SalesEntry> salesEntryList = new ArrayList<SalesEntry>();
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(SalesEntry.class);
			if (salesEntryId > 0) {
				criteria.add(Restrictions.eq("salesEntryId", salesEntryId));
			}
			salesEntryList = criteria.list();
		} catch (HibernateException e) {
			System.out.println("String getCustomerList() { error");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return salesEntryList;
	}

	@Override
	public List<SalesEntry> getSalesBook(Date fromFilterDate, Date toFilterDate) {
		// TODO Auto-generated method stub
		Session session = null;
		List<SalesEntry> salesEntryList = new ArrayList<SalesEntry>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SalesEntry.class)
					.add(Restrictions.between("entryDate", fromFilterDate, toFilterDate)).addOrder(Order.asc("entryDate"));

			salesEntryList = criteria.list();

		} catch (HibernateException e) {
			System.out.println("error in  getSalesBook");

			e.printStackTrace();
		} finally {
			session.close();
		}
		return salesEntryList;
	}

	@Override
	public int addPurchaseEntry(PurchaseEntry purchaseEntry) {
		Session session = null;
		Transaction tx = null;

		int status = 1;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(purchaseEntry);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			status = -1;
			System.out.println("Error in addSalesEntry");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<PurchaseEntry> getPurchaseDetails(Date fromFilterDate, Date toFilterDate) {
		// TODO Auto-generated method stub
		Session session = null;

		List<PurchaseEntry> purchaseEntryList = new ArrayList<PurchaseEntry>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PurchaseEntry.class)
					.add(Restrictions.between("invoiceDate", fromFilterDate, toFilterDate)).addOrder(Order.asc("invoiceDate"));

			purchaseEntryList = criteria.list();

		} catch (HibernateException e) {
			System.out.println(" getPurchaseDetails()  error");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return purchaseEntryList;
	}

	@Override
	public List<PurchaseEntry> getPurchaseDetails(long purchaseId) {
		// TODO Auto-generated method stub
		Session session = null;
		List<PurchaseEntry> purchaseEntryList = new ArrayList<PurchaseEntry>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(PurchaseEntry.class).add(Restrictions.eq("purchaseId", purchaseId));
			purchaseEntryList = criteria.list();

		} catch (HibernateException e) {
			System.out.println("String getPurchaseDetails() { error");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return purchaseEntryList;
	}

	@Override
	public int updatePurchaseEntry(PurchaseEntry purchaseEntry) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		int updatedEntities = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(purchaseEntry);
			tx.commit();

		} catch (Exception e) {
			System.out.println("updatePurchaseEntry error");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return updatedEntities;
	}

	@Override
	public int deletePurchaseEntry(long id) {
		int updatedEntities = 0;
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// session.get(PurchaseEntry.class, "purchaseId");
			// Query query =
			// session.createQuery("delete PurchaseEntry where purchaseId = :purchaseId");
			// query.setParameter("purchaseId", id);

			// int result = query.executeUpdate();

			// session.delete(purchaseEntry);
			System.out.println("% % % --------------------------- ");
			tx.commit();

		} catch (Exception e) {
			System.out.println("deletePurchaseEntry error");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return updatedEntities;
	}

	@Override
	public boolean deleteEntityById(Class<?> entity, long id) {
		// delete entity with id
		logger.info("service Delete Entity By Id (long)");
		Session session = null;
		Transaction tx = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Serializable sid = new Long(id);
			Object persistentInstance = session.get(entity, sid);
			if (persistentInstance != null) {
				session.delete(persistentInstance);
			}
			tx.commit();
			status = true;
		} catch (ConstraintViolationException e) {
			status = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;
	}

	@Override
	public boolean deleteEntityById(Class<?> entity, int id) {
		// delete entity with id
		logger.info("service Delete Entity By Id (int)");
		Session session = null;
		Transaction tx = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Serializable sid = new Integer(id);
			Object persistentInstance = session.get(entity, sid);
			if (persistentInstance != null) {
				session.delete(persistentInstance);
			}
			tx.commit();
			status = true;
		} catch (ConstraintViolationException e) {
			status = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;
	}

	@Override
	public boolean saveOrUpdateEntity(Object entity) {
		logger.info("service : save or update entity");
		Session session = null;
		Transaction tx = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			status = true;
		} catch (Exception e) {
			status = false;
			System.out.println("service : Error in save or update entity");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;
	}

	@Override
	public boolean deleteObject(Object persistentInstance) {
		// delete persistentInstance from db
		logger.info("service : deleteObject");
		Session session = null;
		Transaction tx = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			status = true;
		} catch (Exception e) {
			status = false;
			System.out.println("service : Error in delete Object");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List getAllRecords(Class entity) {
		// getting all records in entity from db
		logger.info("service : get all records in entity");
		Session session = null;
		List<?> recordList = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(entity);

			recordList = criteria.list();

		} catch (HibernateException e) {
			logger.error("Hibernate Error in service : getAllRecords");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error in service : getAllRecords");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return recordList;
	}

	@Override
	public Object getById(Class<?> entity, long id) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		Object persistentInstance = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			persistentInstance = session.get(entity, id);
			tx.commit();
		} catch (HibernateException e) {
			logger.error("Hibernate Error in service : getById");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error in service : getById");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persistentInstance;
	}

	@Override
	public Object getLastRecord(Class<?> entity, String property) {
		// To get last record from the entity
		Session session = null;
		Object persistentInstance = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(entity).addOrder(Order.desc(property)).setMaxResults(1);
			persistentInstance = criteria.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return persistentInstance;
	}

	public Object getPropertyValue(Class<?> entity, String property, String propertyId, Object id) {
		String value = "";
		Session session = null;
		Object result = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("b4 1  result " + id);
			Criteria criteria = session.createCriteria(entity);
			criteria.setProjection(Projections.property(property));
			criteria.add(Restrictions.eq(propertyId, id)).addOrder(Order.desc(propertyId)).setMaxResults(1);
			System.out.println("b4 result ");
			result = criteria.uniqueResult();

			if (result != null)
				value = result + "";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return value;
	}

	@Override
	public String getLastBillNo() {
		// TODO Auto-generated method stub
		Session session = null;
		String billNo = "";
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SalesEntry.class);

			criteria.setProjection(Projections.property("billNo")).addOrder(Order.desc("salesEntryId")).setMaxResults(1);

			// List list = criteria.list();
			//
			// if (list.size() > 0) {
			// billNo = (String) list.get(0);
			// }
			if (criteria.uniqueResult() != null) {
				billNo = criteria.uniqueResult() + "";
			}

			logger.info("---------------------------------------- bill no " + billNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return billNo;
	}
}
