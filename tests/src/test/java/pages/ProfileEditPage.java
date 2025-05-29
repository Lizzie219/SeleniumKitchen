package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileEditPage extends Page {
    private static final By PROFILE_PICTURE_UPLOAD_BUTTON = By.xpath("//input[@class='profile-pic-upload' " + POPUP_CONTENT_FILTER + "]");
    private static final By SAVE_CHANGES_ON_PROFILE_BUTTON = By.xpath("//input[@class='user-registration-Button button ' " + POPUP_CONTENT_FILTER + "]");
    private static final By DELETE_PROFILE_PICTURE_BUTTON = By.xpath("//button[@class='button profile-pic-remove' " + POPUP_CONTENT_FILTER + "]");
    private static final By PROFILE_PICTURE = By.xpath("//img[@class='profile-preview' " + POPUP_CONTENT_FILTER + "]");
    private static final String PROFILE_PICTURE_PLACEHOLDER_IMAGE_URL = "https://secure.gravatar.com/avatar/6f6be1025fd636ab694043f166a75670?s=96&d=mm&r=g";

    public ProfileEditPage(WebDriver driver) {
        super(driver);
    }

    public void uploadProfilePicture(File file) {
        waitAndReturnElement(PROFILE_PICTURE_UPLOAD_BUTTON).sendKeys(file.getAbsolutePath());
        waitAndReturnElement(SAVE_CHANGES_ON_PROFILE_BUTTON).click();
    }

    public void deleteProfilePicture() {
        waitAndReturnElement(DELETE_PROFILE_PICTURE_BUTTON).click();
        waitAndReturnElement(SAVE_CHANGES_ON_PROFILE_BUTTON).click();
    }

    public boolean hasProfilePicture() {
        return !waitAndReturnElement(PROFILE_PICTURE).getAttribute("src").equals(PROFILE_PICTURE_PLACEHOLDER_IMAGE_URL);
    }
}
