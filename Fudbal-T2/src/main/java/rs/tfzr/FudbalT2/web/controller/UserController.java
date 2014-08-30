package rs.tfzr.FudbalT2.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController 
{

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		User userForm = new User();
		model.put("userFormRegister", userForm);

		return "register";
	}

	@RequestMapping(params = "register", method = RequestMethod.POST)
	public String processRegistration(@Valid User user,
			BindingResult bindingResult, Model model) {

		String viewUser;
		if (!bindingResult.hasErrors()) {
			userService.save(user);
			viewUser = "redirect:registerSuccess";
		} else {
			model.addAttribute("user", user);
			viewUser = "register";
		}

		return viewUser;

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
}
