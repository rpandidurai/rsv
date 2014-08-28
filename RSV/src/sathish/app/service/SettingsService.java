/**
 * 
 */
package sathish.app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import sathish.app.dto.Company;
import sathish.app.dto.ProductGroup;

/**
 * @author root
 * 
 */
public class SettingsService implements Settings {

	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger("RSVtraders");

	public SettingsService(Map<String, Object> session) {
		this.sessionFactory = (SessionFactory) session.get("SessionFactory");
	}

	@Override
	public boolean addCompany(Company company) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addGroup(ProductGroup productGroup) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdateEntity(Object entity) {
		logger.info("SettingsService : save or update entity");
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
			System.out.println("SettingsService : Error in save or update entity");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;
	}

	@Override
	public List getAllRecords(Class entity) {
		// getting all records in entity from db
		logger.info("SettingsService : get all records in entity");
		Session session = null;
		List<?> recordList = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(entity);

			recordList = criteria.list();

		} catch (HibernateException e) {
			logger.error("Hibernate Error in SettingsService : getAllRecords");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error in SettingsService : getAllRecords");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return recordList;
	}

	@Override
	public Object getById(Class<?> entity, int id) {
		logger.info("SettingsService : get records by id");
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
}
