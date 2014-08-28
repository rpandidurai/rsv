/**
 * 
 */
package sathish.app.interceptors;

import java.util.Map;

import sathish.app.dto.UserDetail;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author root
 * 
 */
public class AuthenticationInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6908384540098981277L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("in authentication interceptors invoke method");

		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();

		UserDetail user = (UserDetail) session.get("USER");

		if (user == null) {
			System.out.println("login authentication failed...");
			return Action.LOGIN;
		} else {
			Action action = (Action) actionInvocation.getAction();
			if (action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}
			System.out.println("authenticated user access");
		}
		return actionInvocation.invoke();
	}

}
