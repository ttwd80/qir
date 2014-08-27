package com.twistlet.qir.admin.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;

	@Autowired
	public UserManagementServiceImpl(PasswordEncoder passwordEncoder,
			UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> listUser() {
		return userRepository.findAll();
	}

	@Override
	public void create(User user, String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

	@Override
	public void remove(String id) {
		userRepository.delete(id);
	}

}
