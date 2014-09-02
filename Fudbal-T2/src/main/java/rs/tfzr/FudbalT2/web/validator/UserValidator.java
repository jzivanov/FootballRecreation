package rs.tfzr.FudbalT2.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;
import rs.tfzr.FudbalT2.web.dto.UserDTO;

@Component
public class UserValidator implements Validator
{
	private final String EMPTY_FIELD = "page.register.validation.empty";
	private final String USER_EXISTS = "page.register.validation.userExists";
	private final String PASS_LENGTH = "page.register.validation.passLength";
	private final String PASS_NOT_MATCH = "page.register.validation.passNotMatch";
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target != null && supports(target.getClass()))
		{
			UserDTO user = (UserDTO)target;
			if(user.getEmail() == null || user.getEmail().isEmpty())
				errors.reject(EMPTY_FIELD, "erremail");
			else if(userService.loadUserByUsername(user.getEmail()) == null)
				errors.reject(USER_EXISTS, "erremail");
			//Check email format
			if(user.getPassword().isEmpty())
				errors.reject(EMPTY_FIELD, "errpassword");
			else if(user.getPassword().length() < 4)
				errors.reject(PASS_LENGTH, "errpassword");
			else if(!user.getPassword().equals(user.getRepeatPassword()))
				errors.reject(PASS_NOT_MATCH, "errpassword");
			if(user.getFirstName().isEmpty())
				errors.reject(EMPTY_FIELD, "errfirstName");
			if(user.getLastName().isEmpty())
				errors.reject(EMPTY_FIELD, "errlastName");
		}
	}

}
