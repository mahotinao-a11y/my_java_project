package org.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.PaymentPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MTSTest {
    private WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeAll
    public static void installWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void openDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");

        // Инициализируем страницы
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);

        // Закрываем куки
        homePage.acceptCookieBanner();
        System.out.println("Браузер открыт, куки приняты");
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Браузер закрыт");
        }
    }

    @Test
    @DisplayName("Проверка названия браузера")
    public void testPageTitle() {
        String expected = "МТС – мобильный оператор в Беларуси";
        String actual = homePage.getPageTitle();
        assertEquals(expected, actual, "Заголовок страницы не совпадает");
        System.out.println("Заголовок страницы: " + actual);
    }

    @Test
    @DisplayName("Проверка названия блока - Онлайн оплата без комиссии")
    public void testBlockTitle() {
        // Проверяем, что блок отображается
        assertTrue(homePage.isPaymentBlockDisplayed(), "Блок оплаты должен отображаться");

        // Проверяем заголовок
        assertTrue(homePage.isBlockTitleDisplayed(), "Заголовок должен быть видимым");
        assertTrue(homePage.isBlockTitleContainsText("Онлайн пополнение"),
                "Заголовок должен содержать 'Онлайн пополнение'");
        assertTrue(homePage.isBlockTitleContainsText("без комиссии"),
                "Заголовок должен содержать 'без комиссии'");

        System.out.println("Заголовок блока: " + homePage.getBlockTitleText());
    }

    @Test
    @DisplayName("Проверка логотипов платежных систем")
    public void testPaymentIcons() {
        // Проверяем, что иконки есть
        int iconsCount = homePage.getPaymentIconsCount();
        assertTrue(iconsCount > 0, "Должна быть хотя бы одна иконка платежной системы");

        // Проверяем, что все иконки видимы
        assertTrue(homePage.areAllIconsDisplayed(), "Все иконки должны быть видимыми");

        // Выводим все иконки
        homePage.getPaymentIcons().forEach(icon ->
                System.out.println("Иконка: " + icon.getAttribute("alt"))
        );

        System.out.println("Найдено иконок: " + iconsCount);
    }

    @Test
    @DisplayName("Проверка работы ссылки «Подробнее о сервисе»")
    public void testServiceLink() {
        // Проверяем, что ссылка видима
        assertTrue(homePage.isServiceLinkDisplayed(), "Ссылка должна быть видимой");

        // Проверяем текст ссылки
        String linkText = homePage.getServiceLinkText();
        assertEquals("Подробнее о сервисе", linkText, "Текст ссылки не совпадает");

        // Проверяем href
        String href = homePage.getServiceLinkHref();
        assertNotNull(href, "Href не должен быть null");
        assertFalse(href.isEmpty(), "Href не должен быть пустым");
        assertTrue(href.contains("/help/"), "Ссылка должна вести на страницу помощи");
        System.out.println("Ссылка: " + href);

        // Кликаем и проверяем переход
        String originalUrl = homePage.getCurrentUrl();
        homePage.clickServiceLink();

        // Ждем изменения URL
        homePage.waitForUrlContains("/help/");
        String newUrl = homePage.getCurrentUrl();
        assertNotEquals(originalUrl, newUrl, "URL должен измениться");
        System.out.println("Переход выполнен на: " + newUrl);
    }

    @Test
    @DisplayName("Заполнение полей и проверка кнопки «Продолжить» (Услуги связи)")
    public void testContinueButton() {
        // Заполняем форму
        homePage.fillPaymentForm("297777777", "10");

        // Проверяем, что поля заполнились
        String phoneValue = homePage.getPhoneValue();
        String sumValue = homePage.getSumValue();
        assertEquals("(29)777-77-77", phoneValue, "Номер телефона введен неверно");
        assertEquals("10", sumValue, "Сумма введена неверно");

        // Нажимаем кнопку
        homePage.clickContinueButtonWithJS();

        // Проверяем переход
        homePage.waitForUrlContains("pay");
        System.out.println("Переход выполнен на: " + homePage.getCurrentUrl());

        // Проверяем страницу оплаты
        assertTrue(paymentPage.isPaymentPageLoaded(), "Страница оплаты должна загрузиться");
        assertTrue(paymentPage.isSumDisplayed("10"), "Сумма 10 должна отображаться");
        System.out.println("Тест выполнен успешно!");
    }
}

