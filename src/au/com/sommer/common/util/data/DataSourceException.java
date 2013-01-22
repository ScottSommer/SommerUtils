package au.com.sommer.common.util.data;

import au.com.sommer.common.service.interfaces.Incubation;

@Incubation
public class DataSourceException extends Exception {

	public DataSourceException() {
		super();
	}

	public DataSourceException(String message) {
		super(message);
	}

	public DataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceException(Throwable cause) {
		super(cause);
	}
}
