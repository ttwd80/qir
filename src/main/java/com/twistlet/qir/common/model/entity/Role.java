package com.twistlet.qir.common.model.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "#{esconfig['es.indexname']}", type = "role")
public class Role implements Serializable {
	@Id
	private String rolename;

	private String description;

	public Role() {
	}

	public Role(final String rolename) {
		this.rolename = rolename;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(final String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
