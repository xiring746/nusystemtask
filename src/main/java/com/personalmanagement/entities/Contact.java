package com.personalmanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="CONTACT")
public class Contact {
	            
					@Id
	                @GeneratedValue(strategy=GenerationType.AUTO)
                    private int cId;
					@NotBlank(message="Name field is required")
					@Size(min=3, max=12, message="username must be between 3-12 characters")
                    private String name;
					@NotBlank(message="Last Name field is required")
                    private String secondName;
					@Column(unique=true)
	                @NotBlank(message="Email field is required")

                    private String email;
					@NotBlank(message="Enter your valid number")
					@Pattern(regexp="(^$|[0-9]{10})", message="Phone number should be 10 number digit")
					private String phone;
                    private String image;
                    @Column(length=10000)
                    private String department;
                    private String profession;
                    @NotBlank(message="Name field is required")
                    private String postalcode;
                    private String prefecture;
                    @NotBlank(message="	City name is required")
                    private String city;
                    
                 
					
					public Contact(
							@NotBlank(message = "Name field is required") @Size(min = 3, max = 12, message = "username must be between 3-12 characters") String name,
							@NotBlank(message = "Last Name field is required") String secondName,
							@NotBlank(message = "Email field is required") @Email(regexp = ".+@.+\\..+|") String email,
							@NotBlank(message = "Enter your valid number") @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number should be 10 number digit") String phone,
							String image, String department, String profession,
							@NotBlank(message = "Name field is required") String postalcode, String prefecture,
							@NotBlank(message = "\tCity name is required") String city, User user) {
						super();
						this.name = name;
						this.secondName = secondName;
						this.email = email;
						this.phone = phone;
						this.image = image;
						this.department = department;
						this.profession = profession;
						this.postalcode = postalcode;
						this.prefecture = prefecture;
						this.city = city;
						this.user = user;
					}
					public Contact() {
                    	
                    }
					@ManyToOne
                    @JsonIgnore
                    private User user;
					public int getcId() {
						return cId;
					}
					public void setcId(int cId) {
						this.cId = cId;
					}
					public String getName() {
						return name;
					}
					public void setName(String name) {
						this.name = name;
					}
					public String getSecondName() {
						return secondName;
					}
					public void setSecondName(String secondName) {
						this.secondName = secondName;
					}
					public String getEmail() {
						return email;
					}
					public void setEmail(String email) {
						this.email = email;
					}
					public String getPhone() {
						return phone;
					}
					public void setPhone(String phone) {
						this.phone = phone;
					}
					public String getImage() {
						return image;
					}
					public void setImage(String image) {
						this.image = image;
					}
					public String getDepartment() {
						return department;
					}
					public void setDepartment(String department) {
						this.department = department;
					}
					public String getProfession() {
						return profession;
					}
					public void setProfession(String profession) {
						this.profession = profession;
					}
					public String getPostalcode() {
						return postalcode;
					}
					public void setPostalcode(String postalcode) {
						this.postalcode = postalcode;
					}
					public String getPrefecture() {
						return prefecture;
					}
					public void setPrefecture(String prefecture) {
						this.prefecture = prefecture;
					}
					public String getCity() {
						return city;
					}
					public void setCity(String city) {
						this.city = city;
					}
					public User getUser() {
						return user;
					}
					public void setUser(User user) {
						this.user = user;
					}
				   
                    
}
