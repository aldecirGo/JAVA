package com.web.tornese.SpringWeb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_admisntrators")
public class Administrator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "email", nullable = false, length = 180)
	private String email;
	
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	
	@Column(name = "note", columnDefinition = "text")
	private String note;

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
	
	
	@Override
	public String toString() {
		return "Adminstrator [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", note="
				+ note + "]";
	}


	public Administrator(String name, String email, String password, String note) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.note = note;
	}


	public Administrator() {
		
	}
	
	
}
