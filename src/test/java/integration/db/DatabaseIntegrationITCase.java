package integration.db;

import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:qir-context-jpa.xml")
public class DatabaseIntegrationITCase extends AbstractJUnit4SpringContextTests {

	@Autowired
	DataSource dataSource;

	@Test
	public void testNotNull() {
		assertThat(dataSource, notNullValue());
	}
}
