package com.twistlet.qir.common.model.service;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.twistlet.qir.common.model.entity.Role;
import com.twistlet.qir.common.model.entity.User;
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
	public void testInitOnNonEmptyData() {
		when(userRepository.count()).thenReturn(1L);
		when(roleRepository.count()).thenReturn(1L);
		sut.init();
		verify(userRepository).count();
		verify(roleRepository).count();
		verifyNoMoreInteractions(userRepository);
		verifyNoMoreInteractions(roleRepository);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testInitOnEmptyData() {
		when(userRepository.count()).thenReturn(0L);
		when(roleRepository.count()).thenReturn(0L);
		sut.init();
		verify(userRepository).count();
		verify(roleRepository).count();
		verify(userRepository, atLeastOnce()).save(isA(User.class));
		verify(roleRepository, atLeastOnce()).save(
				(Iterable<Role>) isA(Iterable.class));
	}
}
