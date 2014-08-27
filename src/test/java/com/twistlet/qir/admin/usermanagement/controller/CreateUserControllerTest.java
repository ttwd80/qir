package com.twistlet.qir.admin.usermanagement.controller;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

import com.twistlet.qir.admin.usermanagement.service.UserManagementService;
import com.twistlet.qir.common.exception.DatabaseException;
import com.twistlet.qir.common.exception.FormValidationException;
import com.twistlet.qir.common.model.entity.User;

public class CreateUserControllerTest {

	private CreateUserController sut;

	@Mock
	private UserManagementService userManagementService;

	@Mock
	private Errors errors;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		sut = new CreateUserController(userManagementService);
	}

	@Test
	public void testInitBinder() {
		WebDataBinder dataBinder = new WebDataBinder(null);
		sut.initBinder(dataBinder);
		List<Validator> list = dataBinder.getValidators();
		boolean found = false;
		for (Validator validator : list) {
			if (validator instanceof CreateUserFormValidator) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	@Test(expected = FormValidationException.class)
	public void testInsertFormHasErrors() {
		when(errors.hasErrors()).thenReturn(Boolean.TRUE);
		sut.insert(null, errors);
		fail();
	}

	@Test(expected = DatabaseException.class)
	public void testInsertDatabaseHasErrors() {
		when(errors.hasErrors()).thenReturn(Boolean.FALSE);
		doThrow(new RuntimeException()).when(userManagementService).create(
				any(User.class), any(String.class));
		sut.insert(new CreateUserForm(), errors);
		fail();
	}

	@Test
	public void testInsertOk() {
		when(errors.hasErrors()).thenReturn(Boolean.FALSE);
		String value = sut.insert(new CreateUserForm(), errors);
		assertThat(value, equalTo("redirect:/admin/user/list"));
	}
}
