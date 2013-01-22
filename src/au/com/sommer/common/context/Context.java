package au.com.sommer.common.context;

import java.util.List;

import au.com.sommer.common.event.EventListener;
import au.com.sommer.common.event.EventSource;
import au.com.sommer.common.util.data.DataSource;
import au.com.sommer.common.util.data.DataTarget;

public interface Context {

	public List<DataSource> getDataSources(String group);
	public List<DataTarget<?>> getDataTargets(String group);
	public List<EventSource> getEventSources(String group);
	public List<EventListener> getEventListeners(String group);
	
	public void synchronize();
	public void validate();
}
