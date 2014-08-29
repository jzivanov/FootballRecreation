package rs.tfzr.FudbalT2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
public class UserDetailsController 
{
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
