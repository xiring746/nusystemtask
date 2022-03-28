package com.personalmanagement.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.personalmanagement.dao.UserRepository;
import com.personalmanagement.entities.User;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	@Override
    public User loadUserByName(String username) {
    	       return userRepository.getUserByUserName(username);
    }


	

}
