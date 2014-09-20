package com.twistlet.qir.admin.usermanagement.controller;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		final ModelAndView mav = sut.list();
		@SuppressWarnings("unchecked")
		final List<User> list = (List<User>) mav.getModel().get("list");
		assertEquals(4, list.size());
	}

	@Test
	public void testRemoveError() throws JsonProcessingException {
		doThrow(new RuntimeException("ignore: fake exception on delete user"))
				.when(userManagementService).remove("1");
		final Map<String, String> map = sut.remove("1");
		final ObjectMapper objectMapper = new ObjectMapper();
		final String actual = objectMapper.writeValueAsString(map);
		final String expected = "{\"status\":\"error\",\"message\":\"java.lang.RuntimeException: ignore: fake exception on delete user\"}";
		assertThat(actual, equalTo(expected));
	}

	@Test
	public void testRemoveSuccess() throws JsonProcessingException {
		final Map<String, String> map = sut.remove("1");
		final ObjectMapper objectMapper = new ObjectMapper();
		final String actual = objectMapper.writeValueAsString(map);
		final String expected = "{\"status\":\"success\",\"id\":\"1\"}";
		assertThat(actual, equalTo(expected));
	}

	@Test
	public void testPasswordChange() {
		final User user = new User();
		user.setFullname("Bill Joy");
		when(userManagementService.get("abc")).thenReturn(user);
		final ModelAndView mav = sut.passwordChange("abc");
		assertThat(mav.getModel().get("user"), IsSame.sameInstance(user));
	}
}
