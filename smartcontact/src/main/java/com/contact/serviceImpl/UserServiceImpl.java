package com.contact.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contact.entity.Contact;
import com.contact.entity.User;
import com.contact.helpr.GenerateOtp;
import com.contact.helpr.UserFoundException;
import com.contact.repository.UserRepository;
import com.contact.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;	
	@Override
	
	public User saveUser(User user) throws Exception {
 
		User localuser=userRepository.getUserByEmail(user.getEmail());
		if(localuser!=null)
		{
			throw new UserFoundException("User Already registered With Same Email Id");
		}
		else
		{
			localuser=userRepository.save(user);
		}
		
		return localuser;
	}
	@Override
	public User getUserById(Long id) {
		
		User user=this.userRepository.findById(id).get();
		return user;
	}
	@Override
	public User updateUser(User user) {
	User users=this.userRepository.save(user);
		return users;
	}

	
	public void sendVerificationCode(User user,String getUrl)
	{
		System.out.println(user.getVerificationcode());
		String subject="Please Verify Your Registration";
		String content= "<p>Dear "+user.getFirstname() +" "+user.getLastname() + ", </p>";
		content+="<b>Welcme to SmartContactManager System</b>";
		content+="<p>Please Verify your account to click below link</p>";
		String verifylink= getUrl+"/verify?code="+user.getVerificationcode();
		content+="<h3><a href=\"" + verifylink + "\">Verify</a></h3>";
		
				GenerateOtp.generateOtp(subject, content,user.getEmail() , "mjtech8169569382@gmail.com");
	}
	
	@Transactional
	public boolean verifyAccount(String code)
	{
		User user=userRepository.getUserByVerifiation(code);
	
		
		if(user==null || user.isEnable())
		{
			return false;
		}
		else
		{
			userRepository.enableUser(user.getUid());
			return true;
		}
	}
}
