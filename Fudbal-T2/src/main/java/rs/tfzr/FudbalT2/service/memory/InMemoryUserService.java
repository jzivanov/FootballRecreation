package rs.tfzr.FudbalT2.service.memory;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;

@Service
public class InMemoryUserService extends AbstractInMemoryService<User>
		implements UserService {

}
