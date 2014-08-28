/**
 * 
 */
package sathish.app.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author root
 * 
 */
public class HibernateSessionFactoryListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger("RSVtraders");

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		logger.info("Initialize Context");
		SessionFactory sessionFactory;
		ServiceRegistry serviceRegistry;

		Configuration configuration = new Configuration();
		configuration.configure();

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		logger.info("SessionFactory created successfully");

		servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);

		logger.info("Hibernate SessionFactory Configured successfully");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		logger.info("Destroy Context");
		SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			logger.info("Closing sessionFactory");
			sessionFactory.close();
		}
		logger.info("Released Hibernate sessionFactory resource");
	}

}
