package integration.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebLogoutITCase extends AbstractWebITCase {

	@Test
	public void testLoginAndLogout() {
		WebDriver webdriver = loginAdmin();
		WebElement linkLogout = webdriver.findElement(By.id("link-logout"));
		linkLogout.click();
		String url = webdriver.getCurrentUrl();
		assertEquals(baseUrl + "/login", url);
	}
}
