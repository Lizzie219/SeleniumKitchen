package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaticPage extends Page{
    private final String title;
    private final String url;

    public StaticPage(WebDriver driver, String title, String url) {
        super(driver);
        this.title = title;
        this.url = url;
    }

    public boolean checkTitle() {
        driver.get(url);
        boolean titleCorrect = driver.getTitle().equals(title);
        driver.navigate().back();
        return titleCorrect;
    }
}
