/**
 * 
 */
package sathish.app.service;

import java.util.List;

import sathish.app.dto.Company;
import sathish.app.dto.ProductGroup;

/**
 * @author root
 * 
 */
public interface Settings {

	public boolean addGroup(ProductGroup productGroup);

	public boolean addCompany(Company company);

	public List getAllRecords(Class entity);

	public Object getById(Class<?> entity, int id);

	public boolean saveOrUpdateEntity(Object entity);

	public boolean deleteEntityById(Class<?> entity, int id);
}
