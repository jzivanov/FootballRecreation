package rs.tfzr.FudbalT2.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import rs.tfzr.FudbalT2.model.User;

public interface UserService extends CrudService<User>, UserDetailsService
{

}
