package com.contact.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.entity.Contact;
import com.contact.repository.ConactRepository;
import com.contact.service.ContactService;

@Service
public class ContacServiceImpl implements ContactService{
	
    @Autowired
	private ConactRepository contactRepository;
	@Override
	public void deleteContact(Long id) {
		// TODO Auto-generated method stub
		
		contactRepository.deleteById(id);
	}
	@Override
	public Contact getConactById(Long id) {
		Contact contact=this.contactRepository.findById(id).get();
		return contact;
	}
	@Override
	public Contact updateContact(Contact contact) {
	
		Contact contacts=this.contactRepository.save(contact);
		return contacts;
	}

	
}
