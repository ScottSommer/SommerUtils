package au.com.sommer.common.util.data;

import au.com.sommer.common.service.interfaces.Incubation;
import au.com.sommer.common.validate.Validatable;

/**
 * A DataSource will return data when getData is called. The implementation determines how that 
 * data is fetched. This is useful for indirectly referencing data, or data that does not yet 
 * exist at configuration time. 
 * 
 * @author a1613564
 *
 */
@Incubation
public interface DataSource extends Validatable {
	public Object getData() throws DataSourceException;
}
