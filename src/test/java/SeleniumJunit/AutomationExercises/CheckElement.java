package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CheckElement {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void method1() throws InterruptedException {
        driver.get("https://www.trendyol.com");
        if(checkAnyElementIsActiveOrDisplayedXPATH("//div[@class='modal-content']")){
            driver.findElement(By.xpath("//div[@class='modal-close']")).click();
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='Anne & Çocuk']")))
                .perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Bebek Şampuanı')]")).click();
        driver.findElement(By.xpath("//input[@class='fltr-srch-inpt']")).sendKeys("Nivea Baby");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(text(),'Nivea Baby')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='fltr-cntnr-ttl'][normalize-space()='Fiyat']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[normalize-space()='400 TL - 500 TL']/parent::*")).click();

    }
    /**
     * checkAnyElementIsActiveOrDisplayedXPATH
     * <pre>
     *         XPATH standardinda locatoru verilen herhangi bir elementin varligini,
     *         gorunurlugunu kontrol eder
     *         !!! örnek kullanım :  checkAnyElementIsActiveOrDisplayedXPATH("//input[@id='mobile_number']"); !!!
     * </pre>
     *
     * @param XPATHlocator String locator
     * @author Baris Can Ates
     */
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
