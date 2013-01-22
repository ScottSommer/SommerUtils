package au.com.sommer.common.util.data;

import java.lang.reflect.Method;
import java.util.List;

import au.com.sommer.common.service.interfaces.Incubation;
import au.com.sommer.common.util.ReflectionUtils;

@Incubation
public class BeanPropertyDataSource extends AbstractDataSource {

	private Object bean;
	private String beanPropertyId;
	private List<String> args;
	
	@Override
	public Object getData() throws DataSourceException {
		Object data = null;
		
		try {
			Method method = ReflectionUtils.getMethod(bean, beanPropertyId, args.toArray());
			data = method.invoke(bean, args);
		} catch (Exception e) {
			throw new DataSourceException(e);
		}
		
		return data;
	}

	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}

	public String getBeanPropertyId() {
		return beanPropertyId;
	}
	public void setBeanPropertyId(String beanPropertyId) {
		this.beanPropertyId = beanPropertyId;
	}

	public List<String> getArgs() {
		return args;
	}
	public void setArgs(List<String> args) {
		this.args = args;
	}
}
