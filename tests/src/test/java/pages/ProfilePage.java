package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ProfilePage extends Page{
    private static final By PROFILE_EDIT_PAGE_BUTTON = By.xpath("//li[contains(@class, 'edit-profile') " + POPUP_CONTENT_FILTER + "]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfileEditPage goToProfileEditPage() {
        waitAndReturnElement(PROFILE_EDIT_PAGE_BUTTON).click();
        return new ProfileEditPage(driver);
    }
}
