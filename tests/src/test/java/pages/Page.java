package pages;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	private static final By BODY_LOCATOR = By.tagName("body");

	protected final WebDriver driver;
	protected final WebDriverWait wait;

	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 100);
	}

	public String getBodyText() {
		WebElement body = waitAndReturnElement(BODY_LOCATOR);
		return body.getText();
	}

	protected WebElement waitAndReturnElement(By locator) {
		this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}
}
