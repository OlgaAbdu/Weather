import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class OlgaAbduDropMenu {
        @BeforeMethod
        public void start(){
            webDriver = new ChromeDriver();
        }

        @AfterMethod
        public void end(){
            webDriver.quit();
        }
//        static{
//            WebDriverManager.chromedriver().setup();
//        }
        private WebDriver webDriver;

        @Test
        public void getInfoWheatherByCity() throws InterruptedException {
            webDriver.get("https://openweathermap.org/");
            Thread.sleep(3000);
            WebElement search = webDriver.findElement(By.xpath("//input [@placeholder = 'Search city']"));
            search.sendKeys("Austin");
            Thread.sleep(3000);
            webDriver.findElement(By.xpath("//button[@class ='button-round dark']")).click();
            Thread.sleep(4000);
            // //ul[@class="search-dropdown-menu"]/li[5]
            webDriver.findElement(By.xpath("//ul[@class=\"search-dropdown-menu\"]/li[5]")).click();
            Thread.sleep(4000);
            String actualResult = webDriver.findElement(By.xpath("//h2[text()=\"Austin, CA\"]")).getText();
            Assert.assertEquals(actualResult,"Austin, TX");


        }



    }

