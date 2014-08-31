package rs.tfzr.FudbalT2.web.validator;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Comment;
import rs.tfzr.FudbalT2.model.User;

@Component
public class CommentValidator implements Validator
{
	private final String COMMENT_NOT_AVAILABLE = "page.comment.validation.notAvailable";
	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{	
		if(target != null && supports(target.getClass()))
		{
			Comment comment = (Comment)target;
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(comment.getUser().getId() != user.getId())
			{
				errors.reject(COMMENT_NOT_AVAILABLE);
			}
		}
		else if(target == null)
			errors.reject(COMMENT_NOT_AVAILABLE);
	}

}
