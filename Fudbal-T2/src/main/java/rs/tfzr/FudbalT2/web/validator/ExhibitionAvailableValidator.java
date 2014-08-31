package rs.tfzr.FudbalT2.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Exhibition;

@Component
public class ExhibitionAvailableValidator implements Validator
{
	private final String EXHIBITION_DOES_NOT_EXIST = "page.exhibition.validation.exhibitionDoesNotExist";
	private final String EXHIBITION_MUST_BE_OVER = "page.mvp.validation.exhibitionMustBeOver";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Exhibition.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target == null)
			errors.reject(EXHIBITION_DOES_NOT_EXIST);
		
		if(target != null && supports(target.getClass()))
		{
			Exhibition exhibition = (Exhibition) target;
			if(!exhibition.getEnded())
				errors.reject(EXHIBITION_MUST_BE_OVER);
		}
	}

}
