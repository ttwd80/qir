package com.twistlet.qir.admin.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Autowired
	public UserManagementServiceImpl(final PasswordEncoder passwordEncoder,
			final UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> listUser() {
		return userRepository.findAll();
	}

	@Override
	public void create(final User user, final String rawPassword) {
		final String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}

	@Override
	public void remove(final String id) {
		userRepository.delete(id);
	}

	@Override
	public User get(final String id) {
		return userRepository.findOne(id);
	}

}
