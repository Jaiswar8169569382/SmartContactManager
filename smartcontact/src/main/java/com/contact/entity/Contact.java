package com.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cid;
	@NotBlank(message = "Name must be required")
	@Size(min = 3,max = 20,message = "Name must be 3 to 20 char long")
	private String name;
	
	private String secondname;
	@NotBlank(message = "Email must be required ")
	private String email;
	@NotBlank(message = "Contact Must be required")
	private String contact;
	private String work;
	@Column(length = 1000)
	private String about;
	@ManyToOne
	@JsonIgnore
	private User user;
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(Long cid,
			@NotBlank(message = "Name must be required") @Size(min = 3, max = 20, message = "Name must be 3 to 20 char long") String name,
			String secondname, @NotBlank(message = "Email must be required ") String email,
			@NotBlank(message = "Contact Must be required") String contact, String work, String about, User user) {
		super();
		this.cid = cid;
		this.name = name;
		this.secondname = secondname;
		this.email = email;
		this.contact = contact;
		this.work = work;
		this.about = about;
		this.user = user;
	}
	public Contact(
			@NotBlank(message = "Name must be required") @Size(min = 3, max = 20, message = "Name must be 3 to 20 char long") String name,
			String secondname, @NotBlank(message = "Email must be required ") String email,
			@NotBlank(message = "Contact Must be required") String contact, String work, String about, User user) {
		super();
		this.name = name;
		this.secondname = secondname;
		this.email = email;
		this.contact = contact;
		this.work = work;
		this.about = about;
		this.user = user;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
