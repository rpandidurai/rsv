/**
 * 
 */
package sathish.app.interceptors;

import sathish.app.dto.UserDetail;

/**
 * @author root
 * 
 */
public interface UserAware {

	public void setUser(UserDetail user);
}
