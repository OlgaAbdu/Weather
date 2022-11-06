import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.security.auth.Subject;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;


public class OlgaAbduTest {

    //TC  _1_1  - Тест кейс:
//    //1. Открыть страницу https://openweathermap.org/
//    //2. Набрать в строке поиска город Paris
//    //3. Нажать пункт меню Search
//    //4. Из выпадающего списка выбрать Paris, FR
//    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
    //    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах


    @Test
    public void testTemperatureInFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement imperialF = webDriver.findElement(By.xpath("//div[contains(text(),'Imperial')]"));
        Assert.assertEquals(imperialF.getText(), "Imperial: °F, mph");
        imperialF.click();
        Thread.sleep(2000);
        String actualResult = webDriver.findElement(By.xpath("//span[@class='heading']")).getText();
        Assert.assertEquals(actualResult.contains("°F"), true);
        webDriver.quit();
    }

    //TC_11_03
    //1.  Открыть базовую ссылку
    //2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential
    // for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is
    // anonymised. You can allow all cookies or manage them individually.”
    //3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
    @Test
    public void testCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement cookies = webDriver.findElement(By.xpath("//p[@class='stick-footer-panel__description']"));
        System.out.println(cookies);
        String actualResult = cookies.getText();
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        Assert.assertEquals(actualResult, expectedResult);
        WebElement allowAllButton = webDriver.findElement(By.xpath("//button[@class=\"stick-footer-panel__link\"]"));
        String actualResultButtonAllowAllText = allowAllButton.getText();
        String expectedResultAllowAllBtn = "Allow all";
        Assert.assertEquals(actualResultButtonAllowAllText, expectedResultAllowAllBtn);
        Assert.assertEquals(allowAllButton.isDisplayed(), true);

        WebElement manageCookiesButton = webDriver.findElement(By.xpath("//a[@class=\"stick-footer-panel__link\"]"));
        String actualResultButtonManageCookiesText = manageCookiesButton.getText();
        String expectedResultManageCookiesBtn = "Manage cookies";
        Assert.assertEquals(actualResultButtonManageCookiesText, expectedResultManageCookiesBtn);
        Assert.assertEquals(manageCookiesButton.isDisplayed(), true);

        webDriver.quit();
    }

    //TC_11_04
    // Открыть базовую ссылку
    //2. Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testToggleMenuSupport() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement guideLink = webDriver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        Assert.assertEquals(guideLink.getText(), "Support");
        guideLink.click();
        Thread.sleep(2000);
        WebElement faqDropDown = webDriver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'FAQ')]"));
        Assert.assertEquals(faqDropDown.getText(), "FAQ");
        WebElement howToStartDropDown = webDriver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'How to start')]"));
        Assert.assertEquals(howToStartDropDown.getText(), "How to start");
        WebElement askQuestionDropDown = webDriver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'Ask a question')]"));
        Assert.assertEquals(askQuestionDropDown.getText(), "Ask a question");
        webDriver.quit();
    }

    //
//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
    @Test

    public void testToggleMenuSupportAskAQuestion() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportLink = webDriver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        supportLink.click();
        WebElement askQuestionDropDown = webDriver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'Ask a question')]"));
        Assert.assertEquals(askQuestionDropDown.getText(), "Ask a question");
        askQuestionDropDown.click();

        ArrayList<String> newTb = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(newTb.get(1));
        WebElement emailInput = webDriver.findElement(By.xpath("//input[@id='question_form_email']"));
        emailInput.sendKeys("qweerrt@fdgg.bn");

        WebElement subjectSelect = webDriver.findElement(By.xpath("//select[@id='question_form_subject']"));
        subjectSelect.click();
        WebElement chooseOptionDropMenu = webDriver.findElement(By.xpath("//select[@id='question_form_subject']//option[contains(text(),'Questions about weather data')]"));
        chooseOptionDropMenu.click();

        WebElement messageInput = webDriver.findElement(By.xpath("//textarea[@id='question_form_message']"));
        messageInput.sendKeys("Hello world!");
        WebElement submitButton = webDriver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();

        WebElement errorMessage = webDriver.findElement(By.xpath("//div[@class='help-block']"));
        Assert.assertEquals(errorMessage.getText(), "reCAPTCHA verification failed, please try again.");

        webDriver.quit();
    }
    //6
//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//            4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    public void testErrorMessage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportLink = webDriver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        supportLink.click();
        WebElement askQuestionDropDown = webDriver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(),'Ask a question')]"));
        Assert.assertEquals(askQuestionDropDown.getText(), "Ask a question");
        askQuestionDropDown.click();

        ArrayList<String> newTb = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(newTb.get(1));
        WebElement subjectSelect = webDriver.findElement(By.xpath("//select[@id='question_form_subject']"));
        subjectSelect.click();
        Thread.sleep(2000);
        WebElement chooseOptionDropMenu = webDriver.findElement(By.xpath("//select[@id='question_form_subject']//option[contains(text(),'Questions about weather data')]"));
        chooseOptionDropMenu.click();
        Thread.sleep(2000);

        WebElement messageInput = webDriver.findElement(By.xpath("//textarea[@id='question_form_message']"));
        messageInput.sendKeys("Hello world!");
        Thread.sleep(2000);

        WebElement captcha = webDriver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")); //"//span[@id='recaptcha-anchor']"));
        captcha.click();
        Thread.sleep(4000);
        WebElement submitButton = webDriver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();
//
        WebElement errorMessage = webDriver.findElement(By.xpath("//span[@class='help-block']"));
        Assert.assertEquals(errorMessage.getText(), "can't be blank");

        webDriver.quit();
    }

    //    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
    @Test
    public void testTemperatureInFahrenheitAndCelcius() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement imperialF = webDriver.findElement(By.xpath("//div[contains(text(),'Imperial')]"));
        Assert.assertEquals(imperialF.getText(), "Imperial: °F, mph");
        imperialF.click();
        Thread.sleep(2000);
        String actualResult = webDriver.findElement(By.xpath("//span[@class='heading']")).getText();
        Assert.assertEquals(actualResult.contains("°F"), true);
        WebElement metricC = webDriver.findElement(By.xpath("//div[contains(text(),'Metric')]"));
        Assert.assertEquals(metricC.getText(), "Metric: °C, m/s");
        metricC.click();
        Thread.sleep(2000);
        String actualResultC = webDriver.findElement(By.xpath("//span[@class='heading']")).getText();
        Assert.assertEquals(actualResultC.contains("°C"), true);

        webDriver.quit();
    }

    //TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
    @Test
    public void testLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement logo = webDriver.findElement(By.xpath("//li[@class='logo']"));
        logo.click();
        Thread.sleep(1000);
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://openweathermap.org/");
        webDriver.quit();

    }

    //    TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//
//            3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//   5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
    @Test
    public void testNavigationRome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://openweathermap.org/");
        webDriver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement search = webDriver.findElement(By.xpath("//div[@id='desktop-menu']//input[@name='q']"));
        search.sendKeys("Rome", Keys.ENTER);
        Thread.sleep(2000);
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://openweathermap.org/find?q=Rome");
        String actualResult = webDriver.findElement(By.xpath("//input[@value ='Rome']")).getAttribute("value");
        Assert.assertEquals(actualResult, "Rome");

        webDriver.quit();
    }

    //TC_11_10
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню API
    //3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
    @Test
    public void testAPICheck30Button() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/sabdu/OneDrive/Desktop/Chrome Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        int expectedResult = 30;
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);
        WebElement aPIButton = driver.findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/api']"));
        aPIButton.click();

        List<WebElement> orangeButtons = driver.findElements(By.xpath("//a[@class='btn_block orange round']"));
        List<WebElement> orangeButtons1 = driver.findElements(By.xpath("//a[@class='ow-btn round btn-orange']"));

        int actualResult = orangeButtons.size() + orangeButtons1.size();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }


}

