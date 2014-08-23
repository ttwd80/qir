package com.twistlet.qir.admin.usermanagement.service;

import com.twistlet.qir.common.model.entity.User;

public interface UserManagementService {
	Iterable<User> listUser();
}
