package com.personalmanagement.service;

import java.util.List;


import com.personalmanagement.entities.User;

public interface UserService {
	List<User> getAllUsers();
    User saveUser(User user);
	User loadUserByName(String userName);
   
}
