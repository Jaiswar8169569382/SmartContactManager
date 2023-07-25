package com.contact.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contact.entity.User;
import com.contact.repository.UserRepository;

public class CustomUserDetailServices implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.getUserByEmail(username);
		if(user==null)
		{
			System.out.println("User not found exception");
			throw new UsernameNotFoundException("User not found exception");
		}
		
		CustomUserDetail userDetails=new CustomUserDetail(user);
		return userDetails;
	}

	
}
