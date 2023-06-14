package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationEx_05 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test04_Register_user_with_existing_Email(){
        driver.get("http://automationexercise.com");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='New User Signup!']"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@name='name']"))
                .sendKeys("turgay");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys("sari@gmail.com");
        driver.findElement(By.xpath("//button[normalize-space()='Signup']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[normalize-space()='Email Address already exist!']"))
                .isDisplayed());
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}
