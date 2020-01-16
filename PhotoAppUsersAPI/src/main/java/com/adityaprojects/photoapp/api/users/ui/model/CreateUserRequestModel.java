package com.adityaprojects.photoapp.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
	
	@NotNull(message="First name cannot be null")
	@Size(min=2, message="Minimum characters in first name is 2")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=2, message="Minimum characters in last name is 2")
	private String lastName;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=20, message="Password length needs to be greater than 8 and less than 20")
	private String password;
	
	@NotNull(message="Email cannot be null")
	@Email
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
