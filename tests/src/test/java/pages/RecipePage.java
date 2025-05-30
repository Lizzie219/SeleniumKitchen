package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecipePage extends Page{
    private static final By ADD_RECIPE_TO_SAVED_RECIPES_BUTTON = By.xpath("");
    private static final By SAVED_RECIPES_BUTTON = By.xpath("//a[@href='/receptjeim' and not ( @class)]");

    public RecipePage(WebDriver driver) {
        super(driver);
    }

    public void addRecipeToSavedRecipes() {
        waitAndReturnElement(ADD_RECIPE_TO_SAVED_RECIPES_BUTTON).click();
    }

    public SavedRecipesPage navigateToSavedRecipes() {
        waitAndReturnElement(SAVED_RECIPES_BUTTON).click();
        return new SavedRecipesPage(this.driver);
    }
}
