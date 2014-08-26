package com.twistlet.qir.common.model.entity;

import org.junit.Test;

import pojo.AbstractPojoTest;

public class PojoTest extends AbstractPojoTest {

	@Test
	@Override
	public void testPojo() {
		testClass(User.class);
		testClass(Role.class);
	}

}
