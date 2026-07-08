package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class Home extends Base { // Наследуемся от вашего Base класса

    // ==========================================
    // ВАШИ СТАРЫЕ ЛОКАТОРЫ (БЕЗ ИЗМЕНЕНИЙ)
    // ==========================================
    private final By paymentBlock = By.xpath("//div[@class='pay__wrapper']");
    private final By blockTitle = By.xpath(".//h2[normalize-space()='Онлайн пополнение без комиссии']");
    private final By servicesTab = By.xpath(".//span[contains(text(), 'Услуги связи')]");
    private final By phoneInput = By.xpath("//input[@placeholder='Номер телефона']");
    private final By sumInput = By.xpath("//input[@placeholder='Сумма']");
    private final By continueButton = By.xpath("//button[contains(text(), 'Продолжить')]");
    private final By serviceLink = By.xpath(".//a[contains(text(), 'Подробнее о сервисе')]");
    private final By paymentIcons = By.xpath(".//img[contains(@alt, 'MasterCard') or contains(@alt, 'Visa') or contains(@alt, 'Белкарт')]");

    // ==========================================
    // ДОБАВЛЕНО: НОВЫЕ ЛОКАТОРЫ ДЛЯ ЗАДАНИЯ
    // ==========================================
    private final By internetTab = By.xpath(".//span[contains(text(), 'Домашний интернет')]");
    private final By installmentTab = By.xpath(".//span[contains(text(), 'Рассрочка')]");
    private final By debtTab = By.xpath(".//span[contains(text(), 'Задолженность')]");

    // Поля ввода для других вкладок (поиск по уникальным placeholder)
    private final By internetAccountInput = By.xpath("//input[@placeholder='Номер абонента']");
    private final By installmentAccountInput = By.xpath("//input[@placeholder='Номер счета']");
    private final By debtAccountInput = By.xpath("//input[@placeholder='Номер счета']");

    // Элементы внутри iframe оплаты bePaid
    private final By paymentIframe = By.cssSelector("iframe.bepaid-iframe, iframe[src*='checkout']");
    private final By iframeOrderSum = By.className("pay-description__cost");
    private final By iframeOrderInfo = By.className("pay-description__text");
    private final By iframeSubmitButton = By.xpath("//button[@type='submit']");

    // Реквизиты карты внутри iframe
    private final By iframeCardNumber = By.xpath("//input[@id='cc-number'] | //label[contains(text(),'Номер карты')]/following-sibling::input");
    private final By iframeCardExp = By.xpath("//input[@id='cc-exp'] | //label[contains(text(),'Срок действия')]/following-sibling::input");
    private final By iframeCardCvc = By.xpath("//input[@id='cc-csc'] | //label[contains(text(),'CVC')]/following-sibling::input");
    private final By iframeCardHolder = By.xpath("//input[@id='cc-name'] | //label[contains(text(),'Имя держателя')]/following-sibling::input");
    private final By iframeIcons = By.xpath("//div[contains(@class,'cards-icons')]//img | //div[@class='pay-systems']//i");

    public Home(WebDriver driver) {
        super(driver); // Вызываем конструктор родителя Base
    }

    // ==========================================
    // ВАШИ СТАРЫЕ МЕТОДЫ (БЕЗ ИЗМЕНЕНИЙ)
    // ==========================================
    public String getBlockTitleText() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        return block.findElement(blockTitle).getText();
    }

    public boolean isBlockTitleDisplayed() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        return block.findElement(blockTitle).isDisplayed();
    }

    public boolean isBlockTitleContainsText(String text) {
        return getBlockTitleText().contains(text);
    }

    public List<WebElement> getPaymentIcons() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        return block.findElements(paymentIcons);
    }

    public int getPaymentIconsCount() { return getPaymentIcons().size(); }

    public boolean areAllIconsDisplayed() {
        List<WebElement> icons = getPaymentIcons();
        for (WebElement icon : icons) {
            if (!icon.isDisplayed()) return false;
        }
        return !icons.isEmpty();
    }

    public void clickServiceLink() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        block.findElement(serviceLink).click();
    }

    public String getServiceLinkHref() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock)).findElement(serviceLink).getAttribute("href");
    }

    public String getServiceLinkText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock)).findElement(serviceLink).getText().trim();
    }

    public boolean isServiceLinkDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock)).findElement(serviceLink).isDisplayed();
    }

    public void selectServicesTab() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        clickElementWithJS(block.findElement(servicesTab));
    }

    public void fillPhoneNumber(String phone) { sendKeys(phoneInput, phone); }
    public void fillSum(String sum) { sendKeys(sumInput, sum); }

    public void fillPaymentForm(String phone, String sum) {
        selectServicesTab();
        fillPhoneNumber(phone);
        fillSum(sum);
    }

    public String getPhoneValue() { return getPhoneValue(); }
    public String getSumValue() { return getSumValue(); }
    public void clickContinueButton() { clickElement(continueButton); }

    public void clickContinueButtonWithJS() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        clickElementWithJS(button);
    }

    public String getCurrentUrl() { return driver.getCurrentUrl(); }
    public boolean isPaymentBlockDisplayed() { return isDisplayed(paymentBlock); }
    public String getPageTitle() { return driver.getTitle(); }

    // ==========================================
    // ДОБАВЛЕНО: НОВЫЕ МЕТОДЫ ДЛЯ ЗАДАНИЯ
    // ==========================================

    // Переключение вкладок оплаты
    public void selectInternetTab() { clickElementWithJS(wait.until(ExpectedConditions.presenceOfElementLocated(internetTab))); }
    public void selectInstallmentTab() { clickElementWithJS(wait.until(ExpectedConditions.presenceOfElementLocated(installmentTab))); }
    public void selectDebtTab() { clickElementWithJS(wait.until(ExpectedConditions.presenceOfElementLocated(debtTab))); }

    // Извлечение надписей (placeholder) пустых полей на главной
    public String getPhonePlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(phoneInput)).getAttribute("placeholder"); }
    public String getSumPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(sumInput)).getAttribute("placeholder"); }
    public String getInternetAccountPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(internetAccountInput)).getAttribute("placeholder"); }
    public String getInstallmentAccountPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(installmentAccountInput)).getAttribute("placeholder"); }
    public String getDebtAccountPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(debtAccountInput)).getAttribute("placeholder"); }

    // Управление контекстом iframe оплаты bePaid
    public void switchToPaymentIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
    }

    public void switchToMainContent() {
        driver.switchTo().defaultContent();
    }

    // Чтение данных внутри iframe оплаты bePaid
    public String getIframeSumText() { return getText(iframeOrderSum); }
    public String getIframeOrderInfoText() { return getText(iframeOrderInfo); }
    public String getIframeButtonText() { return getText(iframeSubmitButton); }

    // Плейсхолдеры полей ввода карты внутри iframe
    public String getCardNumberPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(iframeCardNumber)).getAttribute("placeholder"); }
    public String getCardExpPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(iframeCardExp)).getAttribute("placeholder"); }
    public String getCardCvcPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(iframeCardCvc)).getAttribute("placeholder"); }
    public String getCardHolderPlaceholder() { return wait.until(ExpectedConditions.presenceOfElementLocated(iframeCardHolder)).getAttribute("placeholder"); }

    public int getIframeIconsCount() {
        waitForVisibility(iframeIcons);
        return driver.findElements(iframeIcons).size();
    }
}
