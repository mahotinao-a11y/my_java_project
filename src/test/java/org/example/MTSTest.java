import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MTSTest {
    private WebDriver driver; // объявляем переменную driver, чтобы использовать ее далее
    private WebDriverWait wait; // объявляем переменную для ожидания


    @BeforeAll // запуск один раз перед выполнением всех тестов
    public static void installWebDriver() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach // перед каждым тестом открывать драйвер
    public void openDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        closeCookieBanner();
    }

    @AfterEach // после каждого теста закрытие driver
    public void closeDriver() {
        if (driver != null) {  // проверка , что driver  вообще создался
            driver.quit();   // закрытие всех окон
        }
    }

    private void closeCookieBanner() {
        try {
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'cookie__wrapper')]//button")
            ));
            closeButton.click();
        } catch (Exception e) {
            System.out.println("⚠️ Куки-баннер не найден");
        }
    }

    @Test
    @DisplayName("Проверка названия браузера")
    public void NameDriver() {
        System.out.println(driver.getTitle());  // выведет в консоль заголовок сайта
        String expected = "МТС – мобильный оператор в Беларуси"; // заголовок , который мы ожидаем, если поставить другое название тест упадет
        String actual = driver.getTitle(); // фактический заголовок на сайте
        assertEquals(expected, actual); // сравнение результата оидания и фактичесного
    }

    @Test
    @DisplayName("Проверка названия блока-Онлайн оплата без комиссии")
    public void NameBlock() {
        By blockLocator = By.xpath("//div[@class='pay__wrapper']");// находим блок онлайн оплата
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(blockLocator)); // проверям что блок видимый
        WebElement title = block.findElement(By.xpath(".//h2[normalize-space()='Онлайн пополнение без комиссии']")); // ищем title в указанном выше блоке
        /*WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath(".//h2[normalize-space()='Онлайн пополнение без комиссии']")
    )); это ожидание появления заголовка*/
        assertTrue(title.isDisplayed()); // проверка что заголовок виден в блоке
        System.out.println(title.getText()); // вывод в консоли заголовка
        String expected = "Онлайн пополнение без комиссии";
        String actual = title.getText();
        assertEquals(expected, actual);// проверка что заголовок блока называется так как мы ожидаем

    }

    @Test
    @DisplayName("Проверка логотипов платежных систем")
    public void Icon() {
        By blockLocator = By.xpath("//div[@class='pay__wrapper']");// находим блок онлайн оплата
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(blockLocator)); // проверям что блок видимый
        List<WebElement> Icons = block.findElements(By.xpath(
                ".//img[contains(@alt, 'MasterCard') or contains(@alt, 'Visa') or contains(@alt, 'Белкарт')]"
        ));// ищем все иконки в указанном блоке
        for (WebElement icon : Icons) {// проходим по каждому элементу списка
            System.out.println("Иконка: " + icon.getAttribute("alt")); //вывод в консоль всех иконок
            assertTrue(icon.isDisplayed(), "Иконка видимая"); // проверка что иконка видимая
        }
    }

    @Test
    @DisplayName("Проверка  работы ссылки «Подробнее о сервисе»")
    public void checklink() {
        By blockLocator = By.xpath("//div[@class='pay__wrapper']");// находим блок онлайн оплата
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(blockLocator)); // проверям что блок видимый
        WebElement link = block.findElement(By.xpath(".//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']")); // найдем ссылку в указанном блоке
        assertTrue(link.isDisplayed()); // проверка видимости ссылки
        assertTrue(link.isEnabled()); // проверка кликабельности ссылки

        String href = link.getAttribute("href"); // проверяем саму ссылку, что она не null, не путсая и
        assertNotNull(href);
        assertFalse(href.isEmpty());

        String linkText = link.getText();// проверяем текс ссылки, оо что видит пользователь в браузере
        String expectedText = "Подробнее о сервисе";
        String actualText = link.getText();
        assertEquals(expectedText, linkText);
        wait.until(ExpectedConditions.elementToBeClickable(link));

        String originalUrl = driver.getCurrentUrl();
        System.out.println("URL до клика: " + originalUrl);

        try {
            link.click();
            System.out.println("Клик выполнен");
        } catch (Exception e) {
            System.out.println("клик не сработал, пробуем Actions...");
            Actions actions = new Actions(driver);
            actions.moveToElement(link).click().perform();
            System.out.println("Actions клик выполнен");
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));


        String currentUrl = driver.getCurrentUrl();
        System.out.println("Текущий URL: " + currentUrl);
        assertTrue(currentUrl.contains("/help/") || currentUrl.contains("poryadok"),
                "URL должен содержать /help/ или poryadok, но найден: " + currentUrl);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

    }

}
