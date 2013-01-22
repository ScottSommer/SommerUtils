package au.com.sommer.common.validate;

import java.util.List;

public interface Validatable {
	public ValidationCode validate();
	public List<Validator> getValidators();
	public void addValidator(Validator validator);
	public void removeValidator(Validator validator);
}
