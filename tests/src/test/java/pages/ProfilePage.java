package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ProfilePage extends Page{
    private static final By LOGGED_IN_SECTION = By.xpath("//div[@class='nav-user nav-user-logged-in']");
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoginSuccessful(){
        return !Objects.equals(waitAndReturnElement(LOGGED_IN_SECTION).getCssValue("display"), "none");
    }
}
