package com.twistlet.qir.common.model.entity;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "role")
public class Role implements Serializable {
	private String rolename;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(final String rolename) {
		this.rolename = rolename;
	}

}
