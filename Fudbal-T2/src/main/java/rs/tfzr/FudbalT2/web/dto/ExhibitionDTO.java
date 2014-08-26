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

	private Long Id;

	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
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
	
	

}
