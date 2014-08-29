package rs.tfzr.FudbalT2.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import rs.tfzr.FudbalT2.model.UserDetail;
import rs.tfzr.FudbalT2.service.UserDetailService;

@Component
public class UserDetailAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	private UserDetailService userDetailService;
	
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		String username = auth.getName();
		String password = (String)auth.getCredentials();
		
		UserDetail userDetails = (UserDetail) userDetailService.loadUserByUsername(username);
		
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
