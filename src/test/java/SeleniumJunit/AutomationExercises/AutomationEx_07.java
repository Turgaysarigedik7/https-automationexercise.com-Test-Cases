package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationEx_07 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test07_Verify_Testcases_Page() throws InterruptedException {
        /*1. Launch browser
        2. Navigate to url 'http://automationexercise.com'
        3. Verify that home page is visible successfully
        4. Click on 'Test Cases' button
        5. Verify user is navigated to test cases page successfully
         */
        driver.get("http://automationexercise.com");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("(//button[contains(text(),'Test Cases')])[1]"))
                .click();
        Thread.sleep(4000);
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Test Cases']"))
                .isDisplayed());
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}
