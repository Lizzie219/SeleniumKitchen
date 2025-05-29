import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;

import org.junit.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfileEditPage;
import pages.ProfilePage;

import static org.apache.commons.io.FileUtils.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTest {
	private RemoteWebDriver driver;

	@Before
	public void setup() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		driver.setFileDetector(new LocalFileDetector());
		this.driver.manage().window().maximize();
	}

	@Test
	public void testSelenium() throws InterruptedException {
		// Login
		HomePage homePage = new HomePage(this.driver);
		homePage.acceptCookies();
		assertEquals("Street Kitchen - Ahol a főzés kezdődik - Fördős Zé bemutatja", homePage.getTitle());
		assertTrue(homePage.isLoggedOut());

		// Hover
		assertEquals("rgba(0, 0, 0, 1)", homePage.getLoginButtonColor());
		assertEquals("rgba(255, 245, 0, 1)", homePage.getLoginButtonColorHovered());

		// Login continued
		LoginPage loginPage = homePage.goToLoginPage();
		assertEquals("Belépés | Street Kitchen", loginPage.getTitle());
		assertTrue(homePage.isLoggedOut());
		ProfilePage profilePage = loginPage.logIn();
		assertTrue(homePage.isLoggedIn());
		assertEquals("Profilom | Street Kitchen", profilePage.getTitle());

		ProfileEditPage profileEditPage = profilePage.goToProfileEditPage();
		assertEquals("Profilom | Street Kitchen", profileEditPage.getTitle());
		assertFalse(profileEditPage.hasProfilePicture());
		profileEditPage.uploadProfilePicture(getFile("picture.jpg"));
		assertTrue(profileEditPage.hasProfilePicture());
		profileEditPage.deleteProfilePicture();
		assertFalse(profileEditPage.hasProfilePicture());
	}

	@After
	public void close() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}

	private File getFile(String fileName) {
		URL resource = getClass().getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new RuntimeException("Resource not found: " + fileName);
		}
		return new File(resource.getFile());
	}
}
