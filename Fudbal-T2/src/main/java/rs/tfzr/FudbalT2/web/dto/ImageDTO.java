package rs.tfzr.FudbalT2.web.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageDTO 
{
	private CommonsMultipartFile data;
	private Long id;
	public CommonsMultipartFile getData() {
		return data;
	}
	public void setData(CommonsMultipartFile data) {
		this.data = data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
