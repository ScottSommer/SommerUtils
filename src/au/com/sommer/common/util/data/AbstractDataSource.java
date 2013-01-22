package au.com.sommer.common.util.data;

import java.util.ArrayList;
import java.util.List;

import au.com.sommer.common.validate.ValidationCode;
import au.com.sommer.common.validate.Validator;

public abstract class AbstractDataSource implements DataSource {

	protected List<Validator> validators;
	
	public AbstractDataSource() {
		validators = new ArrayList<Validator>();
	}
	
	@Override
	public List<Validator> getValidators() {
		return validators;
	}

	@Override
	public void addValidator(Validator validator) {
		validators.add(validator);
	}

	@Override
	public void removeValidator(Validator validator) {
		validators.remove(validator);
	}

	@Override
	public ValidationCode validate() {
		ValidationCode validationLevel;
		
		try {
			Object data = getData();
			
			validationLevel = ValidationCode.PASS;
			for(Validator validator : validators) {
				ValidationCode level = validator.validate(data);
				// TODO: Need to select the worst validation level.
				validationLevel = level > validationLevel ? level : validationLevel;
			}
		} catch (DataSourceException e) {
			validationLevel = ValidationCode.FAIL;
		}
		
		return validationLevel;
	}
}
