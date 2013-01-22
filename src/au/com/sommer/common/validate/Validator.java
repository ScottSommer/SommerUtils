package au.com.sommer.common.validate;

import au.com.sommer.common.filter.ValueFilter;

public class Validator {
	
	private ValueFilter filter;
	private ValidationCode passLevel;
	private ValidationCode failLevel;

	public Validator() {
		
	}
	
	public Validator(ValueFilter filter) {
		this.passLevel = ValidationCode.PASS;
		this.failLevel = ValidationCode.FAIL;
		this.filter = filter;
	}
	
	public Validator(ValidationCode passLevel, ValidationCode failLevel, ValueFilter filter) {
		this.passLevel = passLevel;
		this.failLevel = failLevel;
		this.filter = filter;
	}

	public ValueFilter getFilter() {
		return filter;
	}

	public void setFilter(ValueFilter filter) {
		this.filter = filter;
	}
	
	public ValidationCode getPassLevel() {
		return passLevel;
	}

	public void setPassLevel(ValidationCode passLevel) {
		this.passLevel = passLevel;
	}

	public ValidationCode getFailLevel() {
		return failLevel;
	}

	public void setFailLevel(ValidationCode failLevel) {
		this.failLevel = failLevel;
	}

	public ValidationCode validate(Object value) {
		return filter.passesFilter(value) ? passLevel : failLevel;
	}
}
