package integration.web;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.twistlet.qir.common.model.service.DataInitService;

@ContextConfiguration({ "classpath:test-qir-context-config.xml",
		"classpath:selenium-test-spring-context.xml",
		"classpath:qir-context-es-config.xml",
		"classpath:qir-context-es-client.xml",
		"classpath:qir-context-es-data-init.xml" })
public abstract class AbstractWebITCase extends
		AbstractJUnit4SpringContextTests {

	protected WebDriver webDriver;

	@Autowired
	private DataInitService dataInitService;

	@Value("#{config['url.base']}")
	protected String baseUrl;

	@Value("#{config['wait.element.seconds']}")
	protected int secondsToWaitForElement;

	@Before
	public void initData() {
		dataInitService.clear();
		dataInitService.init();
		webDriver = new PhantomJSDriver();
		final Options options = webDriver.manage();
		final Window window = options.window();
		window.setSize(new Dimension(1280, 800));
	}

	@After
	public void closeBrowsers() {
		webDriver.quit();
	}

	private void loadLoginPage() {
		webDriver.get(baseUrl);
	}

	protected WebDriver login(final String username, final String password) {
		loadLoginPage();
		final WebDriverWait wait = new WebDriverWait(webDriver,
				secondsToWaitForElement);
		wait.until(presenceOfElementLocated(By.id("j_username")));
		webDriver.findElement(By.id("j_username")).sendKeys(username);
		webDriver.findElement(By.id("j_password")).sendKeys(password);
		webDriver.findElement(By.id("submit")).click();
		return webDriver;
	}

	protected WebDriver loginAdmin() {
		return login("admin", "cefew86traqe");
	}

}
