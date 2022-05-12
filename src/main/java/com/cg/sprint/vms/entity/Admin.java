package com.cg.sprint.vms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Sameeksha
 * Date:- 16/02/2021
 * description:- Admin as entity class, username,password are the attributes of this entity.
 */              

@Entity
@Table(name="admin_details")
public class Admin
{
	@Id
	@Column(name="user_name")
	private String username;
	@Column(name="user_password")
	@Size(min=6,message = "password must be atleast 6 character long") 
	private String password;
	
	public Admin()
	{}
	
	public Admin(String username,
			@Size(min = 6, message = "password must be atleast 6 character long") String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
