package rs.tfzr.FudbalT2.web.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import rs.tfzr.FudbalT2.web.dto.ImageDTO;

@Component
public class ImageValidator implements Validator {

	private static final List<String> IMGEXTS;
	private final String FILE_SIZE_OVER = "page.user.validation.sizeover";
	private final String FILE_EMPTY = "page.user.validation.fileEmpty";
	private final String ACCESS_ERROR = "page.user.validation.accessErrors"; 
	private final String EXT_NOT_SUPPORTED = "page.user.validation.extNotSupported";
	
	static
	{
		IMGEXTS = new ArrayList<String>();
		IMGEXTS.add("jpg");
		IMGEXTS.add("jpeg");
		IMGEXTS.add("png");
		IMGEXTS.add("bmp");
		IMGEXTS.add("gif");
	}
	
	private final int FILE_SIZE = 5242880;
	
	@Override
	public boolean supports(Class<?> clazz) 
	{
		return ImageDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target != null && supports(target.getClass()))
		{
			ImageDTO dto = (ImageDTO)target;
			if(dto.getData() != null && dto.getData().getSize() > 0)
			{
				CommonsMultipartFile file = dto.getData();
				try 
				{
					file.getInputStream();
				} 
				catch (IOException e) 
				{
					errors.reject(ACCESS_ERROR);
				}
				
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				
				if(!IMGEXTS.contains(extension))
				{
					errors.reject(EXT_NOT_SUPPORTED);
				}
				
				if(file.getSize() > FILE_SIZE)
				{
					errors.reject(FILE_SIZE_OVER);
				}
			}
			else
			{
				errors.reject(FILE_EMPTY);
			}
		}
	}

}
