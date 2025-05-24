import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import org.junit.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class MainTest {
	private WebDriver driver;

	@Before
	public void setup() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		this.driver.manage().window().maximize();
	}

	@Test
	public void testSelenium() {
		HomePage homePage = new HomePage(this.driver);
		homePage.acceptCookies();
		LoginPage loginPage = homePage.goToLoginPage();
		ProfilePage profilePage = loginPage.logIn();

		Assert.assertTrue(profilePage.isLoginSuccessful());
	}

	@After
	public void close() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}
}
