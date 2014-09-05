package rs.tfzr.FudbalT2.web.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.UserService;
import rs.tfzr.FudbalT2.web.dto.ImageDTO;
import rs.tfzr.FudbalT2.web.dto.UserDTO;
import rs.tfzr.FudbalT2.web.validator.ImageValidator;
import rs.tfzr.FudbalT2.web.validator.UserEditValidator;
import rs.tfzr.FudbalT2.web.validator.UserValidator;

@Controller
@RequestMapping(value = "/user")
public class UserController 
{
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserEditValidator userEditValidator;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageValidator imageValidator;
	
	@Autowired
	private ServletContext context;

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
			us.setPhoneNumber(user.getPhone());
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
		List<User> disusers = new ArrayList<User>();
		disusers = userService.disabledUsers();
		if(disusers.size() > 0)
			model.addAttribute("disusers", userService.disabledUsers());
		model.addAttribute("users", userService.enabledUsers());
		return "users";
	}
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public String viewUsers(@PathVariable Long userId, Model model)
	{
		model.addAttribute("disusers", userService.disabledUsers());
		model.addAttribute("users", userService.enabledUsers());
		model.addAttribute("user", userService.findOne(userId));
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("signeduserid", user.getId());
		return "users";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users/{userId}/enable", method = RequestMethod.GET)
	public String disEnableUser(
			@RequestParam(value = "enable", required = false) String enable,
			@RequestParam(value = "disable", required = false) String disable,
			@PathVariable("userId") User user,
			Model model)
	{
		if(enable != null)
		{
			user.setEnabled(true);
			userService.save(user);
		}
		else if(disable != null)
		{
			user.setEnabled(false);
			userService.save(user);
		}
		return "redirect:/user/users/" + user.getId();
	}
	
	@RequestMapping(value = "/users/{userId}/remove", method = RequestMethod.GET)
	public String remove(@PathVariable("userId") User user, Model model)
	{
		userService.remove(user.getId());
		return "redirect:/user/users/";
	}
	
	@RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("userId") User user, Model model)
	{
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		System.out.println(user.getPassword());
		dto.setPassword(user.getPassword());
		dto.setPhone(user.getPhoneNumber());
		dto.setUsername(user.getEmail());
		dto.setRepeatPassword(user.getPassword());
		dto.setAdmin(user.isAdmin());
		dto.setImage(user.getImage());
		dto.setImagedto(new ImageDTO());
		
		User signeduserid = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("signeduserid", signeduserid.getId());
		model.addAttribute("userform", dto);
		return "edit-user";
	}
	
	@RequestMapping(value = "/users/image/{userId}", method = RequestMethod.GET)
	public void getImageUrl(@PathVariable Long userId, HttpServletResponse response, Model model)
	{
		byte[] img = userService.findOne(userId).getImage();
		if(img == null || img.length == 0)
		{
			try 
			{
				InputStream res = context.getResourceAsStream("/WEB-INF/img/user.png");
				img = IOUtils.toByteArray(res);
				response.getOutputStream().write(img);
				response.getOutputStream().flush();
			} 
			catch (IOException e) 
			{
			}
		}
		else
		{
			try
			{
				response.getOutputStream().write(img);
				response.getOutputStream().flush();
			}
			catch (IOException e) 
			{
			}
		}
	}
	
	@RequestMapping(params = "edit", value = "/users/edit", method = RequestMethod.POST)
	public String setEdit(UserDTO dto, Model model)
	{		
		User user = userService.findOne(dto.getId());
		if(dto.getPassword() == null)
		{
			dto.setPassword(user.getPassword());
			dto.setRepeatPassword(user.getPassword());
		}
		if(dto.getUsername() == null)
		{
			dto.setEmail(user.getEmail());
		}
		else
		{
			dto.setEmail(dto.getUsername());
		}
		boolean imgerr = false;
		if(!dto.getImagedto().getData().isEmpty())
		{
			DataBinder binder = new DataBinder(dto.getImagedto());
			binder.addValidators(imageValidator);
			binder.validate();
			BindingResult result = binder.getBindingResult();
			if(!result.hasErrors())
			{
				user.setImage(dto.getImagedto().getData().getBytes());
			}
			else
			{
				model.addAttribute("imgerrors", result.getAllErrors());
				imgerr = true;
			}
		}
		DataBinder binder = new DataBinder(dto);
		binder.addValidators(userEditValidator);
		binder.validate();
		BindingResult bindingResult = binder.getBindingResult();
		if(!bindingResult.hasErrors() && !imgerr)
		{
			user.setEmail(dto.getEmail());
			user.setFirstName(dto.getFirstName());
			user.setId(dto.getId());
			user.setLastName(dto.getLastName());
			user.setPassword(dto.getPassword());
			user.setPhoneNumber(dto.getPhone());
			user.setUsername(dto.getEmail());
			user.setAdmin(dto.isAdmin());
			userService.save(user);
			return "redirect:/user/users/" + dto.getId();
		} 
		else 
		{
			for(ObjectError error: bindingResult.getGlobalErrors())
			{
				model.addAttribute(error.getDefaultMessage(), error.getCode());
			}
			User signeduserid = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("signeduserid", signeduserid.getId());
			model.addAttribute("userform", dto);
		}
		return "edit-user";
	}
}



