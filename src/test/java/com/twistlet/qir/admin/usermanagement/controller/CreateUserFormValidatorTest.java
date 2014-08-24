package com.twistlet.qir.admin.usermanagement.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreateUserFormValidatorTest {

	private CreateUserFormValidator sut;

	@Before
	public void init() {
		sut = new CreateUserFormValidator();
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

}
