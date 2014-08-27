package rs.tfzr.FudbalT2.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "comment")
public class Comment extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2736385850859895358L;
	
	private User user;
	private Exhibition exhibition;
	private Comment mainComment;
	private String content;
	
	public Comment()
	{
		this.user = new User();
		this.exhibition = new Exhibition();
	}
	
	public Comment(User user, Exhibition exhibition, String content)
	{
		this.user = user;
		this.exhibition = exhibition;
		this.content = content;
	}
	
	public Comment(User user, Exhibition exhibition, String content, Comment mainComment)
	{
		this(user, exhibition, content);
		this.mainComment = mainComment;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Exhibition getExhibition() {
		return exhibition;
	}
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}
	public Comment getMainComment() {
		return mainComment;
	}
	public void setMainComment(Comment mainComment) {
		this.mainComment = mainComment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
