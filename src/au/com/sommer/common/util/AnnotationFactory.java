package au.com.sommer.common.util;

import java.lang.annotation.Annotation;

public class AnnotationFactory {

	/**
	 * Return annotation object
	 * @param annotatedClass Class containing the annotation.
	 * @param annotationName full package and class name of annotation.
	 * @return annotation object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Annotation getInstance(Object annotatedObject, String annotationName) {
		Annotation annotation = null;
		Class annotationClass;
		try {
			annotationClass = Class.forName(annotationName);
			annotation = (Annotation)annotatedObject.getClass().getAnnotation(annotationClass);
		} catch (Exception e) {
			Log.error(AnnotationFactory.class, "Error retrieving annotation: " + annotationName + " from annotatedObject: " + annotatedObject + " - error: " + e.getMessage());		
			e.printStackTrace();
		}
		return annotation;
	}
	
}