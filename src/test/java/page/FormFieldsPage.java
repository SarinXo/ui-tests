package page;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static utils.PageUtils.scrollToElement;
import static utils.PageUtils.selectCheckbox;
import static utils.PageUtils.setText;

@Slf4j
public class FormFieldsPage implements AutoCloseable {

    @Getter
    private final WebDriver driver;

    /*
     * Область регистрации
     */
    @FindBy(id = "name-input")
    private WebElement nameInput;
    
    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    /*
     * Область Favorite drink
     */
    @FindBy(id = "drink1")
    private WebElement waterCheckBox;

    @FindBy(id = "drink2")
    private WebElement milkCheckbox;

    @FindBy(id = "drink3")
    private WebElement coffeeCheckbox;

    @FindBy(id = "drink4")
    private WebElement wineCheckbox;

    @FindBy(id = "drink5")
    private WebElement ctrlAltDelightCheckBox;

    /*
     * Область Favorite color
     */
    @FindBy(css = "#color1")
    private WebElement redRadio;

    @FindBy(css = "#color2")
    private WebElement blueRadio;

    @FindBy(css = "#color3")
    private WebElement yellowRadio;

    @FindBy(css = "#color4")
    private WebElement greenRadio;

    /**
     * На сайте представлен hex код #FFC0CB
     */
    @FindBy(css = "#color5")
    private WebElement pinkRadio;

    @FindBy(id = "automation")
    private WebElement automationDropdown;

    @FindBy(xpath = "//label[text()='Automation tools']/following-sibling::ul/li")
    private List<WebElement> automationTools;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//textarea")
    private WebElement messageInput;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    public FormFieldsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void close() {
        try {
            driver.close();
        } catch (Exception e) {
            log.info("Ошибка при закрытии драйвера браузера");
        }
    }

    public FormFieldsPage enterName(String name) {
        scrollToElement(driver, nameInput);
        setText(nameInput, name);
        return this;
    }

    public FormFieldsPage enterPassword(String password) {
        scrollToElement(driver, passwordInput);
        setText(passwordInput, password);
        return this;
    }

    public FormFieldsPage selectWater() {
        scrollToElement(driver, waterCheckBox);
        selectCheckbox(waterCheckBox);
        return this;
    }

    public FormFieldsPage selectMilk() {
        scrollToElement(driver, milkCheckbox);
        selectCheckbox(milkCheckbox);
        return this;
    }

    public FormFieldsPage selectCoffee() {
        scrollToElement(driver, coffeeCheckbox);
        selectCheckbox(coffeeCheckbox);
        return this;
    }

    public FormFieldsPage selectWine() {
        scrollToElement(driver, wineCheckbox);
        selectCheckbox(wineCheckbox);
        return this;
    }

    public FormFieldsPage selectCtrlAltDelight() {
        scrollToElement(driver, ctrlAltDelightCheckBox);
        selectCheckbox(ctrlAltDelightCheckBox);
        return this;
    }

    public FormFieldsPage selectColor(Color color) {
        scrollToElement(driver, redRadio); //считаем, что цвета расположены вместе
        switch (color) {
            case RED -> redRadio.click();
            case BLUE -> blueRadio.click();
            case YELLOW -> yellowRadio.click();
            case GREEN -> greenRadio.click();
            case PINK -> pinkRadio.click();
            default -> throw new IllegalArgumentException("Неописанный цвет");
        }
        return this;
    }

    public FormFieldsPage selectAutomation(AutomationOption option) {
        scrollToElement(driver, automationDropdown);
        new Select(automationDropdown).selectByVisibleText(option.getValue());
        return this;
    }

    public List<String> getAutomationTools() {
        return automationTools.stream()
                .map(WebElement::getText)
                .toList();
    }

    public FormFieldsPage enterEmail(String email) {
        scrollToElement(driver, emailInput);
        setText(emailInput, email);
        return this;
    }

    public FormFieldsPage enterMessage(String message) {
        scrollToElement(driver, messageInput);
        setText(messageInput, message);
        return this;
    }

    public FormFieldsPage submit() {
        scrollToElement(driver, submitButton);
        submitButton.click();
        return this;
    }

}
