package com.twistlet.qir.admin.usermanagement.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;

public class CreateUserFormValidatorTest {

	private CreateUserFormValidator sut;

	@Mock
	private Errors errors;

	@Before
	public void init() {
		sut = new CreateUserFormValidator();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAssignableSubclass() {
		CreateUserForm form = new CreateUserForm() {
		};
		assertEquals(true, sut.supports(form.getClass()));
	}

	@Test
	public void testAssignableExact() {
		CreateUserForm form = new CreateUserForm();
		assertEquals(true, sut.supports(form.getClass()));
	}

	@Test
	public void testAssignableObject() {
		Object form = new Object();
		assertEquals(false, sut.supports(form.getClass()));
	}

	@Test
	public void testAssignableString() {
		String form = new String();
		assertEquals(false, sut.supports(form.getClass()));
	}

	@Test
	public void testAssignablePolymorphism() {
		Object form = new CreateUserForm();
		assertEquals(true, sut.supports(form.getClass()));
	}

	@Test
	public void testNoUsername() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername(null);
		sut.validate(form, errors);
		verify(errors, atLeastOnce()).reject(any(String.class));
	}

	@Test
	public void testUsernameOkNoPassword() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername("user2012");
		form.setPassword(null);
		sut.validate(form, errors);
		verify(errors, atLeastOnce()).reject(any(String.class));
	}

	@Test
	public void testUsernameOkPasswordOkNoConfirm() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername("user2012");
		form.setPassword("123");
		form.setPasswordConfirm(null);
		sut.validate(form, errors);
		verify(errors, atLeastOnce()).reject(any(String.class));
	}

	@Test
	public void testUsernameOkPasswordOkConfirmMismatch() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername("user2012");
		form.setPassword("123");
		form.setPasswordConfirm("133");
		sut.validate(form, errors);
		verify(errors, atLeastOnce()).reject(any(String.class));
	}

	@Test
	public void testUsernameOkPasswordOkConfirmOkEnabledNull() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername("user2012");
		form.setPassword("123");
		form.setPasswordConfirm("122");
		form.setEnabled(null);
		sut.validate(form, errors);
		verify(errors, atLeastOnce()).reject(any(String.class));
	}

	@Test
	public void testUsernameOkPasswordOkConfirmOkEnabledFalse() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername("user2012");
		form.setPassword("123");
		form.setPasswordConfirm("123");
		form.setEnabled(Boolean.FALSE);
		sut.validate(form, errors);
		verifyZeroInteractions(errors);
	}

	@Test
	public void testUsernameOkPasswordOkConfirmOkEnabledTrue() {
		CreateUserForm form = new CreateUserForm();
		form.setUsername("user2012");
		form.setPassword("123");
		form.setPasswordConfirm("123");
		form.setEnabled(Boolean.TRUE);
		sut.validate(form, errors);
		verifyZeroInteractions(errors);
	}

}
