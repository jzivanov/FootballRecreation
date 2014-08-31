package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Comment;
import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.CommentService;

@Service
public class InMemoryCommentService extends AbstractInMemoryService<Comment> implements CommentService
{	
	@Override
	public Map<Comment, List<Comment>> getCommentsForExhibition(
			Long exhibitionId) 
	{
		Map<Comment, List<Comment>> map = new HashMap<Comment, List<Comment>>();
		for(Comment comm: this.findAll())
		{
			if(comm.getExhibition().getId() == exhibitionId)
			{
				if(comm.getMainComment() == null)
					map.put(comm, commentsForComment(comm.getId()));
			}
		}
		return map;
	}

	private List<Comment> commentsForComment(Long commentId)
	{
		List<Comment> commRes = new ArrayList<Comment>();
		for(Comment comm: findAll())
		{
			if(comm.getMainComment() != null && comm.getMainComment().getId() != commentId)
				commRes.add(comm);
		}
		return commRes;
	}
}
