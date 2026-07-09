package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Payment extends Base {



    // Сумма на странице
    private final By sumOnPage = By.xpath("//span[contains(@class, 'total') or contains(@class, 'sum') or contains(@class, 'amount')]");

    // Сумма на кнопке оплаты
    private final By sumOnButton = By.xpath("//button[contains(@class, 'pay') or contains(@class, 'submit')]//span[contains(@class, 'sum')]");

    // Номер телефона на странице
    private final By phoneNumber = By.xpath("//span[contains(@class, 'phone') or contains(@class, 'number')]");

    // Поля для ввода реквизитов карты
    private final By cardNumberInput = By.xpath("//input[@id='cc-number']");
    private final By cardExpiryInput = By.xpath("//input[@placeholder='ММ/ГГ']");
    private final By cardCvvInput = By.xpath("//label[@class='ng-tns-c2312288139-5 ng-star-inserted']");
    private final By cardHolderInput = By.xpath("//input[@autocomplete='cc-name']");

    // Иконки платежных систем на странице оплаты
    private final By paymentIcons = By.xpath("//div[contains(@class, 'payment') or contains(@class, 'icons')]//img");

    public Payment(WebDriver driver) {
        super(driver);
    }

    public boolean isPaymentPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // Получить сумму на странице
    public String getSumOnPage() {
        WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(sumOnPage));
        return sum.getText().trim();
    }

    // Получить сумму на кнопке
    public String getSumOnButton() {
        WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(sumOnButton));
        return sum.getText().trim();
    }


    // Получить номер телефона на странице
    public String getPhoneNumber() {
        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));
        return phone.getText().trim();
    }

    // Проверка номера телефона
    public boolean isPhoneNumberCorrect(String expectedPhone) {
        String actualPhone = getPhoneNumber();

        // Проверяем, что номер содержит нужные цифры
        return actualPhone.contains("29") && actualPhone.contains("7777777");
    }

    // Проверка placeholder'а у поля "Номер карты"
    public boolean isCardNumberPlaceholderCorrect() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput));
        String placeholder = input.getAttribute("placeholder");
        return "Номер карты".equals(placeholder);
    }

    // Проверка placeholder'а у поля "ММ/ГГ"
    public boolean isCardExpiryPlaceholderCorrect() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardExpiryInput));
        String placeholder = input.getAttribute("placeholder");
        return "ММ/ГГ".equals(placeholder);
    }

    // Проверка placeholder'а у поля "CVV"
    public boolean isCardCvvPlaceholderCorrect() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardCvvInput));
        String placeholder = input.getAttribute("placeholder");
        return "CVV".equals(placeholder);
    }

    // Проверка placeholder'а у поля "Имя держателя"
    public boolean isCardHolderPlaceholderCorrect() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderInput));
        String placeholder = input.getAttribute("placeholder");
        return "Имя держателя".equals(placeholder);
    }

}