package rs.tfzr.FudbalT2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.repository.UserRepository;
import rs.tfzr.FudbalT2.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);

	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();

	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		userRepository.delete(id);

	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
