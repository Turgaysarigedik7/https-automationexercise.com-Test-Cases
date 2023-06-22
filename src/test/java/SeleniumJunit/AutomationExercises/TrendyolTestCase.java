package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class TrendyolTestCase {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void method01() throws InterruptedException {
        driver.get("https://www.trendyol.com/");
        Thread.sleep(5000);
        if(checkAnyElementIsActiveOrDisplayedXPATH("//div[@class='modal-content']")){
            driver.findElement(By.xpath("//div[@class='modal-close']")).click();
        }
        driver.findElement(By.xpath("//p[contains(text(),'Giriş Yap')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='login email']")).sendKeys("tsarigedik@gmail.com");
        driver.findElement(By.xpath("//input[@name='login-password']")).sendKeys("Trgykzlm01");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']//span[contains(text(),'Giriş Yap')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[contains(text(),'Çok Satanlar')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//div[@class='product-card'])[1]//i[@class='i-heart']")).click();
        Thread.sleep(2000);
        String firstPageWindowHandleCode = driver.getWindowHandle();
        driver.findElement(By.xpath("(//div[@class='product-card'])[1]")).click();
        Set<String> handleCodes = driver.getWindowHandles();
        String secondPageWindowHandleCode ="";
        for (var each:handleCodes) {
            if(!each.equals(firstPageWindowHandleCode)){
                secondPageWindowHandleCode = each;
            }
        }
        driver.switchTo().window(secondPageWindowHandleCode);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='add-to-basket-button-text']")).click();
        driver.switchTo().window(firstPageWindowHandleCode);
    }
    public boolean checkAnyElementIsActiveOrDisplayedXPATH(String XPATHlocator) throws InterruptedException {
        Thread.sleep(5000);

        List<WebElement> elementList = driver.findElements(By.xpath(XPATHlocator));
        System.out.println(elementList.size());
        if (elementList.size() > 0) {
            // Element is present
            return true;
        } else {
            // Element is not present
            return false;
        }
    }
    @After
    public void tearDown(){
        driver.close();
    }
}
