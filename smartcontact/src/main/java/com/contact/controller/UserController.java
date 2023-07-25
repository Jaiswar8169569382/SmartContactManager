package com.contact.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.entity.User;
import com.contact.repository.UserRepository;
import com.contact.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepositoy;
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public void addCommonData(Principal principal,Model model)
	{
		String username=principal.getName();
		User user=userRepositoy.getUserByEmail(username);
		model.addAttribute("user", user);
	}
@RequestMapping("/profile")
public String profile(Principal principal,Model model)
{
	model.addAttribute("title","user-dashboard ! smart contact manager");
	
	return "user/dashboard";
}

@RequestMapping("/about")
public String about(Model model)
{
	System.out.println("worked");
	model.addAttribute("title", "about ! Smart contact manager");
	return "user/about";
}

@GetMapping("/update")
public String updateProfile(Model model,Principal principal)
{
	String username=principal.getName();
	User user=this.userRepositoy.getUserByEmail(username);
	
	model.addAttribute("user", user);
	return "user/update-profile";
}

@PostMapping("/updateprocess")
public String updateProfileProcessing(@ModelAttribute User user) throws Exception
{
	this.userService.updateUser(user);
	return "user/about";
}


}
