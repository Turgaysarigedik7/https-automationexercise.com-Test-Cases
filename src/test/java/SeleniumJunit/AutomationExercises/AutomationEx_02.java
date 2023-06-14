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

public class AutomationEx_02 {
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
    public void test02_Login_User_With_Correct_Info() throws InterruptedException {

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
        Thread.sleep(10000);
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),' Logged in as ')]"))
                .isDisplayed());
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[normalize-space()='Delete Account']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Account Deleted!']"))
                .isDisplayed());

    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.close();
    }
}
