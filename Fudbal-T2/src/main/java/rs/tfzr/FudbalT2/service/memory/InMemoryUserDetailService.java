package rs.tfzr.FudbalT2.service.memory;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.UserDetail;
import rs.tfzr.FudbalT2.service.UserDetailService;

@Service
public class InMemoryUserDetailService extends AbstractInMemoryService<UserDetail> 
	implements UserDetailService
{
	public InMemoryUserDetailService()
	{
		UserDetail user = new UserDetail();
		user.setEmail("zjovan.ost@gmail.com");
		user.setPassword("123");
		user.setUsername("zjovan.ost@gmail.com");
		this.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetail retUser = new UserDetail();

		for(UserDetail user: findAll())
		{
			if(user.getUsername().equals(username))
			{
				retUser = user;
				break;
			}
		}
		return retUser;
	}

}
