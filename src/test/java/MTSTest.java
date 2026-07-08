package org.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class MTSTest {
    private WebDriver driver;
    private Home homePage;

    @BeforeAll
    public static void installWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void openDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mts.by");
        homePage = new Home(driver);
        homePage.acceptCookieBanner(); // Используем ваш метод закрытия куки
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка надписей в незаполненных полях каждого варианта оплаты")
    public void testPlaceholdersInAllTabs() {
        // 1. Услуги связи
        homePage.selectServicesTab();
        assertEquals("Номер телефона", homePage.getPhonePlaceholder(), "Неверный плейсхолдер телефона");
        assertEquals("Сумма", homePage.getSumPlaceholder(), "Неверный плейсхолдер суммы");

        // 2. Домашний интернет
        homePage.selectInternetTab();
        assertEquals("Номер абонента", homePage.getInternetAccountPlaceholder(), "Неверный плейсхолдер аккаунта интернета");
        assertEquals("Сумма", homePage.getSumPlaceholder(), "Неверный плейсхолдер суммы интернета");

        // 3. Рассрочка
        homePage.selectInstallmentTab();
        assertEquals("Номер счета", homePage.getInstallmentAccountPlaceholder(), "Неверный плейсхолдер счета рассрочки");
        assertEquals("Сумма", homePage.getSumPlaceholder(), "Неверный плейсхолдер суммы рассрочки");

        // 4. Задолженность
        homePage.selectDebtTab();
        assertEquals("Номер счета", homePage.getDebtAccountPlaceholder(), "Неверный плейсхолдер счета задолженности");
        assertEquals("Сумма", homePage.getSumPlaceholder(), "Неверный плейсхолдер суммы задолженности");
    }

    @Test
    @DisplayName("Проверка окна оплаты bePaid: суммы, номера телефона, полей карты и иконок")
    public void testPaymentIframeValidation() {
        String phoneNumber = "297777777";
        String amount = "10";

        // Заполняем форму услуг связи и нажимаем продолжить
        homePage.fillPaymentForm(phoneNumber, amount);
        homePage.clickContinueButtonWithJS();

        // Переключаем контекст Selenium внутрь фрейма оплаты bePaid
        homePage.switchToPaymentIframe();

        // 1. Проверяем корректность суммы и номера телефона во фрейме
        String iframeSumText = homePage.getIframeSumText();
        String iframeInfoText = homePage.getIframeOrderInfoText();
        assertTrue(iframeSumText.contains(amount), "Сумма во фрейме указана некорректно");
        assertTrue(iframeInfoText.contains(phoneNumber), "Номер телефона во фрейме отсутствует или указан неверно");

        // 2. Проверяем текст и сумму на кнопке подтверждения платежа
        String buttonText = homePage.getIframeButtonText();
        assertTrue(buttonText.contains("Оплатить") && buttonText.contains(amount),
                "На кнопке подтверждения отсутствует текст 'Оплатить' или верная сумма");

        // 3. Проверяем надписи в незаполненных полях ввода реквизитов карты
        assertEquals("Номер карты", homePage.getCardNumberPlaceholder(), "Неверный плейсхолдер номера карты");
        assertEquals("ММ / ГГ", homePage.getCardExpPlaceholder(), "Неверный плейсхолдер срока действия");
        assertEquals("CVC", homePage.getCardCvcPlaceholder(), "Неверный плейсхолдер поля CVC");
        assertEquals("Имя держателя карты", homePage.getCardHolderPlaceholder(), "Неверный плейсхолдер владельца");

        // 4. Проверяем наличие логотипов/иконок платежных систем
        assertTrue(homePage.getIframeIconsCount() > 0, "Иконки платежных систем внутри фрейма не обнаружены");

        // Возвращаем драйвер на главную страницу (good practice)
        homePage.switchToMainContent();
    }
}

