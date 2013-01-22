package au.com.sommer.common.util.data;

import java.lang.reflect.Method;

import au.com.sommer.common.service.interfaces.Incubation;
import au.com.sommer.common.validate.ValidationCode;
import au.com.sommer.common.validate.Validator;

@Incubation
public class MethodDataSource extends AbstractDataSource {

	private Method method;
	private Object target;
	private Object[] args;
	
	@Override
	public Object getData() throws DataSourceException {
		Object data = null;
		
		try {
			data = method.invoke(target, args);
		} catch (Exception e) {
			throw new DataSourceException(e);
		}
		
		return data;
	}

	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}

	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}

	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}

}
