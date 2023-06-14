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
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

public class AutomationEx_14 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addExtensions(new File("C://Users//ACER//Downloads//extension_3_17_0_0.crx"));
        driver = new ChromeDriver(opt);
    }

    @Test
    public void test14_Place_Order_Register_while_Checkout(){

        driver.get("http://automationexercise.com");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        WebElement product =driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
        Actions actions= new Actions(driver);
        actions.moveToElement(product).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        actions.click().perform();

        driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"))
                .click();
        driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//a[@href='/view_cart']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Shopping Cart')]"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"))
                .click();
        driver.findElement(By.xpath("//u[normalize-space()='Register / Login']//parent::a"))
                .click();
        driver.findElement(By.xpath("//input[@data-qa='signup-name']"))
                .sendKeys("Sefa");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys("sefhkkkksda@gmail.com");
        driver.findElement(By.xpath("//button[normalize-space()='Signup']"))
                .click();
        driver.findElement(By.xpath("//input[@id='id_gender1']"))
                .click();
        driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("1234567");

        WebElement dropDays = driver.findElement(By.xpath("//select[@id='days']"));
        Select selectDays = new Select(dropDays);
        selectDays.selectByVisibleText("17");

        WebElement dropMonths = driver.findElement(By.xpath("//select[@id='months']"));
        Select selectMonths = new Select(dropMonths);
        selectMonths.selectByVisibleText("November");

        WebElement dropYears = driver.findElement(By.xpath("//select[@id='years']"));
        Select selectYears = new Select(dropYears);
        selectYears.selectByVisibleText("1999");

        driver.findElement(By.xpath("//input[@id='newsletter']"))
                .click();
        driver.findElement(By.xpath("//input[@id='optin']"))
                .click();
        driver.findElement(By.xpath("//input[@id='first_name']"))
                .sendKeys("Turgay");
        driver.findElement(By.xpath("//input[@id='last_name']"))
                .sendKeys("sefa");
        driver.findElement(By.xpath("//input[@id='company']"))
                .sendKeys("Company Name");
        driver.findElement(By.xpath("//input[@id='address1']"))
                .sendKeys("Address1 Text");
        driver.findElement(By.xpath("//input[@id='address2']"))
                .sendKeys("Address2 Text");

        WebElement dropCountry = driver.findElement(By.cssSelector("Select[id='country']"));
        Select selectCounrty = new Select(dropCountry);
        selectCounrty.selectByIndex(1);

        driver.findElement(By.cssSelector("input[id='state']"))
                .sendKeys("State Text");
        driver.findElement(By.cssSelector("input[id='city']"))
                .sendKeys("Antalya");
        driver.findElement(By.cssSelector("input[id='zipcode']"))
                .sendKeys("07570");
        driver.findElement(By.cssSelector("input[id='mobile_number']"))
                .sendKeys("05764665455");
        driver.findElement(By.xpath("//button[contains(text(),'Create Account')]"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Account Created!']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@class='btn btn-primary']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),' Logged in as ')]"))
                .isDisplayed());

        driver.findElement(By.xpath("(//a[@href='/view_cart'])[1]"))
                .click();
        driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.xpath("//textarea[@name='message']"))
                .sendKeys("text message");
        driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"))
                .click();
        driver.findElement(By.xpath("//input[@name='name_on_card']"))
                .sendKeys("sefa");
        driver.findElement(By.xpath("//input[@name='card_number']"))
                .sendKeys("44444444444444");
        driver.findElement(By.xpath("//input[@placeholder='ex. 311']"))
                .sendKeys("311");
        driver.findElement(By.xpath("//input[@placeholder='MM']"))
                .sendKeys("12");
        driver.findElement(By.xpath("//input[@placeholder='YYYY']"))
                .sendKeys("2027");
        driver.findElement(By.xpath("//button[@id='submit']"))
                .click();
        driver.findElement(By.xpath("//a[normalize-space()='Delete Account']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Account Deleted!']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@class='btn btn-primary']"))
                .click();
    }
    @After
    public void tearDown(){
        driver.close();
    }
}
