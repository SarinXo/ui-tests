package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AutomationOption;
import page.Color;
import page.FormFieldsPage;
import utils.PropertyReader;

import java.time.Duration;
import java.util.Comparator;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.TestUtils.attachScreenshot;

public class FormFieldsTest {

    private final PropertyReader properties = new PropertyReader();
    private FormFieldsPage form;

    @BeforeEach
    public void openBrowser() {
        String url = properties.get("application.forms.form-fields.url", String.class);

        var driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);

        form = new FormFieldsPage(driver);
    }

    @AfterEach
    void tearDown(TestInfo info) {
        attachScreenshot(form.getDriver(), "Screenshot - " + info.getDisplayName());
        form.close();
    }

    @Test
    @DisplayName("Часть 1. Автоматизация. Работа с полями и формами")
    void whenFillForm_thenNotificationOccurred() {
        //given
        String toolsCount = String.valueOf(form.getAutomationTools().size());
        String longestTool = form.getAutomationTools().stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new RuntimeException("Не найдены инструменты автоматизации"));

        String message = toolsCount + " " + longestTool;

        //when
        form.enterName("admin")
                .enterPassword("admin")
                .selectMilk()
                .selectCoffee()
                .selectColor(Color.YELLOW)
                .selectAutomation(AutomationOption.YES)
                .enterEmail("name@example.com")
                .enterMessage(message)
                .submit();

        //then
        Alert alert = form.getDriver().switchTo().alert();
        String alertText = alert.getText();

        assertEquals("Message received!", alertText);

        alert.accept();
    }

    @Test
    @DisplayName("Позитивный: отправка формы с заполненным полем Name")
    void whenFillOnlyName_thenAlertAppears() {
        // when
        form.enterName("admin").submit();

        // then
        Alert alert = form.getDriver().switchTo().alert();
        String alertText = alert.getText();

        assertEquals("Message received!", alertText);

        alert.accept();
    }

    @Test
    @DisplayName("Негативный: отправка формы с БЕЗ заполненного поля Name")
    void whenSubmitEmptyForm_thenNoAlert() {
        // when
        form.submit();

        // then
        assertThrows(TimeoutException.class,
                () -> new WebDriverWait(form.getDriver(), Duration.ofSeconds(1))
                        .until(ExpectedConditions.alertIsPresent()));
    }

}
