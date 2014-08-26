package rs.tfzr.FudbalT2.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.User;

@Controller
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		User userForm = new User();
		model.put("userFormRegister", userForm);

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userFormRegister") User user,
			Map<String, Object> model) {

        System.out.println("RegisterSuccess");

      //Register logic here
		return "registerSuccess";
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
		//Login logic here
        System.out.println("LoginSuccess");


		return "loginSuccess";
	}
}