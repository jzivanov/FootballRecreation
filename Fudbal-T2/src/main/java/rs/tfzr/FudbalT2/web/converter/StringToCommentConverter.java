package rs.tfzr.FudbalT2.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import rs.tfzr.FudbalT2.model.Comment;
import rs.tfzr.FudbalT2.service.CommentService;

public class StringToCommentConverter implements Converter<String, Comment>
{
	@Autowired
	private CommentService commentService;
	
	@Override
	public Comment convert(String commentId) {
		return commentService.findOne(Long.parseLong(commentId));
	}

}
