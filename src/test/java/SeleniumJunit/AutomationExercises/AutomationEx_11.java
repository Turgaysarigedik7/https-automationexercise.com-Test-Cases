package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AutomationEx_11 {
    @Test
    public void test11_Verify_Subscription_in_chart_page(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://automationexercise.com");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']//parent::a"))
                .click();
        WebElement footer = driver.findElement(By.cssSelector("#footer"));
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
}
