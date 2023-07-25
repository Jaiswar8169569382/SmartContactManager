package com.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.entity.Contact;
import com.contact.entity.User;

public interface ConactRepository extends JpaRepository<Contact, Long>{

	
	public List<Contact> findByNameContainingAndUser(String keywords,User user);
}
