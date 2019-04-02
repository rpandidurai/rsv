/**
 * 
 */
package sathish.app.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.SessionFactory;

import sathish.app.dto.UserDetail;
import sathish.app.interceptors.UserAware;
import sathish.app.service.LoginService;
import sathish.app.service.LoginServiceManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * @author ps
 * 
 */
public class LoginAction extends ActionSupport implements UserAware, SessionAware, ServletRequestAware, Preparable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -264520247252022735L;
	private static final Logger logger = LogManager.getLogger("RSVtraders");

	private Map<String, Object> session;
	private HttpServletRequest request;

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		logger.info("-------------Preparing--------------");

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public LoginAction() {
		System.out.println("in Login Action");
	}

	public String login() {
		logger.info("in Login Action");
		// new Utilities().generateBillNo();
		session.put("SessionFactory", (SessionFactory) request.getServletContext().getAttribute("SessionFactory"));
		LoginService login = new LoginServiceManager(session);
		// UserDetail user = new UserDetail();
		if (session.get("SessionFactory") != null && session.get("USER") != null) {
			System.out.println("first");
			return "SUCCESS";

		} else if (this.user != null) {
			UserDetail userLogged = login.loginAuthentication(this.user);
			if (userLogged != null) {
				session.put("userId", userLogged.getUserId());
				session.put("userName", userLogged.getName());
				session.put("userUName", userLogged.getUserName());

				session.put("USER", user);
				System.out.println("Two");
				return "SUCCESS";
			} else {
				System.out.println("Three");
				return "FAIL";
			}

		}
		System.out.println("End");
		return "SUCCESS";
	}

	public String logout() {
		logger.info("logging out...");
		if (this.session != null) {
			this.session.clear();
			logger.info("session destroyed...");
		}

		return "SUCCESS";
	}

	private UserDetail user;

	public UserDetail getUser() {
		return user;
	}

	public void setUser(UserDetail user) {
		this.user = user;
	}

}
