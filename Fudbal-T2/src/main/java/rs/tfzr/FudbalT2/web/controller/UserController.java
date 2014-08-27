package rs.tfzr.FudbalT2.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;

@Controller
public class UserController {

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
	public String viewLogin(Map<String, Object> model) {
		User userFormLogin = new User();
		model.put("userFormLogin", userFormLogin);

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@ModelAttribute("userFormLogin") User user,
			Map<String, Object> model) {
		// Login logic here
		System.out.println("LoginSuccess");

		return "loginSuccess";
	}
}