package utils;

import org.openqa.selenium.WebElement;

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

    public static void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
