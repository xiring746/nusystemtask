package com.personalmanagement.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.personalmanagement.entities.User;
import com.personalmanagement.helper.Message;
import com.personalmanagement.service.UserService;
@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
    @RequestMapping("/")        
	public String home(Model model) {
    	     model.addAttribute("title", "Home - Personal management system");
		     return "home";
	}
    @RequestMapping("/about")
    public String about(Model model) {
    	     model.addAttribute("title", "About - Personal management system");
    	     return "about";
    }
    @RequestMapping("/signup")        
   	public String signup(Model model) {
    	     model.addAttribute("title", "Register - Personal management system");
    	     model.addAttribute("user", new User());
   		     return "signup";
   	}
    @RequestMapping(value="/do_register", method= RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1, @RequestParam(value="agreement", defaultValue="false") boolean agreement, Model model, HttpSession session) {
    	               try {
    	            	   if(!agreement) {
       	    	          
       	    	            throw new Exception("You have not agreed the terms and conditions");
       	    	        }
    	            	   if(result1.hasErrors()) {
    	            		      
    	            		        model.addAttribute("user", user);
    	            		        return "signup";
    	            	   }
       	    	        user.setRole("ROLE_USER");
       	    	        user.setEnabled(true);
       	    	        user.setPassword(passwordEncoder.encode(user.getPassword()));
       	    	    
       	    	        User result = userService.saveUser(user);
       	    	        model.addAttribute("user", new User()); 
       	    	        session.setAttribute("message", new Message("successfully registered", "alert-success"));
                     	 return "login";
    	               }catch(Exception e) {
    	            	     e.printStackTrace();
    	            	     model.addAttribute("user", user);
    	            	     session.setAttribute("message", new Message("something went wrong!!"+ e.getMessage(), "alert-danger"));
    	                   	 return "signup";
    	               }
    }
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String customLogin(Model model) {
    	model.addAttribute("title", "LoginPage");
    	return "login";
    }
}
