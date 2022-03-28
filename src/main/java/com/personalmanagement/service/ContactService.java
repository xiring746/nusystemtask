package com.personalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.personalmanagement.entities.Contact;
import com.personalmanagement.entities.User;


public interface ContactService {
	List<Contact> getAllContacts();
    Optional<Contact> getContactById(Integer cId);
	Page<Contact> loadContactsByUser(int id, Pageable pageable);
	void deleteContact(Contact contact);
	Contact saveContact(Contact contact);
	List<Contact> searchByNameAndUser(String query, User user);
	 
}
