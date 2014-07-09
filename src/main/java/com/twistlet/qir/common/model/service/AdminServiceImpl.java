package com.twistlet.qir.common.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twistlet.qir.common.model.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

	private final UserRepository userRepository;

	@Autowired
	public AdminServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public long countRegisteredUsers() {
		return userRepository.count();
	}

}
