package rs.tfzr.FudbalT2.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "comment")
public class Comment extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2736385850859895358L;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	@ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
	private Exhibition exhibition;
	private Comment mainComment;
	private String body;
	private String title;
	
	public Comment()
	{
		this.user = new User();
		this.exhibition = new Exhibition();
	}
	
	public Comment(User user, Exhibition exhibition, String title, String body)
	{
		this.user = user;
		this.exhibition = exhibition;
		this.body = body;
	}
	
	public Comment(User user, Exhibition exhibition, String title, String body, Comment mainComment)
	{
		this(user, exhibition, title, body);
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
	public String getBody() {
		return body;
	}
	public void setBody(String content) {
		this.body = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
