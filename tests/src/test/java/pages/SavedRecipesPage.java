package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SavedRecipesPage extends Page{
    public SavedRecipesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRecipeSaved(String recipeTitle) {
        return waitAndReturnElement(By.xpath("//a[@title='" + recipeTitle + "' and @class='article-link']")).isDisplayed();
    }
}
