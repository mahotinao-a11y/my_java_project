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
    private final By serviceLink = By.xpath(".//a[contains(text(), 'Подробнее о сервисе')]");
    private final By paymentIcons = By.xpath(
            ".//img[contains(@alt, 'MasterCard') or contains(@alt, 'Visa') or contains(@alt, 'Белкарт')]"
    );

    // добавленные локаторы
    private final By internetTab = By.xpath("//span[@class='select__now']");
    private final By installmentTab = By.xpath("//input[@data-mask='account-num-instalment']");

    // локаторы для полей playsholder
    private final By phonePlaceholder = By.xpath("//input[@placeholder='Номер телефона']");
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


    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
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
        // 1. Ищем элемент вкладки по улучшенному XPath, который учитывает возможные обертки
        By debtTabLocator = By.xpath(
                "//button[contains(., 'Задолженность')] | " +
                        "//div[contains(@class, 'tab') and contains(., 'Задолженность')] | " +
                        "//*[contains(text(), 'Задолженность')]"
        );

        // 2. Ждем, пока элемент станет полностью кликабельным
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(debtTabLocator));

        // 3. Скроллим к нему по центру экрана
        scrollToElement(tab);

        // 4. Кликаем стандартным методом. Если не сработает, то JS-кликом ниже
        try {
            tab.click();
        } catch (Exception e) {
            clickElementWithJS(tab);
        }
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

            // ищем "Номер телефона"
            WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Номер телефона']")
            ));

            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Сумма']")
            ));

            String phonePlaceholderText = phone.getAttribute("placeholder");
            String sumPlaceholderText = sum.getAttribute("placeholder");

            System.out.println("Вкладка 'Домашний интернет'");
            System.out.println("Поле 1: '" + phonePlaceholderText + "'");
            System.out.println("Поле 2: '" + sumPlaceholderText + "'");

            // Проверяем, что в поле 1 "Номер телефона"
            return "Номер телефона".equals(phonePlaceholderText) &&
                    "Сумма".equals(sumPlaceholderText);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return false;
        }
    }

    public boolean areInstallmentPlaceholdersCorrect() {
        try {
            selectInstallmentTab();

            System.out.println("Вкладка 'Рассрочка'");

            WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("score-instalment")
            ));
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("instalment-sum")
            ));

            String accountText = account.getAttribute("placeholder");
            String sumText = sum.getAttribute("placeholder");

            System.out.println("Поле счета: '" + accountText + "'");
            System.out.println("Поле суммы: '" + sumText + "'");

            return "Номер счета на 44".equals(accountText) &&
                    "Сумма".equals(sumText);
        } catch (Exception e) {
            System.out.println("Ошибка в 'Рассрочка': " + e.getMessage());
            return false;
        }
    }
    public boolean areDebtPlaceholdersCorrect() {
        try {
            selectDebtTab();

            System.out.println("Вкладка 'Задолженность'");
            Thread.sleep(1000);

            // Ожидаем физического появления полей ввода на экране
            WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("score-arrears")));
            WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("arrears-sum")));

            String accountText = account.getAttribute("placeholder");
            String sumText = sum.getAttribute("placeholder");;

            System.out.println("Поле счета: '" + accountText + "'");
            System.out.println("Поле суммы: '" + sumText + "'");

            return "Номер счета на 2073".equals(accountText) && "Сумма".equals(sumText);
        } catch (Exception e) {
            System.out.println("Ошибка в 'Задолженность': " + e.getMessage());
            return false;
        }
    }



}