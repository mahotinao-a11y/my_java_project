import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private static WebDriver driver; // объявляем переменную driver, чтобы использовать ее далее
    private static WebDriverWait wait; // объявляем переменную для ожидания


    @BeforeAll // запуск один раз перед выполнением всех тестов
    public static void installWebDriver() {
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    public void openDriver() { // открывает браузер для каждого теста
        driver = new ChromeDriver(); // нового объекта драйвера
        driver.manage().window().maximize();  // разворачивает на весь экран
        driver.get("https://www.mts.by/"); // переходим в браузер
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ждем загрузку браузера
        acceptCookieBanner(); // метод, который закртывает кукки по нажатию на кнопку "Принять"
        System.out.println("Браузер открыт, куки приняты");
    }

    @AfterEach  // закрывает браузер после каждого теста
    public void closeDriver() {
        if (driver != null) {// проверка , что браузерне null
            driver.quit(); // заурывает браузер
            System.out.println("Браузер закрыт");
        }
    }
    private static void acceptCookieBanner() { // метод для принятия кукки
        try {// юлок если кукки присутствуют
            WebElement acceptButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='cookie-agree']")));
            acceptButton.click();  // кликаем на Принять
            System.out.println("Куки-баннер принят");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'cookie')]") // ожидание закрытия кукки
            ));
            System.out.println("Куки-баннер исчез");
        } catch (Exception e) { // блок если кукк нет
            System.out.println("Куки-баннер не найден или уже принят");
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
    @Test
    @DisplayName("Заполнение полей и проверка кнопки «Продолжить» (Услуги связи)")
    public void сontinueButton() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='pay__wrapper']"))); // находим блок оплаты
        WebElement servicesTab = block.findElement(By.xpath(".//span[contains(text(), 'Услуги связи')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", servicesTab); // Приводит driver к типу JavascriptExecutor,JavaScript-код: берет первый переданный аргумент и кликает по нему,Передает servicesTab как первый аргумент в JavaScript
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Номер телефона']"))); //Ждем, пока появится поле для ввода телефона
        phoneInput.sendKeys("297777777"); // заполение поля телефона

        WebElement sumInput = block.findElement(By.xpath("//input[@placeholder='Сумма']"));// ищем в блоке поле суммы
        sumInput.sendKeys("10");// заполняем сумму

        String expectedPhone = "297777777";
        String actualPhone = phoneInput.getAttribute("value");
        assertEquals(expectedPhone, actualPhone, "Номер телефона введен неверно");
        String expectedSum = "10";
        String actualSum = sumInput.getAttribute("value");
        assertEquals(expectedSum, actualSum);

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]"))); //Ждем, пока кнопка станет кликабельной, и нажимаем
        continueButton.click();
        wait.until(ExpectedConditions.urlContains("pay"));
    }

}
