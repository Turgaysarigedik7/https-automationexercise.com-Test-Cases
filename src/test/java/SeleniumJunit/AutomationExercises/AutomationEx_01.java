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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class AutomationEx_01 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void test01_Register_User() throws InterruptedException {
        driver.get("http://automationexercise.com");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/product_details/43']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'New User Signup!')]"))
                .isDisplayed());
        driver.findElement(By.xpath("//input[@name='name']"))
                .sendKeys("turgay");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys("sari3kkkkkk@gmail.com");
        driver.findElement(By.xpath("//button[normalize-space()='Signup']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Enter Account Information']"))
                .isDisplayed());
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
        Thread.sleep(5000);
        WebElement frame1 = driver.findElement(By.id("aswift_2"));
        driver.switchTo().frame(frame1);
        WebElement frame2 = driver.findElement(By.id("ad_iframe"));
        driver.switchTo().frame(frame2);
        driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(5000);

        Thread.sleep(10000);

        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]//i"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[normalize-space()='Delete Account']"))
                .click();
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Account Deleted!']"))
                .isDisplayed());
        driver.findElement(By.xpath("//a[@class='btn btn-primary']"))
                .click();
    }
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.close();
    }
}
