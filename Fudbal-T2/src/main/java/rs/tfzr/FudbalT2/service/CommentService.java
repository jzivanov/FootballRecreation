package rs.tfzr.FudbalT2.service;

import java.util.List;
import java.util.Map;

import rs.tfzr.FudbalT2.model.Comment;

public interface CommentService extends CrudService<Comment>
{
	Map<Comment, List<Comment>> getCommentsForExhibition(Long exhibitionId);
}
