package com.twistlet.qir.admin.usermanagement.service;

import java.util.List;

import com.twistlet.qir.common.model.entity.Role;

public interface RoleManagementService {
	void create(Role role);

	void update(Role role);

	void remove(String rolename);

	void assign(List<String> usernames, String rolename);

	List<Role> list();
}
