package rs.tfzr.FudbalT2.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;

public class StringToUserConverter implements Converter<String, User>
{
	@Autowired
	private UserService userService;
	
	@Override
	public User convert(String userId) {
		return userService.findOne(Long.parseLong(userId));
	}

}
