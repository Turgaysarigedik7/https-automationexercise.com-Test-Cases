package SeleniumJunit.AutomationExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class AutomationEx_13 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("C://Users//ACER//Downloads//extension_3_17_0_0.crx"));
        driver = new ChromeDriver(opt);
    }
    @Test
    public void test13_Verify_Product_quantity_in_Cart(){


        driver.get("http://automationexercise.com");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@href='/product_details/1']"))
                .click();
        String expect ="Product Details";

        Assert.assertEquals(expect,characterSelection());

        WebElement quantityElement = driver.findElement(By.xpath("//input[@id='quantity']"));
        quantityElement.clear();
        quantityElement.sendKeys("4");

        driver.findElement(By.xpath("//button[@class='btn btn-default cart']"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//u[contains(text(),'View Cart')]//parent::a"))
                .click();
        int expectedQuantity = 4;
        int actualQuantity =Integer.parseInt(driver.findElement(By.xpath("//td[@class='cart_quantity']//button"))
                .getText());
        Assert.assertEquals(expectedQuantity,actualQuantity);

    }
    private String characterSelection(){
        int spaceIndex = driver.getTitle().indexOf(" ");
        String actual =driver.getTitle().substring(spaceIndex+1);
        spaceIndex = actual.indexOf(" ");
        actual = actual.substring(spaceIndex+1);
        spaceIndex = actual.indexOf(" ");
        actual = actual.substring(spaceIndex+1);
        return actual;
    }
}
