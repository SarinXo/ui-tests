package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class PageUtils {

    public static String getPageUrl() {
        PropertyReader propertyReader = new PropertyReader();
        return propertyReader.get("application.forms.form-fields.url", String.class);
    }

    public static void selectCheckbox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public static void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
