package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SavedRecipesPage extends Page{
    private final static String REMOVE_RECIPE_FROM_SAVED_BUTTON_TEMPLATE =
            "//div[contains(@class, 'sk-bookmark') and preceding-sibling::a[@title='%s']]";

    public SavedRecipesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRecipeSaved(String recipeTitle) {
        return this.getBodyText().contains(recipeTitle);
    }

    public void removeRecipeFromSaved(String recipeTitle) {
        By removeRecipeFromSavedButton = By.xpath(String.format(REMOVE_RECIPE_FROM_SAVED_BUTTON_TEMPLATE, recipeTitle));
        waitAndReturnElement(removeRecipeFromSavedButton).click();
    }
}
