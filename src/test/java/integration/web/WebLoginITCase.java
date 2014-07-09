package integration.web;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class WebLoginITCase extends AbstractWebITCase {

	@Test
	public void loginAdminBad() {
		login("admin", "bad");
		final String actual = webDriver.getCurrentUrl();
		assertThat(actual, equalTo(baseUrl + "/login?fail=true"));
	}

	@Test
	public void loginAdminOk() {
		login("admin", "password123!");
		final String actual = webDriver.getCurrentUrl();
		assertThat(actual, equalTo(baseUrl + "/admin/index"));
	}

	@Test
	public void loginUserBad() {
		login("user01", "bad");
		final String actual = webDriver.getCurrentUrl();
		assertThat(actual, equalTo(baseUrl + "/login?fail=true"));
	}

	@Test
	public void loginUserOk() {
		login("user01", "user01");
		final String actual = webDriver.getCurrentUrl();
		assertThat(actual, equalTo(baseUrl + "/user/index"));
	}

}
