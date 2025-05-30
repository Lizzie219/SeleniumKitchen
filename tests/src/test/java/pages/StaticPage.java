package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaticPage extends Page{
    private final By locator;
    private final String title;

    public StaticPage(WebDriver driver, String title, String locatorString) {
        super(driver);
        this.title = title;
        this.locator = By.xpath(locatorString);
    }

    public boolean checkTitle() {
        waitAndReturnElement(locator).click();
        return driver.getTitle().equals(title);
    }
}
