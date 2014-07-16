package integration.db;

import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.twistlet.qir.common.model.entity.User;
import com.twistlet.qir.common.model.repository.UserRepository;

@ContextConfiguration({ "classpath:qir-context-es-client.xml",
		"classpath:qir-context-es-config.xml" })
public class ElasticSearchITCase extends AbstractJUnit4SpringContextTests {

	@Autowired
	UserRepository userRepository;

	@Before
	public void init() {
		userRepository.deleteAll();
	}

	@Test
	public void testNotNull() {
		assertThat(userRepository, notNullValue());
	}

	@Test
	public void testSaveNull() {
		final User user1 = new User();
		user1.setUsername("a");
		userRepository.save(user1);
		assertThat(userRepository.findOne("a"), notNullValue());
	}

	@Test
	public void testGetNull() {
		assertThat(userRepository.findOne("a"), nullValue());
	}
}
