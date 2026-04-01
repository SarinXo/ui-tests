package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static utils.PageUtils.selectCheckbox;
import static utils.PageUtils.setText;

public class FormFieldsPage {
    
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
    @FindBy(id = "color1")
    private WebElement redRadio;

    @FindBy(id = "color2")
    private WebElement blueRadio;

    @FindBy(id = "color3")
    private WebElement yellowRadio;

    @FindBy(id = "color4")
    private WebElement greenRadio;

    /**
     * На сайте представлен hex код #FFC0CB
     */
    @FindBy(id = "color5")
    private WebElement pinkRadio;

    @FindBy(id = "automation")
    private WebElement automationDropdown;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//textarea")
    private WebElement messageInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public FormFieldsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FormFieldsPage enterName(String name) {
        setText(nameInput, name);
        return this;
    }

    public FormFieldsPage enterPassword(String password) {
        setText(passwordInput, password);
        return this;
    }

    public FormFieldsPage selectWater() {
        selectCheckbox(waterCheckBox);
        return this;
    }

    public FormFieldsPage selectMilk() {
        selectCheckbox(milkCheckbox);
        return this;
    }

    public FormFieldsPage selectCoffee() {
        selectCheckbox(coffeeCheckbox);
        return this;
    }

    public FormFieldsPage selectWine() {
        selectCheckbox(wineCheckbox);
        return this;
    }

    public FormFieldsPage selectCtrlAltDelight() {
        selectCheckbox(ctrlAltDelightCheckBox);
        return this;
    }

    public FormFieldsPage selectColor(Color color) {
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
        new Select(automationDropdown).selectByVisibleText(option.getValue());
        return this;
    }

    public FormFieldsPage enterEmail(String email) {
        setText(emailInput, email);
        return this;
    }

    public FormFieldsPage enterMessage(String message) {
        setText(messageInput, message);
        return this;
    }

    public FormFieldsPage submit() {
        submitButton.click();
        return this;
    }

}
