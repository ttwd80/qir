package com.twistlet.qir.common.exception;

import org.junit.Test;

import pojo.AbstractPojoTest;

public class PojoTest extends AbstractPojoTest {

	@Test
	@Override
	public void testPojo() {
		testClass(DatabaseException.class);
		testClass(FormValidationException.class);
	}
}
