/**
 * 
 */
package sathish.app.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;

import sathish.app.service.DBConnection;
import sathish.app.action.UserAction;
import sathish.app.dto.ProductDetails;
import sathish.app.dto.PurchaseDetails;
import sathish.app.dto.PurchaseEntry;
import sathish.app.dto.SalesDetails;
import sathish.app.dto.SalesEntry;
import sathish.app.dto.UserDetail;

/**
 * @author EswaranKuppusamy
 * 
 */
public class LoginServiceManager implements LoginService {
	private Map<String, Object> session;
	// private HttpServletRequest request;
	private SessionFactory sessionFactory;

	public LoginServiceManager() {
		// TODO Auto-generated constructor stub
	}

	public LoginServiceManager(Map<String, Object> session) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = (SessionFactory) session.get("SessionFactory");
	}

	// SessionFactory sessionFactory = (SessionFactory)
	// request.getServletContext().getAttribute("SessionFactory");

	@Override
	public UserDetail loginAuthentication(UserDetail user) {
		// TODO Auto-generated method stub
		// Connection con = null;
		// CallableStatement st = null;
		// ResultSet rs = null;
		// try {
		//
		// con = DBConnection.makeConnection();
		// st = con.prepareCall("select * from test");
		// rs = st.executeQuery();
		//
		// if (rs.next()) {
		// System.out.println("worked ");
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return user;

		Session hsession = sessionFactory.openSession();
		Transaction tx = null;
		UserDetail loggedUser = null;
		try {
			tx = hsession.beginTransaction();
			List<UserDetail> userDetails = hsession.createQuery("FROM UserDetail").list();
//			hsession.createCriteria(UserDetail.class).add(CriteriaQuery<UserDetail.class>.)
			//List<UserDetail> userDetails = hsession.createCriteria(UserDetail.class).add(Restrictions.eq("userName", user.getUserName())).list(); // .createQuery("FROM UserDetail")..list();
			for (Iterator iterator = userDetails.iterator(); iterator.hasNext();) {
				UserDetail userDetail = (UserDetail) iterator.next();
				if (user.getUserName().equals(userDetail.getUserName())) {
					if (user.getPassWord().equals(userDetail.getPassWord())) {
						loggedUser = new UserDetail();
						loggedUser.setUserId(userDetail.getUserId());
						loggedUser.setUserName(userDetail.getUserName());
						loggedUser.setUserName(userDetail.getPassWord());
						loggedUser.setName(userDetail.getName());
					}
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("String chkH() { error");
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			hsession.close();
		}

		return loggedUser;

	}

	public void chkList() {
		System.out.println("*********** in purchse chk");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			PurchaseEntry pEntry = new PurchaseEntry();

			pEntry.setInvoiceDate(new Date());
			pEntry.setInvoiceNo("1111");
			pEntry.setTotalAmount(1000);
			pEntry.setTotalQuantity(10);

			PurchaseDetails pD1 = new PurchaseDetails();
			pD1.setAmount(5);
			// pD1.setProductDescription("this is a desc");
			pD1.setPurchseQuantity(2);
			pD1.setSerialNoFrom(1);
			pD1.setSerialNoTo(3);
			pD1.setUnitRate(10);

			PurchaseDetails pD2 = new PurchaseDetails();
			pD2.setAmount(8);
			// pD2.setProductDescription("this is a desc 2");
			pD2.setPurchseQuantity(3);
			pD2.setSerialNoFrom(68);
			pD2.setSerialNoTo(30);
			pD2.setUnitRate(68);

			// pD1.setPurchaseEntry(pEntry);

			pEntry.getPurchaseDetailsList().add(pD1);
			pEntry.getPurchaseDetailsList().add(pD2);

			tx = session.beginTransaction();
			session.save(pEntry);
			// session.save(pD1);
			// hsession.save(salesEntry.getSalesDetailsList().get(0));
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in chk list");
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void chkList(SalesEntry salesEntry) {
		Session hsession = sessionFactory.openSession();
		Transaction tx = null;
		try {

			// System.out.println(salesEntry.getBillNo());
			System.out.println(salesEntry.getSalesEntryId());
			System.out.println(salesEntry.getEntryDate());
			// System.out.println(((SalesDetails)salesEntry.getSalesDetailsList().get(0)).getSalesId());
			// System.out.println(((SalesDetails)salesEntry.getSalesDetailsList().get(0)).getSalesEntryId());

			// System.out.println(((SalesDetails)salesEntry.getSalesDetailsList().get(1)).getSalesId());
			// System.out.println(((SalesDetails)salesEntry.getSalesDetailsList().get(1)).getSalesEntryId());

			tx = hsession.beginTransaction();

			// userAction.getSalesDetailsList().get(0).setSalesEntryId((Integer)hsession.save(userAction.getSalesEntry()));
			// System.out.println("salesEntry "+salesEntry.getSalesDetailsList());
			hsession.save(salesEntry);
			// hsession.save(salesEntry.getSalesDetailsList().get(0));
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in chk list");
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			hsession.close();
		}
	}

	public String chkH() {
		Session hsession = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = hsession.beginTransaction();
			List<ProductDetails> productList = hsession.createQuery("FROM ProductDetails").list();
			for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
				ProductDetails productDetail = (ProductDetails) iterator.next();
				System.out.println("ProductDetails Name: " + productDetail.getProductName());
			}
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("String chkH() { error");
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			hsession.close();
		}

		return "";
	}

}
