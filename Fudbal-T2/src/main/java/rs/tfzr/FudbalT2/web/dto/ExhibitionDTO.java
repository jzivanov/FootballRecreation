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

	private Long getId() {
		return Id;
	}

	private void setId(Long Id) {
		this.Id = Id;
	}

	private Date getStartDate() {
		return startDate;
	}

	private void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private Date getEndDate() {
		return endDate;
	}

	private void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
