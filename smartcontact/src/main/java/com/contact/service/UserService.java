package com.contact.service;

import java.util.List;

import com.contact.entity.Contact;
import com.contact.entity.User;

public interface UserService {
public User saveUser(User user) throws Exception;
public User getUserById(Long id);
public User updateUser(User user);

}
