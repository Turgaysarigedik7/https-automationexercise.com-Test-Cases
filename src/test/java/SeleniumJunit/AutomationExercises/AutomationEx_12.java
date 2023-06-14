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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AutomationEx_12 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("C://Users//ACER//Downloads//extension_3_17_0_0.crx"));
        driver = new ChromeDriver(opt);
    }
    @Test
    public void test12_Add_Products_In_Cart() throws InterruptedException {

        driver.get("http://automationexercise.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.cssSelector("a[href='/products']"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement product = driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
        Actions a = new Actions(driver);
        a.moveToElement(product).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        a.click().perform();

        driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement secondProduct = driver.findElement(By.xpath("(//a[@data-product-id='2'])[1]"));
        Actions a2 = new Actions(driver);
        a2.moveToElement(secondProduct).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        a.click().perform();

        Thread.sleep(3000);
        driver.findElement(By.xpath("//u[normalize-space()='View Cart']//parent::a"))
                .click();
        List<WebElement> cardProductList = driver.findElements(By.xpath("//td[@class='cart_quantity']"));
        int sum = 0;
        for (var i:cardProductList) {
            sum+=Integer.parseInt(i.getText());
        }
        int excepted = 2;
        int actual = sum;
        Assert.assertEquals(excepted,actual);

        String firstProductPrice =driver.findElement(By.xpath("//td[@class='cart_price']//p[contains(text(),'Rs. 500')]"))
                .getText();
        String actualFirstProductPrice = "Rs. 500";
        Assert.assertEquals(firstProductPrice,actualFirstProductPrice);

        String secondProductPrice =driver.findElement(By.xpath("//td[@class='cart_price']//p[contains(text(),'Rs. 400')]"))
                .getText();
        String actualSecondProductPrice = "Rs. 400";
        Assert.assertEquals(secondProductPrice,actualSecondProductPrice);

        List<WebElement> cardProductTotal = driver.findElements(By.xpath("//p[@class='cart_total_price']"));

        int sumTotal =0;
        for (var i:cardProductTotal) {
            sumTotal+=Integer.parseInt(i.getText().replaceAll("\\D",""));
        }
        int exceptSumTotal=900;
        Assert.assertEquals(exceptSumTotal,sumTotal);

    }
    @After
    public void tearDown(){
        driver.close();
    }
}
