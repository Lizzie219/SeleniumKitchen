package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class HomePage extends Page {
	private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//a[@href='/belepes/']");
	private static final By COOKIE_ACCEPT_BUTTON = By.xpath("//button[@id='accept-btn']");

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver.get(CONFIG.getUrl());
	}

	public LoginPage goToLoginPage() {
		waitAndReturnElement(LOGIN_BUTTON_LOCATOR).click();
		return new LoginPage(driver);
	}

	public void acceptCookies(){
		waitAndReturnElement(COOKIE_ACCEPT_BUTTON).click();
	}

	public String getLoginButtonColorHovered() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(LOGIN_BUTTON_LOCATOR)).perform();
		TimeUnit.SECONDS.sleep(1);
		return getLoginButtonColor();
	}

	public String getLoginButtonColor() {
		return getLoginButton().getCssValue("background-color");
	}

	private WebElement getLoginButton() {
		return waitAndReturnElement(LOGIN_BUTTON_LOCATOR);
	}
}
