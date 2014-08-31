package rs.tfzr.FudbalT2.service.memory;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.tfzr.FudbalT2.model.Comment;
import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.User;

public class InMemoryCommentTest 
{
	InMemoryCommentService service;
	
	@Before
	public void setUp()
	{
		service = new InMemoryCommentService();
		
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
		
		Comment comment1 = new Comment(user1, exhibition1, null, "comment on exhibition1 from user1");
		Comment commentOnComment1 = new Comment(user2, exhibition1, null, "comment on comment, from user2", comment1);
		Comment commentOnComment1_2 = new Comment(user3, exhibition1, null, "comment", comment1);
		service.save(comment1);
		service.save(commentOnComment1);
		service.save(commentOnComment1_2);
	}
	
	@Test
	public void testGetCommentsForExhibition()
	{
		List<Comment> list = service.findAll();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 3);
		Map<Comment, List<Comment>> map = service.getCommentsForExhibition(1L);
		Assert.assertTrue(map.size() == 1);
		Assert.assertNotNull(map.values());
		for(List<Comment> comments: map.values())
		{
			Assert.assertTrue(comments.size() == 2);
		}
		
		for(Comment comment: map.keySet())
		{
			System.out.println(comment.getBody());
			for(Comment c: map.get(comment))
			{
				System.out.println("---" + c.getBody());
			}
		}
	}
}
