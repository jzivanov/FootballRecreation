/**
 * 
 */
package rs.tfzr.FudbalT2.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author Miroslav
 *
 */
public final class ExhibitionDTO {

	private Long id;

	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	
	private boolean ended;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public boolean getEnded() {
		return ended;
	}
	
	

}
