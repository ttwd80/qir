package com.twistlet.qir.common.controller;

import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

public class IndexControllerTest {

	IndexController sut;

	@Mock
	private NativeWebRequest request;

	@Before
	public void init() {
		final Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("ROLE_USER", "redirect:/user/index");
		map.put("ROLE_ADMIN", "redirect:/admin/index");
		sut = new IndexController(map);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetReturnNull() {
		final ModelAndView mav = sut.get(request);
		assertThat(mav, nullValue());
	}

	@Test
	public void testGetReturnOk() {
		when(request.isUserInRole("ROLE_ADMIN")).thenReturn(Boolean.TRUE);
		final ModelAndView mav = sut.get(request);
		assertThat(mav.getViewName(), equalTo("redirect:/admin/index"));
	}

}
