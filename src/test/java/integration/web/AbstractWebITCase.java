package integration.web;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration({ "classpath:selenium-test-spring-context.xml",
		"classpath:qir-context-datasource.xml" })
public abstract class AbstractWebITCase extends
		AbstractJUnit4SpringContextTests {

	protected WebDriver webDriver;

	@Value("#{config['url.base']}")
	protected String baseUrl;

	@Value("#{config['wait.element.seconds']}")
	protected int secondsToWaitForElement;

	@Autowired
	protected DataSource dataSource;

	@Before
	public void initData() {
		//TODO populate data
	}

	@After
	public void closeBrowsers() {
		webDriver.quit();
	}

	private void loadLoginPage() {
		webDriver = new PhantomJSDriver();
		webDriver.get(baseUrl);
	}

	protected WebDriver login(final String username, final String password) {
		loadLoginPage();
		final WebDriverWait wait = new WebDriverWait(webDriver,
				secondsToWaitForElement);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id("j_username")));
		webDriver.findElement(By.id("j_username")).sendKeys(username);
		webDriver.findElement(By.id("j_password")).sendKeys(password);
		webDriver.findElement(By.id("submit")).click();
		return webDriver;
	}
}
