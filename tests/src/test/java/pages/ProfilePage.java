package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ProfilePage extends Page{
    private static final By PROFILE_PAGE_TITLE = By.xpath("(/html/head/title)[1]");
    private static final By LOGOUT_BUTTON = By.xpath("//a[@href='/profilom/kilepes/']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoginSuccessful(){
        return !Objects.equals(waitAndReturnElement(PROFILE_PAGE_TITLE).getText(), "Profilom | Street Kitchen");
    }
    public void logOut(){
        waitAndReturnElement(LOGOUT_BUTTON).click();
    }
}
