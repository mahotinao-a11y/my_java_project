package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.awt.SystemColor.text;

public class Base { // создаем класс с полями
    protected WebDriver driver;    // поле дврайвера
    protected WebDriverWait wait;  // поле ожидание для драйвера

    public Base(WebDriver driver) { // констурктор
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // тут указываем методы, который общие для всех тестов
    public void clickElement(By locator) {// клик по локатору
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click(); // в этом методе ищем локатор, ждем его видимости и кликаем на него
    }

    public void clickElement(WebElement element) {// клик по найденому элементу
        wait.until(ExpectedConditions.elementToBeClickable(element)).click(); // в этом методе мы кликаем уже по найденому элементу
    }

    public void clickElementWithJS(WebElement element) { // метод в котором кликает по перекрытому элементу
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void sendKeys(By locator, String text) { // метод , который наполняет поля текстом
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // находим поле , ждем его видимости
        element.clear(); // щчищаем старый данные
        element.sendKeys(text); // заполняем поля
    }

    public String getText(By locator) { // метод для получения теска
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText(); // вернет вводимый текст
    }

    public boolean isDisplayed(By locator) { // метод , который проверяет видимость элемента в момент действия
        try { // блок вернет true  если элемент виден
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) { // вернет если элемент не виден
            return false;
        }
    }

    public void waitForVisibility(By locator) { // метод, который гарантирует видимсоть элемента после ожидания
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForUrlContains(String urlPart) { // метод проверяет url
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public void acceptCookieBanner() { // vtnjl [frhsdftn кукки, которые перекрывают страницу
        try {
            WebElement acceptButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='cookie-agree']"))
            );
            acceptButton.click();
            System.out.println("🍪 Куки-баннер принят");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'cookie')]")
            ));
        } catch (Exception e) {
            System.out.println("⚠️ Куки-баннер не найден или уже принят");
        }
    }
}