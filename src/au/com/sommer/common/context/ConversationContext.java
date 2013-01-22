package au.com.sommer.common.context;

import java.util.List;

import au.com.sommer.common.event.EventListener;
import au.com.sommer.common.event.EventSource;
import au.com.sommer.common.util.data.DataSource;
import au.com.sommer.common.util.data.DataTarget;

public class ConversationContext extends ContextImpl {
	
	private boolean enabled;
	
	@Override
	protected void init() {
		enabled = false;
	}

	public void start() {
		enabled = true;
	}
	
	public void end() {
		enabled = false;
		clear();
	}

	@Override
	public boolean add(DataSource arg0) {
		if(!enabled) return false;
		return super.add(arg0);
	}

	@Override
	public boolean add(DataTarget<?> arg0) {
		if(!enabled) return false;
		return super.add(arg0);
	}

	@Override
	public boolean add(EventSource arg0) {
		if(!enabled) return false;
		return super.add(arg0);
	}

	@Override
	public boolean add(EventListener arg0) {
		if(!enabled) return false;
		return super.add(arg0);
	}

	@Override
	public void clear() {
		if(!enabled) throw new Exception();
		super.clear();
	}

	@Override
	public List<DataSource> getDataSources() {
		if(!enabled) return null;
		return super.getDataSources();
	}

	@Override
	public List<DataTarget<?>> getDataTargets() {
		if(!enabled) return null;
		return super.getDataTargets();
	}

	@Override
	public List<EventSource> getEventSources() {
		if(!enabled) return null;
		return super.getEventSources();
	}

	@Override
	public List<EventListener> getEventListeners() {
		if(!enabled) return null;
		return super.getEventListeners();
	}
	
	
}
