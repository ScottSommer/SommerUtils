package au.com.sommer.common.filter;

/**
 * 
 * 
	<bean id="equalFilter" class="au.edu.adelaide.dmac.common.filter.Compare.Equal" scope="prototype"/>
	<bean id="notFilter" class="au.edu.adelaide.dmac.common.filter.Not" scope="prototype"/>
	
	<bean id="onNoFilter" parent="equalFilter">
		<property name="value" ref="YESNO_NO"/>
	</bean>
	
	<bean id="onYesFilter" parent="equalFilter">
		<property name="value" ref="YESNO_YES"/>
	</bean>
	
	<bean id="onNotNoFilter" parent="notFilter">
		<property name="filter" ref="onNoFilter"/>
	</bean>
	
	<bean id="onNotYesFilter" parent="notFilter">
		<property name="filter" ref="onYesFilter"/>
	</bean>
	
 * @author a1613564
 *
 */
public abstract class ValueFilter {
	public abstract boolean passesFilter(Object value);
	public abstract boolean appliesToValue(Object value);
	public void init() {
		
	}
}
