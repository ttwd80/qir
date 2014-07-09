package com.twistlet.qir.common.model.entity;

import org.junit.Test;

public class BasicEntityTest {

	@Test
	public void increaseCoverageTest() {
		fullTestCoverage(User.class);
		fullTestCoverage(Role.class);
	}

	private void fullTestCoverage(final Class<?> class1) {
		// TODO Auto-generated method stub

	}

}
