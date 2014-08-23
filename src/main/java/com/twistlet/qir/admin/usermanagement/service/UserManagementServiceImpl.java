package com.twistlet.qir.admin.usermanagement.service;

import org.springframework.stereotype.Service;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private UserRepository userRepository;

	public UserManagementServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> listUser() {
		return userRepository.findAll();
	}

}
