package com.contact.service;

import com.contact.entity.Contact;

public interface ContactService {

	public void deleteContact(Long id);
	public Contact getConactById(Long id);
	public Contact updateContact(Contact contact);
}
