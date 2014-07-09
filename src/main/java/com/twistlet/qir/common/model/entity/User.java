package com.twistlet.qir.common.model.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "user")
public class User implements Serializable {

	@Id
	private String username;
	private String fullname;
	private String password;
	private List<String> roles;

	public User() {
	}

	public User(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(final String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(final List<String> roles) {
		this.roles = roles;
	}

}
