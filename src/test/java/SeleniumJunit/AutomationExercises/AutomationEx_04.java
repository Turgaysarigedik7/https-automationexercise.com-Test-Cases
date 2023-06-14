package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationEx_04 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test04_Logout_User(){
        driver.get("http://automationexercise.com");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@data-qa='login-email']"))
                .sendKeys("sari@gmail.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']"))
                .sendKeys("1234567");
        driver.findElement(By.xpath("//button[@data-qa='login-button']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//i[@class='fa fa-user']//parent::a"))
                .isDisplayed());
        driver.findElement(By.xpath("//i[@class='fa fa-lock']//parent::a"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"))
                .isDisplayed());

    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}
