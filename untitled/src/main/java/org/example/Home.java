package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.Base;

import java.util.List;


public class Home extends Base { // локаторы, наследуемый класс от Base

    // Локаторы
    private final By paymentBlock = By.xpath("//div[@class='pay__wrapper']");
    private final By blockTitle = By.xpath(".//h2[normalize-space()='Онлайн пополнение без комиссии']");
    private final By servicesTab = By.xpath(".//span[contains(text(), 'Услуги связи')]");
    private final By phoneInput = By.xpath("//input[@placeholder='Номер телефона']");
    private final By sumInput = By.xpath("//input[@placeholder='Сумма']");
    private final By continueButton = By.xpath("//button[contains(text(), 'Продолжить')]");
    private final By serviceLink = By.xpath(".//a[contains(text(), 'Подробнее о сервисе')]");
    private final By paymentIcons = By.xpath(
            ".//img[contains(@alt, 'MasterCard') or contains(@alt, 'Visa') or contains(@alt, 'Белкарт')]"
    );

    // добавленные локаторы
    private final By internetTab = By.xpath(".//span[contains(text(), 'Домашний интернет')]");
    private final By installmentTab = By.xpath(".//span[contains(text(), 'Рассрочка')]");
    private final By debtTab = By.xpath(".//span[contains(text(), 'Задолженность')]");

    // локаторы для полей playsholder
    private final By phonePlaceholder = By.xpath("//input[@placeholder='Номер телефона']");
    private final By accountPlaceholder = By.xpath("//input[@placeholder='Лицевой счет']");
    private final By contractPlaceholder = By.xpath("//input[@placeholder='Номер договора']");
    private final By sumPlaceholder = By.xpath("//input[@placeholder='Сумма']");

    public Home(WebDriver driver) {
        super(driver);
    }


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


    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void clickContinueButtonWithJS() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        scrollToElement(button);
        clickElementWithJS(button);
    }

    public void fillFormAndContinue(String phone, String sum) {
        fillPaymentForm(phone, sum);
        clickContinueButtonWithJS();
        waitForUrlContains("pay");
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
    public boolean isPaymentBlockDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getCurrentUrl() {
            return driver.getCurrentUrl();
        }



    // Выбор вкладок
    public void selectInternetTab() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement tab = block.findElement(internetTab);
        clickElementWithJS(tab);
    }

    public void selectInstallmentTab() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement tab = block.findElement(installmentTab);
        clickElementWithJS(tab);
    }

    public void selectDebtTab() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBlock));
        WebElement tab = block.findElement(debtTab);
        clickElementWithJS(tab);
    }

    // Проверка placeholder для каждой вкладки
    public boolean areServicesPlaceholdersCorrect() {
        try {
            WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phonePlaceholder));
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(sumPlaceholder));

            String phonePlaceholderText = phone.getAttribute("placeholder");
            String sumPlaceholderText = sum.getAttribute("placeholder");

            return "Номер телефона".equals(phonePlaceholderText) &&
                    "Сумма".equals(sumPlaceholderText);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areInternetPlaceholdersCorrect() {
        try {
            selectInternetTab(); // Переключаемся на вкладку Интернет
            WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(accountPlaceholder));
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(sumPlaceholder));

            String accountPlaceholderText = account.getAttribute("placeholder");
            String sumPlaceholderText = sum.getAttribute("placeholder");

            return "Лицевой счет".equals(accountPlaceholderText) &&
                    "Сумма".equals(sumPlaceholderText);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areInstallmentPlaceholdersCorrect() {
        try {
            selectInstallmentTab(); // Переключаемся на вкладку Рассрочка
            WebElement contract = wait.until(ExpectedConditions.visibilityOfElementLocated(contractPlaceholder));
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(sumPlaceholder));

            String contractPlaceholderText = contract.getAttribute("placeholder");
            String sumPlaceholderText = sum.getAttribute("placeholder");

            return "Номер договора".equals(contractPlaceholderText) &&
                    "Сумма".equals(sumPlaceholderText);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areDebtPlaceholdersCorrect() {
        try {
            selectDebtTab(); // Переключаемся на вкладку Задолженность
            WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phonePlaceholder));
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(sumPlaceholder));

            String phonePlaceholderText = phone.getAttribute("placeholder");
            String sumPlaceholderText = sum.getAttribute("placeholder");

            return "Номер телефона".equals(phonePlaceholderText) &&
                    "Сумма".equals(sumPlaceholderText);
        } catch (Exception e) {
            return false;
        }
    }


}