package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AutomationEx_10 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test10_Verify_Subscription_in_home_page(){

        driver.get("http://automationexercise.com");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        WebElement footer = driver.findElement(By.id("footer"));
        Actions a = new Actions(driver);
        a.moveToElement(footer).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='Subscription']"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@id='susbscribe_email']"))
                .sendKeys("sari@gmail.com");
        driver.findElement(By.xpath("//button[@id='subscribe']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'You have been successfully subscribed!')]"))
                .isDisplayed());

    }
    @After
    public void tearDown(){
        driver.close();
    }
}
