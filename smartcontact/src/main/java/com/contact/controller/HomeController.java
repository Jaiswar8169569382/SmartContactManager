package com.contact.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contact.entity.User;
import com.contact.helpr.Messege;
import com.contact.helpr.MyUtility;
import com.contact.helpr.UserFoundException;
import com.contact.repository.UserRepository;
import com.contact.service.UserService;
import com.contact.serviceImpl.UserServiceImpl;

import net.bytebuddy.utility.RandomString;

@Controller
public class HomeController {
@Autowired
private UserService userService; 

@Autowired
private UserServiceImpl userServiceImpl;
@Autowired
private BCryptPasswordEncoder encoder;

@RequestMapping("/")
public String home(Model model)
{
	model.addAttribute("title", "home ! SmartContactManager");
	return "home";
}

@RequestMapping("/signin")
public String signin(Model model)
{
	model.addAttribute("title", "Signin ! SmartContactManager");
	model.addAttribute("user", new User());
	return "signin";
}

@RequestMapping(value = "/signinprocess",method = RequestMethod.POST)
public String signinprocess()
{
System.out.println("Hello world");	
	return "";
}

@RequestMapping("/signup")
public String signup(Model model)
{
	model.addAttribute("title", "Signup ! SmartContactManager");
	model.addAttribute("user", new User());
	return "signup";
}

@RequestMapping(value = "/signupprocess",method = RequestMethod.POST)

public String signupprocess(@Valid @ModelAttribute User user, BindingResult result, Model model,HttpSession session,HttpServletRequest request) 
{
	System.out.println("signup called");
	System.out.println(user.getPassword());
	String verificationCode=RandomString.make(64).toString();
	
	user.setEnable(false);
   user.setVerificationcode(verificationCode);
	
	user.setRole("ROLE_USER");
	user.setPassword(encoder.encode(user.getPassword()));
	String myUrl=MyUtility.generateUrl(request);
	try
	{
		if(result.hasErrors())
		{
			
			return "signup";
		}
		User users=this.userService.saveUser(user);
		this.userServiceImpl.sendVerificationCode(users, myUrl);
		if(users==null)
		{
			
		}
		else
		{
			 model.addAttribute("user", new User());
				session.setAttribute("sms", new Messege("User Registered Successfully ! Please verify your email into your gmail account","success","alert alert-success"));
		}
	   
	}
	catch(UserFoundException e)
	{
		model.addAttribute("user", new User());
		session.setAttribute("sms", new Messege(""+e.getMessage(),"success","alert alert-danger"));
	}
	catch(Exception e)
	{    model.addAttribute("user", new User());
		session.setAttribute("sms", new Messege("User Already registered With same email id"+e.getMessage(),"success","alert alert-danger"));
	} 
	
	
	return "signup";
}

@RequestMapping("/verify")
@Transactional
public String verifyUser(@Param("code") String code,Model model)
{
	boolean verifying=this.userServiceImpl.verifyAccount(code);
	
	String pageTitle= verifying?"User verification success":"User Already verified";
	model.addAttribute("pageTitle", pageTitle);
	
	return verifying?"success":"failed";
}

@RequestMapping("/success")
public String success()
{
	return"success";
}

@RequestMapping("/failed")
public String failed()
{
	return"failed";
}
}
