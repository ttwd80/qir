package com.twistlet.qir.common.controller;

import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.qir.common.model.service.AdminService;

public class AdminControllerTest {

	private AdminController sut;

	@Mock
	private AdminService adminService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new AdminController(adminService);
	}

	@Test
	public void testIndex() {
		when(adminService.countRegisteredUsers()).thenReturn(5L);
		final ModelAndView mav = sut.index();
		assertThat(mav.getViewName(), nullValue());
		assertThat(mav.getModelMap().size(), equalTo(1));
		assertThat((Long) mav.getModelMap().get("count"), equalTo(5L));
	}

}
