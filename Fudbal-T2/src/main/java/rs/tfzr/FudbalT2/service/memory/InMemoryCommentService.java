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
	public InMemoryCommentService()
	{
		Exhibition exhibition1 = new Exhibition();
		exhibition1.setId(1L);
		Exhibition exhibition2 = new Exhibition();
		exhibition1.setId(2L);
		Exhibition exhibition3 = new Exhibition();
		exhibition1.setId(3L);
		
		User user1 = new User();
		user1.setId(1L);
		user1.setEmail("zjovan.ost@gmail.com");
		user1.setFirstName("jovan");
		user1.setLastName("zivanov");
		user1.setEmail("email");
		user1.setPassword("df");
		
		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("pera@kojot.supergenije");
		user2.setFirstName("pera kojot");
		user2.setLastName("super genije");
		user2.setEmail("email");
		user2.setPassword("df");
		
		User user3 = new User();
		user3.setId(3L);
		user3.setEmail("bip@bip.bip");
		user3.setFirstName("bip");
		user3.setLastName("bip");
		user3.setEmail("email");
		user3.setPassword("df");
		
		Comment comment1 = new Comment(user1, exhibition1, "comment on exhibition1 from user1");
		Comment commentOnComment1 = new Comment(user2, exhibition1, "comment on comment, from user2", comment1);
		Comment commentOnComment1_2 = new Comment(user3, exhibition1, "comment", comment1);
		this.save(comment1);
		this.save(commentOnComment1);
		this.save(commentOnComment1_2);
	}
	@Override
	public Map<Comment, List<Comment>> getCommentsForExhibition(
			Long exhibitionId) 
	{
		Map<Comment, List<Comment>> map = new HashMap<Comment, List<Comment>>();
		for(Comment comm: this.findAll())
		{
			if(comm.getMainComment() == null)
				map.put(comm, commentsForComment(comm.getId()));
		}
		return map;
	}

	private List<Comment> commentsForComment(Long commentId)
	{
		List<Comment> commRes = new ArrayList<Comment>();
		for(Comment comm: findAll())
		{
			if(comm.getId() != commentId && comm.getMainComment().getId() == commentId)
				commRes.add(comm);
		}
		return commRes;
	}
}
