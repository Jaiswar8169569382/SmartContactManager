package com.contact.controller;

import java.security.Principal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contact.entity.User;
import com.contact.helpr.GenerateOtp;
import com.contact.helpr.Messege;
import com.contact.repository.UserRepository;

@Controller
public class ForgestPassword {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@RequestMapping("/forget")
	public String forgetPassword()
	{
		return "loademail";
	}
	
	@PostMapping("/generateotp")
	public String generateOtp(@RequestParam("email") String email,HttpSession session)
	{
		Format  format=new DecimalFormat("000000");
		String otp=format.format(new Random().nextInt(999999));
		String message="Your Otp is : "+otp;
		String subject="Smart contact manager otp";
		String to=email;
		String from="mjtech8169569382@gmail.com";
	    GenerateOtp.generateOtp(message, subject, to, from);
	    session.setAttribute("otp", otp);
	    session.setAttribute("email", email);
		return "otpform";
	}
	@PostMapping("/validateotp")
	
	public String validateOtp(@RequestParam("otp") String otp,HttpSession session)
	{
		System.out.println(otp);
		String validateotp=(String)session.getAttribute("otp");
		System.out.println(validateotp);
		if(validateotp.equals(otp))
		{
			System.out.println("Otp validate password is reset");
			
			return "changepassword";
		}
		else
		{
			System.out.println("Otp validation failed");
			session.setAttribute("osms", new Messege("Otp validation failed", "", "alert alert-danger"));
			return "otpform";
		}
		
	}
	
	@PostMapping("/changedPassword")
	public String changedPassword(@RequestParam("password") String password,Principal principal,HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		User user=userRepository.getUserByEmail(email);
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
		System.out.println("Password changed");
		session.setAttribute("messages", new Messege("Password Change Successfully", "", "alert alert-success"));
		return "signin";
	}
}
