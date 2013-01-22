package au.com.sommer.common.util.data;

import au.com.sommer.common.service.interfaces.Incubation;

/**
 * NOT YET IMPLEMENTED
 * 
 * Gets data using a controller.
 * 
 * Need to get a reference to the application to be able to implement this. Needs to be moved
 * to the framework project as it will be referencing BaseApplication. To get the Application
 * object an ideal solution would be to define the Application object in the SpringContext. 
 * That way it could be passed in to this via a setter as per normal bean args.
 * 
 * @author a1613564
 *
 */
@Incubation
public class ControllerDataSource extends AbstractDataSource {

	private String controllerName;
	private String action;
	
	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
