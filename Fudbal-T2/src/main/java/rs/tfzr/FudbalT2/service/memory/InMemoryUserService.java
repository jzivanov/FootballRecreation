package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;

@Service
public class InMemoryUserService extends AbstractInMemoryService<User> 
	implements UserService
{
	public InMemoryUserService()
	{
		User user = new User();
		user.setEmail("zjovan.ost@gmail.com");
		user.setPassword("123");
		user.setUsername("zjovan.ost@gmail.com");
		user.setFirstName("Jovan");
		user.setLastName("Zivanov");
		user.setAdmin(true);
		
		User user2 = new User();
		user2.setEmail("pera@kojot");
		user2.setPassword("123");
		user2.setFirstName("pera kojot");
		user2.setLastName("super genije");
		user2.setUsername("pera@kojot");
		
		User user3 = new User();
		user3.setEmail("bip@bip");
		user3.setPassword("123");
		user3.setFirstName("bip");
		user3.setLastName("bip");
		user3.setUsername("bip@bip");
		user3.setAdmin(true);
		user3.setEnabled(true);
		
		this.save(user);
		this.save(user2);
		this.save(user3);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		for(User user: findAll())
		{
			if(user.getUsername().equals(username))
			{
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> disabledUsers() {
		List<User> users = new ArrayList<User>();
		for(User user: findAll())
		{
			if(!user.isEnabled())
				users.add(user);
		}
		return users;
	}

	@Override
	public List<User> enabledUsers() {
		List<User> users = new ArrayList<User>();
		for(User user: findAll())
		{
			if(user.isEnabled())
				users.add(user);
		}
		return users;
	}

}
