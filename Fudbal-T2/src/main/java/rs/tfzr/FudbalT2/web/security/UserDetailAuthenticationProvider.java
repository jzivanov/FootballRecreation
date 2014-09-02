package rs.tfzr.FudbalT2.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;

@Component
public class UserDetailAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	private UserService userDetailService;
	
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		String username = auth.getName();
		String password = (String)auth.getCredentials();
		
		User userDetails = (User) userDetailService.loadUserByUsername(username);
		if(userDetails == null)
			throw new BadCredentialsException("Username not found.");
		
		if(!password.equals(userDetails.getPassword()))
			throw new BadCredentialsException("Wrong password.");

		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<? extends Object> clazz) {
		return true;//UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
	}

}
