package pages;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
	private static final By LOGIN_EMAIL = By.xpath("(//input[@id='username'])[1]");
	private static final By LOGIN_PASSWORD = By.xpath("(//input[@id='password'])[1]");
	private static final By LOGIN_BUTTON = By.xpath("(//input[@class='user-registration-Button button '])[1]");
	private static final Config CONFIG = Config.getConfig();

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public ProfilePage logIn() {
		waitAndReturnElement(LOGIN_EMAIL).sendKeys(CONFIG.getEmail());
		waitAndReturnElement(LOGIN_PASSWORD).sendKeys(CONFIG.getPassword());
		waitAndReturnElement(LOGIN_BUTTON).click();

		return new ProfilePage(driver);
	}
}
