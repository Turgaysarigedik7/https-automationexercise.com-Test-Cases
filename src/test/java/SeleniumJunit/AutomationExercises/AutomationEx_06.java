package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationEx_06 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test06_Contact_Us_Form() throws InterruptedException {
        driver.get("http://automationexercise.com");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='Contact us']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='Get In Touch']"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@name='name']"))
                .sendKeys("Turgay");
        driver.findElement(By.xpath("//input[@name='email']"))
                .sendKeys("sari@gmail.com");
        driver.findElement(By.xpath("//input[@name='subject']"))
                .sendKeys("subject text");
        driver.findElement(By.xpath("//textarea[@name='message']"))
                .sendKeys("message text");
        driver.findElement(By.xpath("//input[@name='upload_file']"))
                .sendKeys("C://Users//ACER//Downloads/deneme.txt");
        driver.findElement(By.xpath("//input[@name='submit']"))
                .click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='status alert alert-success']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@class='btn btn-success']"))
                .click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());

    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
       // driver.close();
    }
}
