package com.twistlet.qir.admin.usermanagement.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.qir.admin.usermanagement.service.UserManagementService;
import com.twistlet.qir.common.model.entity.User;

public class UserManagementControllerTest {

	private UserManagementController sut;

	private List<User> list;

	@Mock
	private UserManagementService userManagementService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		list = new ArrayList<User>();
		list.add(new User());
		list.add(new User());
		list.add(new User());
		list.add(new User());
		when(userManagementService.listUser()).thenReturn(list);
		sut = new UserManagementController(userManagementService);
	}

	@Test
	public void testList() {
		ModelAndView mav = sut.list();
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) mav.getModel().get("list");
		assertEquals(4, list.size());
	}
}
