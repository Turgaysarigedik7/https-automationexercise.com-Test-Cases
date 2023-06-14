package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class AutomationEx_08 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        driver = new ChromeDriver(opt);
    }
    @Test
    public void test08_Verify_All_Products_product_detail_page() throws InterruptedException {
        driver.get("http://automationexercise.com");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@href='/products']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"))
                .isDisplayed());
        driver.findElement(By.cssSelector("a[href='/product_details/1']"))
                .click();
        Thread.sleep(20000);

        Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='Blue Top']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[normalize-space()='Category: Women > Tops']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Rs. 500']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Availability:']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Condition:']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Brand:']"))
                .isDisplayed());
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        //driver.close();
    }
}
