package com.twistlet.qir.admin.usermanagement.service;

import static org.hamcrest.core.IsSame.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

public class UserManagementServiceImplTest {
	private UserManagementServiceImpl sut;
	private Iterable<User> items;

	@Mock
	private UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new UserManagementServiceImpl(userRepository);
		items = new ArrayList<User>();
		when(userRepository.findAll()).thenReturn(items);
	}

	@Test
	public void testListUser() {
		Iterable<User> listFromSutCall = sut.listUser();
		assertThat(listFromSutCall, sameInstance(items));
	}
}
