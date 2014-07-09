package integration.db;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.aop.SpringProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration({ "classpath:qir-context-security.xml",
		"classpath:qir-context-jpa.xml",
		"classpath:qir-context-spring-data.xml" })
public class IntegrationAOPTransactionITCase extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	UserDetailsService userDetailsService;

	@Test
	public void testAop() {
		final Class<?>[] items = userDetailsService.getClass().getInterfaces();
		final List<?> list = Arrays.asList(items);
		assertTrue(list.contains(SpringProxy.class));
	}
}
