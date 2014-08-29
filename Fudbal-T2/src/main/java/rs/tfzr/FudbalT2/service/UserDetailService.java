package rs.tfzr.FudbalT2.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import rs.tfzr.FudbalT2.model.UserDetail;

public interface UserDetailService extends CrudService<UserDetail>, UserDetailsService
{

}
