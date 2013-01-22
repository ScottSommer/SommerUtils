package au.com.sommer.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.sommer.common.event.EventListener;
import au.com.sommer.common.event.EventSource;
import au.com.sommer.common.util.data.DataSource;
import au.com.sommer.common.util.data.DataTarget;

public class ContextImpl implements Context {

	Map<String, List<DataSource>> dataSources;
	Map<String, List<DataTarget<?>>> dataTargets;
	Map<String, List<EventSource>> eventSources;
	Map<String, List<EventListener>> eventListeners;
	
	public ContextImpl() {
		init();
	}
	
	protected void init() {
		dataSources = new HashMap<String, List<DataSource>>();
		dataTargets = new HashMap<String, List<DataTarget<?>>>();
		eventSources = new HashMap<String, List<EventSource>>();
		eventListeners = new HashMap<String, List<EventListener>>();
		
//		dataSources = new ArrayList<DataSource>();
//		dataTargets = new ArrayList<DataTarget<?>>();
//		eventSources = new ArrayList<EventSource>();
//		eventListeners = new ArrayList<EventListener>();
	}
	
	public boolean add(DataSource arg0) {
		return add(null, arg0);
	}
	
	public boolean add(String group, DataSource arg0) {
		boolean ret;
		List<DataSource> ds = dataSources.get(group);
		
		if(ds == null) {
			ds = new ArrayList<DataSource>();
			dataSources.put(null, ds);
		}
		ret = ds.add(arg0);
		
		//TODO: Check that it exists in the map's list.
		return ret;
	}
	
	public boolean add(DataTarget<?> arg0) {
		return add(null, arg0);
	}

	public boolean add(String group, DataTarget<?> arg0) {
		boolean ret;
		List<DataTarget<?>> dt = dataTargets.get(group);
		
		if(dt == null) {
			dt = new ArrayList<DataTarget<?>>();
			dataTargets.put(null, dt);
		}
		ret = dt.add(arg0);
		
		//TODO: Check that it exists in the map's list.
		return ret;
	}
	
	public boolean add(EventSource arg0) {
		return add(null, arg0);
	}

	public boolean add(String group, EventSource arg0) {
		boolean ret;
		List<EventSource> es = eventSources.get(group);
		
		if(es == null) {
			es = new ArrayList<EventSource>();
			eventSources.put(null, es);
		}
		ret = es.add(arg0);
		
		//TODO: Check that it exists in the map's list.
		return ret;
	}
	
	public boolean add(EventListener arg0) {
		return add(null, arg0);
	}

	public boolean add(String group, EventListener arg0) {
		boolean ret;
		List<EventListener> et = eventListeners.get(group);
		
		if(et == null) {
			et = new ArrayList<EventListener>();
			eventListeners.put(null, et);
		}
		ret = et.add(arg0);
		
		//TODO: Check that it exists in the map's list.
		return ret;
	}

	public void clear() {
		dataSources.clear();
		dataTargets.clear();
		eventSources.clear();
		eventListeners.clear();
	}

	public List<DataSource> getDataSources(String group) {
		return dataSources.get(group);
	}
	public List<DataTarget<?>> getDataTargets(String group) {
		return dataTargets.get(group);
	}
	public List<EventSource> getEventSources(String group) {
		return eventSources.get(group);
	}
	public List<EventListener> getEventListeners(String group) {
		return eventListeners.get(group);
	}

	@Override
	public void synchronize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() {
		
	}
}
