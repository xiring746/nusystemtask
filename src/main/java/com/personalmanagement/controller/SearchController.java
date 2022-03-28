package com.personalmanagement.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.personalmanagement.entities.Contact;
import com.personalmanagement.entities.User;
import com.personalmanagement.service.ContactService;
import com.personalmanagement.service.UserService;

@RestController
public class SearchController {
	       @Autowired
	       private UserService userService;
	       @Autowired
	       private ContactService contactService;
           // search handler
	       @GetMapping("/search/{query}")
	       public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal){
	    	   
	    	    User user = userService.loadUserByName(principal.getName());
	    	    List<Contact> contacts = contactService.searchByNameAndUser(query, user);
	    	    return ResponseEntity.ok(contacts);
	       }
}
