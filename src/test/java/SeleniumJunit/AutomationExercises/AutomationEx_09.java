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

import java.util.List;

public class AutomationEx_09 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
    }
    @Test
    public void test09_Search_Product(){

        driver.get("http://automationexercise.com");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@href='/products']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center']"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@id='search_product']"))
                .sendKeys("Blue");
        driver.findElement(By.xpath("//button[@id='submit_search']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='title text-center']"))
                .isDisplayed());
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='product-image-wrapper']"));
        int expected = 7;
        int actual = productList.size();
        Assert.assertEquals(expected,actual);


    }
    @After
    public void tearDown(){
        driver.close();
    }
}
