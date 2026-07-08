package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.Base;

import java.util.List;


public class Home extends Base{// локаторы, наследуемый класс от Base

    private final By paymentBlock = By.xpath("//div[@class='pay__wrapper']"); // локаторы делаем неизменяемыми
    private final By blockTitle = By.xpath(".//h2[normalize-space()='Онлайн пополнение без комиссии']");
    private final By servicesTab = By.xpath(".//span[contains(text(), 'Услуги связи')]");
    private final By phoneInput = By.xpath("//input[@placeholder='Номер телефона']");
    private final By sumInput = By.xpath("//input[@placeholder='Сумма']");
    private final By continueButton = By.xpath("//button[contains(text(), 'Продолжить')]");


    private final By serviceLink = By.xpath(".//a[contains(text(), 'Подробнее о сервисе')]"); // локатор ссылки неизменяемый

    private final By paymentIcons = By.xpath(
            ".//img[contains(@alt, 'MasterCard') or contains(@alt, 'Visa') or contains(@alt, 'Белкарт')]"
    ); // иконки платежных логотипов неизменяемые


    public Home(WebDriver driver) { //конструктор super так как
        super(driver); // вызываем конструктор родителя Base page
    }



    // Далее передем методы
    public String getBlockTitleText() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement title = block.findElement(blockTitle);
        return title.getText();
    }

    public boolean isBlockTitleDisplayed() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement title = block.findElement(blockTitle);
        return title.isDisplayed();
    }

    public boolean isBlockTitleContainsText(String text) {
        String titleText = getBlockTitleText();
        return titleText.contains(text);
    }

    // Методы для иконок
    public List<WebElement> getPaymentIcons() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        return block.findElements(paymentIcons);
    }

    public int getPaymentIconsCount() {
        return getPaymentIcons().size();
    }

    public boolean areAllIconsDisplayed() {
        List<WebElement> icons = getPaymentIcons();
        for (WebElement icon : icons) {
            if (!icon.isDisplayed()) {
                return false;
            }
        }
        return !icons.isEmpty();
    }

    // Методы для ссылки
    public void clickServiceLink() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement link = block.findElement(serviceLink);
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();
    }

    public String getServiceLinkHref() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement link = block.findElement(serviceLink);
        return link.getAttribute("href");
    }

    public String getServiceLinkText() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement link = block.findElement(serviceLink);
        return link.getText().trim();
    }

    public boolean isServiceLinkDisplayed() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement link = block.findElement(serviceLink);
        return link.isDisplayed();
    }

    //Методы для  заполнение полей оплаты
    public void selectServicesTab() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement tab = block.findElement(servicesTab);
        clickElementWithJS(tab);
    }

    public void fillPhoneNumber(String phone) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        input.clear();
        input.sendKeys(phone);
    }

    public void fillSum(String sum) {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement input = block.findElement(sumInput);
        input.clear();
        input.sendKeys(sum);
    }

    public void fillPaymentForm(String phone, String sum) {
        selectServicesTab();
        fillPhoneNumber(phone);
        fillSum(sum);
    }

    public String getPhoneValue() {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        return input.getAttribute("value");
    }

    public String getSumValue() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement input = block.findElement(sumInput);
        return input.getAttribute("value");
    }

    public void clickContinueButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
    }

    public void clickContinueButtonWithJS() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        scrollToElement(button);
        clickElementWithJS(button);
    }

    private void scrollToElement(WebElement button) {
    }

    // Комплексный метод для проверки кнопки "Продолжить"
    public void fillFormAndContinue(String phone, String sum) {
        fillPaymentForm(phone, sum);
        clickContinueButtonWithJS();
        waitForUrlContains("pay");
    }

    // Получение текущего URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Проверка, что блок отображается
    public boolean isPaymentBlockDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
