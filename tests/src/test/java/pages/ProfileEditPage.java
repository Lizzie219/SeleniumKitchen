package pages;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;

public class ProfileEditPage extends Page {
    private static final By PROFILE_PICTURE_UPLOAD_BUTTON = By.xpath("//input[@class='profile-pic-upload' " + POPUP_CONTENT_FILTER + "]");
    private static final By SAVE_CHANGES_ON_PROFILE_BUTTON = By.xpath("//input[@class='user-registration-Button button ' " + POPUP_CONTENT_FILTER + "]");
    private static final By DELETE_PROFILE_PICTURE_BUTTON = By.xpath("//button[@class='button profile-pic-remove' " + POPUP_CONTENT_FILTER + "]");
    private static final By PROFILE_PICTURE = By.xpath("//img[@class='profile-preview' " + POPUP_CONTENT_FILTER + "]");
    private static final By LAST_NAME = By.xpath("//input[@name='user_registration_last_name' " + POPUP_CONTENT_FILTER + "]");
    private static final By FIRST_NAME = By.xpath("//input[@name='user_registration_first_name' " + POPUP_CONTENT_FILTER + "]");
    private static final By SEARCH_RECIPE_INPUT = By.xpath("//input[@class='search-field' " + POPUP_CONTENT_FILTER + "]");
    private static final By SEARCH_RECIPE_BUTTON = By.xpath("//input[@class='search-submit' " + POPUP_CONTENT_FILTER + "]");

    private static final String PROFILE_PICTURE_PLACEHOLDER_IMAGE_URL = "https://secure.gravatar.com/avatar/6f6be1025fd636ab694043f166a75670?s=96&d=mm&r=g";

    private final Faker faker = new Faker();
    private String firstName;
    private String lastName;

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

    public void changeNameToRandom(){
        lastName = faker.name().lastName();
        firstName = faker.name().firstName();

        WebElement LastNameField = waitAndReturnElement(LAST_NAME);
        LastNameField.clear();
        LastNameField.sendKeys(lastName);

        WebElement FirstNameField = waitAndReturnElement(FIRST_NAME);
        FirstNameField.clear();
        FirstNameField.sendKeys(firstName);

        waitAndReturnElement(SAVE_CHANGES_ON_PROFILE_BUTTON).click();
    }

    public boolean isNameChangeSuccessful() {
        return waitAndReturnElement(LAST_NAME).getAttribute("value").equals(lastName) && waitAndReturnElement(FIRST_NAME).getAttribute("value").equals(firstName);
    }

    public SearchResultPage searchingForRecipe() {
        waitAndReturnElement(SEARCH_RECIPE_INPUT).sendKeys(CONFIG.getValuesForRecipeSearching().getKey());
        waitAndReturnElement(SEARCH_RECIPE_BUTTON).click();
        return new SearchResultPage(this.driver, CONFIG.getValuesForRecipeSearching().getValue());
    }
}
