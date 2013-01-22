package au.com.sommer.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import au.com.sommer.common.domain.DMACBean;

public class ReflectionUtils {

	public static String invokeString(Object targetObject,String methodName) {
		return (String)invoke(targetObject,methodName, null);
	}
	public static Integer invokeInteger(Object targetObject,String methodName) {
		return (Integer)invoke(targetObject,methodName, null);
	}	
	public static Boolean invokeBoolean(Object targetObject,String methodName) {
		return (Boolean)invoke(targetObject,methodName, null);
	}	

	public static Date invokeDate(Object targetObject,String methodName) {
		return (Date)invoke(targetObject,methodName, null);
	}	
	public static Date invokeDate(Object targetObject,String methodName, Object[] parameters) {
		return (Date)invoke(targetObject,methodName, parameters);
	}	

	public static String invokeString(Object targetObject,String methodName, Object[] parameters) {
		return (String)invoke(targetObject,methodName, parameters);
	}
	public static Integer invokeInteger(Object targetObject,String methodName, Object[] parameters) {
		return (Integer)invoke(targetObject,methodName, parameters);
	}
	public static Boolean invokeBoolean(Object targetObject,String methodName, Object[] parameters) {
		return (Boolean)invoke(targetObject,methodName, parameters);
	}

	public static Object invoke(Object targetObject,String methodName) {
		return invoke(targetObject,methodName, null);
	}

	public static Method getMethod(Object targetObject,String methodName, Object[] parameters) throws SecurityException, NoSuchMethodException  {
		Method method = targetObject.getClass().getMethod(methodName);
		if (parameters == null || parameters.length == 0) {
			method = targetObject.getClass().getMethod(methodName);
		} else {
			Class<?>[] argumentTypes = getArgumentTypes(parameters);
			method = targetObject.getClass().getMethod(methodName,argumentTypes);
		}
		return method;
	}
	
	public static boolean hasMethod(Object targetObject,String methodName, Object[] parameters) {
		try {
			if(parameters == null) {
				targetObject.getClass().getMethod(methodName);
			} else {
				Class<?>[] argumentTypes = getArgumentTypes(parameters);
				targetObject.getClass().getMethod(methodName,argumentTypes);
			}
			
			return true;
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
	
	public static Object invoke(Object targetObject,String methodName, Object[] parameters) {
		return ReflectionUtils.invoke(targetObject, methodName, parameters, null);
	}

	public static Object invoke(Object targetObject,String methodName, Object[] parameters, Class<?>[] clazzes) {

		Object result = null;
	
		try {
			if (parameters == null || parameters.length == 0) {
				Method method = targetObject.getClass().getMethod(methodName);
				result = method.invoke(targetObject);
			} else {
				Method method;
				if(clazzes == null) {
					Class<?>[] argumentTypes = getArgumentTypes(parameters);
					method = targetObject.getClass().getMethod(methodName,argumentTypes);
				} else {
					method = targetObject.getClass().getMethod(methodName,clazzes);
				}
				result = method.invoke(targetObject, parameters);
			}
		} catch (NoSuchMethodException nsm) {
			try {
				clazzes = new Class<?>[parameters.length];
				java.util.Arrays.fill(clazzes, DMACBean.class);
				result = targetObject.getClass().getMethod(methodName,clazzes).invoke(targetObject,parameters);
			} catch(NoSuchMethodError nsm2){
				// Continue with nsm exception, wasn't able to find a DMACBean argument type alternative.
				Log.error(ReflectionUtils.class, nsm.getMessage());			
				if (targetObject.getClass() == null) {
					Log.error(ReflectionUtils.class, "object->class:" + targetObject.getClass());
				} else {
					Log.error(ReflectionUtils.class, "object->class->name:" + targetObject.getClass().getName());				
				}
				Log.error(ReflectionUtils.class, "method:" + methodName);
				if (parameters != null) {
					for (int i = 0 ; i < parameters.length;i++) {
						Log.debug(ReflectionUtils.class,parameters[i].getClass() + ":" + parameters[i]);
					}			
				}
				nsm.printStackTrace();
			} catch (Exception e) {
				Log.error(ReflectionUtils.class, e.getMessage());			
				e.printStackTrace();
			}
		} catch (InvocationTargetException ite) {
			Log.error(ReflectionUtils.class, ite.getMessage());			
			ite.printStackTrace();
		} catch (IllegalAccessException iac) {
			Log.error(ReflectionUtils.class, iac.getMessage());			
			iac.printStackTrace();
		} catch (Exception e) {
			Log.error(ReflectionUtils.class, e.getMessage());			
			e.printStackTrace();
		}
		return result;
	}
	
	
	@SuppressWarnings("unused")
	private static void logError(Object targetObject,String methodName, Object[] parameters,Exception e) {
		
		
		/*
			if (targetObject.getClass() == null) {
				Log.error(ReflectionUtils.class, "object->class:" + targetObject.getClass());
			} else {
				Log.error(ReflectionUtils.class, "object->class->name:" + targetObject.getClass().getName());				
			}
			Log.error(ReflectionUtils.class, "method:" + methodName);
			if (parameters != null) {
				for (int i = 0 ; i < parameters.length;i++) {
					Log.debug(ReflectionUtils.class,parameters[i].getClass() + ":" + parameters[i]);
				}			
			}
		 
		 */
		
		
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("targetObject: ");
		errorMessage.append(targetObject);
		errorMessage.append("\n");
		
		if (targetObject != null && targetObject.getClass() != null) {
			errorMessage.append("targetObject-class: ");
			errorMessage.append(targetObject.getClass().getName());
			errorMessage.append("\n");			
		}

		errorMessage.append("methodName: ");
		errorMessage.append(methodName);
		errorMessage.append("\n");
		errorMessage.append("parameters: ");
		errorMessage.append(parameters);
		errorMessage.append("\n");
		if (parameters != null) {
			for (int i = 0 ; i < parameters.length;i++) {
				errorMessage.append("parameter: ");
				if (parameters[i] != null) {
					errorMessage.append(parameters[i].getClass());
				}
				errorMessage.append(" : ");
				errorMessage.append(parameters[i]);
				errorMessage.append("\n");
			}
		}
		errorMessage.append("exception: " + e.getMessage());
		
		Log.error(ReflectionUtils.class,errorMessage.toString());
		e.printStackTrace();
		
	}
	
	private static Class<?>[] getArgumentTypes(Object[] parameters) {	
		Class<?>[] argumentTypes = new Class[parameters.length];
		for (int i = 0 ; i < parameters.length; i++) {
			argumentTypes[i] = parameters[i].getClass();
		}
		return argumentTypes;
	}

	public static String asGetter(String property) {
		return "get" + property.substring(0,1).toUpperCase() + property.substring(1, property.length());
	}
}
