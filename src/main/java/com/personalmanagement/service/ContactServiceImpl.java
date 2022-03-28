package com.personalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.personalmanagement.dao.ContactRepository;
import com.personalmanagement.entities.Contact;
import com.personalmanagement.entities.User;
@Service
public class ContactServiceImpl implements ContactService{
    private ContactRepository contactRepository;
    
	public ContactServiceImpl(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Page<Contact> loadContactsByUser(int id, Pageable pageable) {
		return contactRepository.findContactsByUser(id, pageable);
	}

	@Override
	public Optional<Contact> getContactById(Integer cId) {
		   return contactRepository.findById(cId);
	}

	@Override
	public void deleteContact(Contact contact) {
		   contactRepository.delete(contact);
		
	}

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public List<Contact> searchByNameAndUser(String query, User user) {
		return contactRepository.findByNameContainingAndUser(query, user);
	}

	

}
