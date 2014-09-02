package rs.tfzr.FudbalT2.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;
import rs.tfzr.FudbalT2.web.dto.UserDTO;
import rs.tfzr.FudbalT2.web.validator.UserValidator;

@Controller
@RequestMapping(value = "/user")
public class UserController 
{
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegistration(Model model) {
		UserDTO userForm = new UserDTO();
		model.addAttribute("userFormRegister", userForm);
		return "register";
	}

	@RequestMapping(params = "register", value = "/register", method = RequestMethod.POST)
	public String processRegistration(UserDTO user, Model model) {
		System.out.println("in process registration");
		DataBinder binder = new DataBinder(user);
		binder.addValidators(userValidator);
		binder.validate();
		BindingResult bindingResult = binder.getBindingResult();

		if (!bindingResult.hasErrors()) {
			User us = new User();
			us.setEmail(user.getEmail());
			us.setFirstName(user.getFirstName());
			us.setLastName(user.getLastName());
			us.setPassword(user.getPassword());
			us.setPhoneNumber(user.getPhoneNumber());
			us.setUsername(user.getEmail());
			userService.save(us);
			model.addAttribute("success", new String("success"));
		} else {
			for(ObjectError error: bindingResult.getGlobalErrors())
			{
				model.addAttribute(error.getDefaultMessage(), error.getCode());
			}
			user.setPassword("");
			user.setRepeatPassword("");
			model.addAttribute("userFormRegister", user);
		}

		return "register";

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model)
	{
		if(error != null)
			model.addAttribute("error", "page.login.error");
		if(logout != null)
			model.addAttribute("logout_msg", "page.logout.msg");
		
		return "user-login";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String viewUsers(Model model)
	{
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public String viewUsers(@PathVariable Long userId, Model model)
	{
		model.addAttribute("users", userService.findAll());
		model.addAttribute("user", userService.findOne(userId));
		return "users";
	}
}
