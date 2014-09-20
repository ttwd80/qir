package com.twistlet.qir.admin.usermanagement.service;

import static org.hamcrest.core.IsSame.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

public class UserManagementServiceImplTest {
	private UserManagementServiceImpl sut;
	private Iterable<User> items;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new UserManagementServiceImpl(passwordEncoder, userRepository);
		items = new ArrayList<User>();
		when(userRepository.findAll()).thenReturn(items);
	}

	@Test
	public void testListUser() {
		final Iterable<User> listFromSutCall = sut.listUser();
		assertThat(listFromSutCall, sameInstance(items));
		verifyZeroInteractions(passwordEncoder);
	}

	@Test
	public void testCreate() {
		when(passwordEncoder.encode("abc")).thenReturn("***");
		final User user = new User();
		sut.create(user, "abc");
		verify(passwordEncoder).encode("abc");
		verify(userRepository).save(any(User.class));
		verifyNoMoreInteractions(passwordEncoder);
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	public void testRemove() {
		sut.remove("100");
		verify(userRepository).delete("100");
		verifyNoMoreInteractions(passwordEncoder);
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	public void testGet() {
		final User user = new User();
		when(userRepository.findOne("random#id")).thenReturn(user);
		assertThat(sut.get("random#id"), IsSame.sameInstance(user));
	}
}
