package com.twistlet.qir.common.model.service;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.twistlet.qir.common.model.repository.UserRepository;

public class AdminServiceImplTest {

	private AdminServiceImpl sut;

	@Mock
	private UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new AdminServiceImpl(userRepository);
	}

	@Test
	public void testCountRegisteredUsers() {
		when(userRepository.count()).thenReturn(19L);
		assertThat(sut.countRegisteredUsers(), equalTo(19L));
	}
}
