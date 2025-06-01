package pages;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	private static final By BODY_LOCATOR = By.tagName("body");
	private static final By LOGGED_OUT_NAV = By.xpath("//nav[contains(@class, 'nav-user-logged-out')]");
	private static final By LOGGED_IN_NAV = By.xpath("//nav[contains(@class, 'nav-user-logged-in')]");
	private static final By LOG_OUT_BUTTON = By.xpath("//a[@href='/profilom/kilepes/']");
	private static final By SEARCH_RECIPE_INPUT = By.xpath("//div[contains(@class, 'desktop')]//input[@class='search-field']");
	private static final By SEARCH_RECIPE_BUTTON = By.xpath("//div[contains(@class, 'desktop')]//input[@class='search-submit']");
	protected static final String POPUP_CONTENT_FILTER = "and not(ancestor::div[contains(@class, 'popup')])";

	protected final WebDriver driver;
	protected final WebDriverWait wait;

	protected static final Config CONFIG = Config.getConfig();

	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 25);
	}

	public final String getBodyText() {
		WebElement body = waitAndReturnElement(BODY_LOCATOR);
		return body.getText();
	}

	public final boolean isLoggedIn() {
		return waitPresenceAndReturnElement(LOGGED_IN_NAV).isDisplayed()
				&& !waitPresenceAndReturnElement(LOGGED_OUT_NAV).isDisplayed();
	}

	public final boolean isLoggedOut() {
		return waitPresenceAndReturnElement(LOGGED_OUT_NAV).isDisplayed()
				&& !waitPresenceAndReturnElement(LOGGED_IN_NAV).isDisplayed();
	}

	public void logOut(){
		waitAndReturnElement(LOG_OUT_BUTTON).click();
	}

	protected WebElement waitAndReturnElement(By locator) {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	public final String getTitle() {
		return this.driver.getTitle();
	}

	public SearchResultPage searchingForRecipe() {
		waitAndReturnElement(SEARCH_RECIPE_INPUT).sendKeys(CONFIG.getValuesForRecipeSearching().getKey());
		waitAndReturnElement(SEARCH_RECIPE_BUTTON).click();
		return new SearchResultPage(this.driver, CONFIG.getValuesForRecipeSearching().getValue());
	}

	private WebElement waitPresenceAndReturnElement(By locator) {
		this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
	}
}
