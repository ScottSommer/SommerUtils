package au.com.sommer.common.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Log {

	public static void info(Class<?> classSource, Object oMessage) {
		Logger.getLogger(classSource).info(oMessage);
	}

	public static void debug(Class<?> classSource, Object oMessage) {
		Logger.getLogger(classSource).debug(oMessage);
	}

	public static void warn(Class<?> classSource, Object oMessage) {
		Logger.getLogger(classSource).warn(oMessage);
	}

	public static void warn(Class<?> classSource, Object oMessage, Throwable cause) {
		Logger.getLogger(classSource).warn(oMessage, cause);
	}

	public static void error(Class<?> classSource, Object oMessage) {
		Logger.getLogger(classSource).error(oMessage);
	}

	public static void error(Class<?> classSource, Object oMessage, Throwable cause) {
		Logger.getLogger(classSource).error(oMessage, cause);
	}

	public static void fatal(Class<?> classSource, Object oMessage) {
		Logger.getLogger(classSource).fatal(oMessage);
	}

	public static void fatal(Class<?> classSource, Object oMessage, Throwable cause) {
		Logger.getLogger(classSource).fatal(oMessage, cause);
	}

	public static Logger getLogger(Class<?> classSource) {
		return Logger.getLogger(classSource);
	}
	
	public static boolean isDebugEnabled(Class<?> classSource) {
		return Logger.getLogger(classSource).isDebugEnabled();
	}		
	
	public static void printList(Class<?> classSource, List<Object> list) {
		for (Iterator<Object> i = list.iterator();i.hasNext();) {
			debug(classSource,i.next());
		}
	}
	
	public static void printMap(Class<?> classSource, Map<Object,Object> map) {
		Object key;
		for (Iterator<Object> i = map.keySet().iterator();i.hasNext();) {
			key = i.next();
			debug(classSource,key + ":" + map.get(key));
		}
	}
	
}
