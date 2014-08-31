package rs.tfzr.FudbalT2.web.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CommentDTO 
{
	Long userId;
	@NotNull
	Long exhibitionId;
	@NotEmpty
	String title;
	@NotEmpty
	String body;
	Long mainCommentId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getExhibitionId() {
		return exhibitionId;
	}
	public void setExhibitionId(Long exhibitionId) {
		this.exhibitionId = exhibitionId;
	}
	public Long getMainCommentId() {
		return mainCommentId;
	}
	public void setMainCommentId(Long commentId) {
		this.mainCommentId = commentId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
