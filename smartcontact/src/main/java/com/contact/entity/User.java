package com.contact.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;





@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uid;
	@NotBlank(message = "Frstname must be required")
	@Size(min = 3,max = 20,message = "Firstname must be in between 3 to 20 char long")
	private String firstname;
	@NotBlank(message = "Last name must be required")
	@Size(min = 3,max = 20,message = "Lastname must be in between 3 to 20 char long")
	private String lastname;
	@NotBlank(message = "Email must be required")
	@Email(message = "Please enter valid email")
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Password must be required")
	@Size(min = 8,message = "Password have minimum 8 char long")
	private String password;
	private String role;
	private String gender;
	private boolean enable;
	@Column(length = 64,updatable = false)
	private String verificationcode;
	
	@Column(length = 1000)
     private String about;
	@CreationTimestamp
	private LocalDateTime rdate;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	private Set<Contact> contact= new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long uid,
			@NotBlank(message = "Frstname must be required") @Size(min = 3, max = 20, message = "Firstname must be in between 3 to 20 char long") String firstname,
			@NotBlank(message = "Last name must be required") @Size(min = 3, max = 20, message = "Lastname must be in between 3 to 20 char long") String lastname,
			@NotBlank(message = "Email must be required") @Email(message = "Please enter valid email") String email,
			@NotBlank(message = "Password must be required") @Size(min = 8, message = "Password have minimum 8 char long") String password,
			String role, String gender, boolean enable, String verificationcode, String about, LocalDateTime rdate,
			Set<Contact> contact) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.gender = gender;
		this.enable = enable;
		this.verificationcode = verificationcode;
		this.about = about;
		this.rdate = rdate;
		this.contact = contact;
	}

	public User(
			@NotBlank(message = "Frstname must be required") @Size(min = 3, max = 20, message = "Firstname must be in between 3 to 20 char long") String firstname,
			@NotBlank(message = "Last name must be required") @Size(min = 3, max = 20, message = "Lastname must be in between 3 to 20 char long") String lastname,
			@NotBlank(message = "Email must be required") @Email(message = "Please enter valid email") String email,
			@NotBlank(message = "Password must be required") @Size(min = 8, message = "Password have minimum 8 char long") String password,
			String role, String gender, boolean enable, String verificationcode, String about, LocalDateTime rdate,
			Set<Contact> contact) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.gender = gender;
		this.enable = enable;
		this.verificationcode = verificationcode;
		this.about = about;
		this.rdate = rdate;
		this.contact = contact;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public LocalDateTime getRdate() {
		return rdate;
	}

	public void setRdate(LocalDateTime rdate) {
		this.rdate = rdate;
	}

	public Set<Contact> getContact() {
		return contact;
	}

	public void setContact(Set<Contact> contact) {
		this.contact = contact;
	}

	
}
