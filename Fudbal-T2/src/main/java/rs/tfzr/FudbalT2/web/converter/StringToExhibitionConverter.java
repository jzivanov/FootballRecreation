package rs.tfzr.FudbalT2.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.service.ExhibitionService;

public class StringToExhibitionConverter implements Converter<String, Exhibition>
{
	@Autowired
	private ExhibitionService exhibitionService;

	@Override
	public Exhibition convert(String exhibitionId) {
		return exhibitionService.findOne(Long.parseLong(exhibitionId));
	}

}
