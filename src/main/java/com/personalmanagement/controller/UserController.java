package com.personalmanagement.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.personalmanagement.dao.ContactRepository;
import com.personalmanagement.entities.Contact;
import com.personalmanagement.entities.User;
import com.personalmanagement.helper.Message;
import com.personalmanagement.service.ContactService;
import com.personalmanagement.service.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	                         @Autowired
	                         private UserService userService;
	                         @Autowired
	                         private ContactService contactService;
	                       
	                         @ModelAttribute
	                         public void addCommonData(Model model, Principal principal) {
	                        	  String userName = principal.getName(); 
                            	  // get the user using username(email)
                            	  User user = userService.loadUserByName(userName);
                            	  model.addAttribute("user", user);
	                         }
                             @RequestMapping("/dashboard")
	                         public String dashboard(Model model, Principal principal) {
                            	  model.addAttribute("title", "User Dashboard - Personal Management System");
	                        	  return "normal/user_dashboard";
	                         }
                             @RequestMapping(value = "/add-user", method = RequestMethod.GET)
                             public String openAddContactForm(Model model) {
                            	    model.addAttribute("title", "Add User - Personal Management System");
                            	    model.addAttribute("contact", new Contact());
                            	
                            	   List <String> departmentList = 
                           	    		 Arrays.asList("Development",
                           	    				 "Sales & Marketing",
                           	    				 "Training",
                           	    				 "HR",
                           	    				 "Finance");
                           	       model.addAttribute("departmentList", departmentList);
                           	     
                           	       List <String> professionList = 
                   		    		 Arrays.asList("Web Developer",
                   		    				 "Mobile Application Developer",
                   		    				 "Web Designer",
                   		    				 "Graphic Designer");
                   		           model.addAttribute("professionList", professionList);
                   		          
                   		           List <String> prefectureList = 
                      		    		 Arrays.asList("Tokyo",
                      		    				 "Saitama",
                      		    				 "Fukuoka",
                      		    				 "Osaka",
                      		    				 "Kyoto");
                      		       model.addAttribute("prefectureList", prefectureList);
                   		         
	                               return "normal/add_user_form";
	                         }
                             //Processing add contact form
                             @RequestMapping(value = "/process-contact", method = RequestMethod.POST)
                             public String processContact(
                            		 @Valid @ModelAttribute Contact contact,
                            		 BindingResult result2,
                            		 @RequestParam("profileImage") MultipartFile file,
                            		 Model model,
                            		 Principal principal,
                            		 HttpSession session) {
                            	     try {
                            	    	String name = principal.getName();
                                 	    User user = userService.loadUserByName(name);
                                 	    // processing and uploading file
                                 	    
                                 	    if(file.isEmpty()) {
                                 	    	contact.setImage("contact.png");    
                                 	    }
                                 	   if(result2.hasErrors()) {
         	            		            model.addAttribute("user", user );
         	            		           userService.saveUser(user);
         	            		           List <String> departmentList = 
                                   	    		 Arrays.asList("Development",
                                   	    				 "Sales & Marketing",
                                   	    				 "Training",
                                   	    				 "HR",
                                   	    				 "Finance");
                                   	        model.addAttribute("departmentList", departmentList);
                                   	        List <String> professionList = 
                           		    		 Arrays.asList("Web Developer",
                           		    				 "Mobile Application Developer",
                           		    				 "Web Designer",
                           		    				 "Graphic Designer");
                           		            model.addAttribute("professionList", professionList);
                           		            List <String> prefectureList = 
                              		    		 Arrays.asList("Tokyo",
                              		    				 "Saitama",
                              		    				 "Fukuoka",
                              		    				 "Osaka",
                              		    				 "Kyoto");
                              		        model.addAttribute("prefectureList", prefectureList);
                           		            userService.saveUser(user);
         	            		            return "normal/add_user_form";
         	            	            }
                                 	    else {
                                 	    	contact.setImage(file.getOriginalFilename());
                                 	    	File saveFile =  new ClassPathResource("static/img").getFile();
                                 	        Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                                 	    	Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                                 	    	
                                 	    }
                                 	    System.out.println(contact.getDepartment());
                                 	    System.out.println(contact.getProfession());
                                 	    contact.setUser(user);
                                 	    user.getContacts().add(contact);
                                 	   
                                 	   
                         		        userService.saveUser(user);
                                 	    session.setAttribute("message", new Message("Your contact is Added!! Add more..","success"));
                                 	    // success message
                                 	   List <String> departmentList = 
                               	    		 Arrays.asList("Development",
                               	    				 "Sales & Marketing",
                               	    				 "Training",
                               	    				 "HR",
                               	    				 "Finance");
                               	        model.addAttribute("departmentList", departmentList);
                               	       List <String> professionList = 
                       		    		 Arrays.asList("Web Developer",
                       		    				 "Mobile Application Developer",
                       		    				 "Web Designer",
                       		    				 "Graphic Designer");
                       		            model.addAttribute("professionList", professionList);
                       		           List <String> prefectureList = 
                          		    		 Arrays.asList("Tokyo",
                          		    				 "Saitama",
                          		    				 "Fukuoka",
                          		    				 "Osaka",
                          		    				 "Kyoto");
                          		       model.addAttribute("prefectureList", prefectureList);
                            	       }catch(Exception e) {
                            	    	e.printStackTrace();
                            	    	session.setAttribute("message", new Message("Something went wrong!! Try Again..","danger"));
                            	       }
                            	     
                            	     
                            	       return "normal/add_user_form";
                             }
                             //per page-5[n]
                             //Current page - 0[page]
                             @RequestMapping(value = "/show-contacts/{page}", method = RequestMethod.GET)
                             public String showContacts(@PathVariable("page") Integer page, Model model, Principal principal) {
                            	 model.addAttribute("title", "Show Contacts");
                            	 String userName = principal.getName();
                            	 User user = userService.loadUserByName(userName);
                            	 Pageable pageable = PageRequest.of(page, 5);
                            	 Page<Contact> contacts = contactService.loadContactsByUser(user.getId(), pageable);
                            	 model.addAttribute("contacts", contacts);
                            	 model.addAttribute("currentPage", page);
                            	 model.addAttribute("totalPages", contacts.getTotalPages());
                            	 return "normal/show_contacts";
                             }
                             // showing particular content details
                             @RequestMapping("/contact/{cId}")
                             public String showContactDetails(@PathVariable("cId") Integer cId, Model model,Principal principal) { 
                            	      Optional<Contact> contactOptional = contactService.getContactById(cId);
                            	      Contact contact = contactOptional.get();
                            	      String userName = principal.getName();
                            	      User user = userService.loadUserByName(userName);
                            	      if(user.getId() == contact.getUser().getId()) {
                            	    	  model.addAttribute("contact", contact); 
                            	    	  model.addAttribute("title", contact.getName()); 
                            	      } 
                            	      return "normal/contact_detail";
                             }
                             // Deleting specific contact
                             @RequestMapping(value = "/delete/{cid}", method = RequestMethod.GET)
                             public String deleteContact(@PathVariable("cid") Integer cid,Principal principal, HttpSession session) {
                            	 Optional<Contact> contactOptional = contactService.getContactById(cid);
                            	 Contact contact = contactOptional.get();
                            	 String userName = principal.getName();
                       	         User user = userService.loadUserByName(userName);
                       	         if(user.getId() == contact.getUser().getId()) {
                       	        	   contact.setUser(null);
                       	        	   contactService.deleteContact(contact);
                       	        	   session.setAttribute("message", new Message("Contact deleted Successfully...","success"));
                       	         }
                            	 return "redirect:/user/show-contacts/0";
                             }
                             
                             // open update form handler
                             @RequestMapping(value = "/update-contact/{cid}", method = RequestMethod.POST)
                             public String updateForm(@PathVariable("cid") Integer cid, Model model) {
                            	  model.addAttribute("title", "update contact");
                            	  Contact contact = contactService.getContactById(cid).get();
                            	  model.addAttribute("contact", contact);
                            	  List <String> departmentList = 
                     		    		 Arrays.asList("Development",
                     		    				 "Sales & Marketing",
                     		    				 "Training",
                     		    				 "HR",
                     		    				 "Finance");
                     		     model.addAttribute("departmentList", departmentList);
                     		    List <String> professionList = 
                   		    		 Arrays.asList("Web Developer",
                   		    				 "Mobile Application Developer",
                   		    				 "Web Designer",
                   		    				 "Graphic Designer");
                     		    model.addAttribute("professionList", professionList);
                     		    List <String> prefectureList = 
                    		    		 Arrays.asList("Tokyo",
                    		    				 "Saitama",
                    		    				 "Fukuoka",
                    		    				 "Osaka",
                    		    				 "Kyoto");
                    		    model.addAttribute("prefectureList", prefectureList);
                   		      
                            	  return "normal/update_form";
                             }
                             
                             // update form handler
                             @RequestMapping(value = "/process-update", method = RequestMethod.POST)
                             public String updateHandler(@Valid @ModelAttribute Contact contact, BindingResult result3,  @RequestParam("profileImage") MultipartFile file, Model m, HttpSession session, Principal principal) {
                            	    try {
                            	    	// old contact details
                            	    	Contact oldcontactDetail = contactService.getContactById(contact.getcId()).get();
                            	    	// image
                            	    	if(!file.isEmpty()) {
                            	    		//file work
                            	    		// delete old photo
                            	    		File deleteFile =  new ClassPathResource("static/img").getFile();
                            	    		File file1 = new File(deleteFile, oldcontactDetail.getImage());
                            	    		file1.delete();
                            	    		// update new photo
                            	    		File saveFile =  new ClassPathResource("static/img").getFile();
                                 	        Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                                 	    	Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                                 	    	contact.setImage(file.getOriginalFilename());
                            	    	}
                            	    	 if(result3.hasErrors()) {
           	            		            m.addAttribute("contact", contact);
           	            		         List <String> departmentList = 
                                 	    		 Arrays.asList("Development",
                                 	    				 "Sales & Marketing",
                                 	    				 "Training",
                                 	    				 "HR",
                                 	    				 "Finance");
                                 	        m.addAttribute("departmentList", departmentList);
                                 	        List <String> professionList = 
                         		    		 Arrays.asList("Web Developer",
                         		    				 "Mobile Application Developer",
                         		    				 "Web Designer",
                         		    				 "Graphic Designer");
                         		            m.addAttribute("professionList", professionList);
                         		           List <String> prefectureList = 
                              		    		 Arrays.asList("Tokyo",
                              		    				 "Saitama",
                              		    				 "Fukuoka",
                              		    				 "Osaka",
                              		    				 "Kyoto");
                              		       m.addAttribute("prefectureList", prefectureList);
                         		      
           	            		            return "normal/update_form";
           	            	            }else {
                            	    		   contact.setImage(oldcontactDetail.getImage());
                            	    	}
                            	    	User user = userService.loadUserByName(principal.getName());
                            	    	contact.setUser(user);
                            	        contactService.saveContact(contact);
                            	        session.setAttribute("message", new Message("Your contact is updated...", "success"));                            	    }catch(Exception e) {
                            	    	e.printStackTrace();
                            	    }
                            	 
                            	    return "redirect:/user/contact/"+contact.getcId();
                             }
                          
                             // Your profile Handler
                             @RequestMapping(value = "/profile", method = RequestMethod.GET)
                             public String yourProfile(Model model) {
                            	  model.addAttribute("title", "Profile Page");
                            	  return "normal/profile";
                             }
                          
}












