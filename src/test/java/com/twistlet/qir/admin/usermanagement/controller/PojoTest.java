package com.twistlet.qir.admin.usermanagement.controller;

import org.junit.Test;

import pojo.AbstractPojoTest;

public class PojoTest extends AbstractPojoTest {

	@Test
	@Override
	public void testPojo() {
		testClass(CreateUserForm.class);
	}
}
