package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	private static final By BODY_LOCATOR = By.tagName("body");
	private static final By LOGGED_OUT_NAV = By.xpath("//nav[contains(@class, 'nav-user-logged-out')]");
	private static final By LOGGED_IN_NAV = By.xpath("//nav[contains(@class, 'nav-user-logged-in')]");
	protected static final String POPUP_CONTENT_FILTER = "and not(ancestor::div[contains(@class, 'popup')])";

	protected final WebDriver driver;
	protected final WebDriverWait wait;

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

	protected WebElement waitAndReturnElement(By locator) {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	public final String getTitle() {
		return this.driver.getTitle();
	}

	private WebElement waitPresenceAndReturnElement(By locator) {
		this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
	}
}
