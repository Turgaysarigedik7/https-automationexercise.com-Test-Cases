package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AutomationEx_03 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void test03_Login_User_With_Incorrect_Info(){
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(),' Signup / Login')]"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='Login to your account']"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@data-qa='login-email']"))
                .sendKeys("sari3@gmail.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']"))
                .sendKeys("1234567");
        driver.findElement(By.xpath("//button[normalize-space()='Login']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"))
                .isDisplayed());
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.close();
    }
}
