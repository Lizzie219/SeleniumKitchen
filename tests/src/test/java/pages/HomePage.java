package pages;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class HomePage extends Page {
	private static final Config CONFIG = Config.getConfig();
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
}
