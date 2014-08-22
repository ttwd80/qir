package com.twistlet.qir.common.model.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.twistlet.qir.common.model.repository.RoleRepository;
import com.twistlet.qir.common.model.repository.UserRepository;

public class ElasticsearchDataInitServiceTest {

	private ElasticsearchDataInitService sut;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new ElasticsearchDataInitService(new BCryptPasswordEncoder(),
				userRepository, roleRepository);
	}

	@Test
	public void testInit() {
		sut.init();
	}

}
