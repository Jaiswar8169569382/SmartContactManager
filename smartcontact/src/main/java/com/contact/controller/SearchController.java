package com.contact.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.contact.entity.Contact;
import com.contact.entity.User;
import com.contact.repository.ConactRepository;
import com.contact.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class SearchController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConactRepository contactRepository;
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal)
	{
	     System.out.println(query);	
		String username=principal.getName();
		User user=this.userRepository.getUserByEmail(username);
		List<Contact> contacts=this.contactRepository.findByNameContainingAndUser(query, user);
		return ResponseEntity.ok(contacts);
	}
}
