package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends Page{
    private final By DESIRED_RECIPE_BUTTON;

    public SearchResultPage(WebDriver driver, String desiredRecipe) {
        super(driver);
        DESIRED_RECIPE_BUTTON = By.xpath("//a[@title='" + desiredRecipe + "' and @class='article-link']");
    }

    public RecipePage navigateToRecipePage() {
        waitAndReturnElement(DESIRED_RECIPE_BUTTON).click();
        return new RecipePage(this.driver);
    }
}
