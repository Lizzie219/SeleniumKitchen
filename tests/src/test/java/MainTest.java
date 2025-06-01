import config.Config;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;

import org.junit.*;
import pages.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTest {
	private RemoteWebDriver driver;
	private static final Config CONFIG = Config.getConfig();

	@Before
	public void setup() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("start-maximized");
		this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		driver.setFileDetector(new LocalFileDetector());
	}

	@Test
	public void testSelenium() throws InterruptedException {
		// Title test
		HomePage homePage = new HomePage(this.driver);
		homePage.acceptCookies();
		assertEquals("Street Kitchen - Ahol a főzés kezdődik - Fördős Zé bemutatja", homePage.getTitle());
		assertTrue(homePage.isLoggedOut());

		// Hover test
		assertEquals("rgba(0, 0, 0, 1)", homePage.getLoginButtonColor());
		assertEquals("rgba(255, 245, 0, 1)", homePage.getLoginButtonColorHovered());

		// Login test continued
		LoginPage loginPage = homePage.goToLoginPage();
		assertEquals("Belépés | Street Kitchen", loginPage.getTitle());
		assertTrue(homePage.isLoggedOut());
		ProfilePage profilePage = loginPage.logIn();
		assertTrue(homePage.isLoggedIn());
		assertEquals("Profilom | Street Kitchen", profilePage.getTitle());

		// Upload photo test
		ProfileEditPage profileEditPage = profilePage.goToProfileEditPage();
		assertEquals("Profilom | Street Kitchen", profileEditPage.getTitle());
		assertFalse(profileEditPage.hasProfilePicture());
		profileEditPage.uploadProfilePicture(getFile("picture.jpg"));
		assertTrue(profileEditPage.hasProfilePicture());
		profileEditPage.deleteProfilePicture();
		assertFalse(profileEditPage.hasProfilePicture());

		// Send form as a user - change first and last name to a random name test
		profileEditPage.changeNameToRandom();
		assertTrue(profileEditPage.isNameChangeSuccessful());

		// Search for a recipe and save it test
		SearchResultPage searchResultPage = profileEditPage.searchingForRecipe();
		RecipePage recipePage = searchResultPage.navigateToRecipePage();
		recipePage.addRecipeToSavedRecipes();
		SavedRecipesPage savedRecipePage = recipePage.navigateToSavedRecipes();
		assertTrue(savedRecipePage.isRecipeSaved(CONFIG.getValuesForRecipeSearching().getValue()));
		savedRecipePage.removeRecipeFromSaved(CONFIG.getValuesForRecipeSearching().getValue());

		// static tests
		testStaticPages();

		// logout
		savedRecipePage.logOut();
		assertTrue(savedRecipePage.isLoggedOut());
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

	private void testStaticPages() {
        Map<String, String> staticPages = CONFIG.getStaticPages();
		staticPages.forEach((title, url) -> {
            StaticPage page = new StaticPage(driver, title, url);
			assertTrue(page.checkTitle());
		});
	}
}
