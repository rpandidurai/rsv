/**
 * 
 */
package sathish.app.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.*;
/**
 * @author root
 * 
 */
public class HibernateSessionFactoryListener implements ServletContextListener {

	private static final Logger logger = LogManager.getLogger("RSVtraders");

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		logger.debug("Initialize Context");
		SessionFactory sessionFactory;
		ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
		configuration.configure();

//		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		logger.debug("SessionFactory created successfully");

		servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);

		logger.debug("Hibernate SessionFactory Configured successfully");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		logger.debug("Destroy Context");
		SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			logger.debug("Closing sessionFactory");
			sessionFactory.close();
		}
		logger.debug("Released Hibernate sessionFactory resource");
	}

}
