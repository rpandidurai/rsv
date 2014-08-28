/**
 * 
 */
package sathish.app.service;

import sathish.app.dto.UserDetail;

/**
 * @author EswaranKuppusamy
 *
 */
public interface LoginService {

	public abstract UserDetail loginAuthentication(UserDetail user);
}
