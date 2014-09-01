package rs.tfzr.FudbalT2.web.validator;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Comment;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.web.dto.CommentDTO;

@Component
public class CommentValidator implements Validator
{
	private final String COMMENT_NOT_AVAILABLE = "page.comment.validation.notAvailable";
	private final String BODY_NULL = "page.comment.validation.bodyNull";
	private final String TITLE_NULL = "page.comment.validation.titleNull";
	@Override
	public boolean supports(Class<?> clazz) {
		return CommentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{	
		if(target != null && supports(target.getClass()))
		{
			CommentDTO comment = (CommentDTO)target;
			if(comment.getBody().isEmpty())
			{
				System.out.println("BODY_NULL");
				errors.rejectValue("body", BODY_NULL);
			}
			if(comment.getTitle().isEmpty())
			{
				System.out.println("TITLE_NULL");
				errors.rejectValue("title", TITLE_NULL);
			}
		}
		else if(target == null)
		{
			System.out.println("COMMENT_NOT_AVAILABLE");
			errors.reject(COMMENT_NOT_AVAILABLE);
		}
	}

}
