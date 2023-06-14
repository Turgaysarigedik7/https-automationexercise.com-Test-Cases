package SeleniumJunit.AutomationExercises;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Date;

public class AutomationEx_15 {
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationexercise.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void test15_Place_Order_Register_before_Checkout(){

        String exceptionResult = "Automation Exercise";
        Assert.assertEquals(exceptionResult,driver.getTitle());

        eventItem("//a[normalize-space()='Signup / Login']");

        Faker faker = new Faker();
        String name = faker.name().nameWithMiddle();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        eventItem("//input[@data-qa='signup-name']",name);
        eventItem("//input[@data-qa='signup-email']",email);
        eventItem("//button[@data-qa='signup-button']");
        eventItem("//input[@id='id_gender1']");
        eventItem("//input[@id='password']",password);

        dropSelectItem("//select[@id='days']","10");
        dropSelectItem("//select[@id='months']","April");
        dropSelectItem("//select[@id='years']","2000");

        eventItem("//input[@id='newsletter']");
        eventItem("//input[@id='optin']");

        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String address = faker.address().fullAddress();
        eventItem("//input[@id='first_name']",firstName);
        eventItem("//input[@id='last_name']",lastname);
        eventItem("//input[@id='address1']",address);

        dropSelectItem("//select[@id='country']","India");


        String state = faker.address().state();
        String city = faker.address().city();
        String zipCode = faker.address().zipCode();
        String mobileNumber = faker.phoneNumber().cellPhone();
        eventItem("//input[@id='state']",state);
        eventItem("//input[@id='city']",city);
        eventItem("//input[@id='zipcode']",zipCode);
        eventItem("//input[@id='mobile_number']",mobileNumber);
        eventItem("//button[normalize-space()='Create Account']");

        String exceptResult = "ACCOUNT CREATED!";
        String actualResult = driver.findElement(By.xpath("//b[normalize-space()='Account Created!']"))
                .getText();
        Assert.assertEquals(exceptResult,actualResult);

        eventItem("//a[@class='btn btn-primary']");

        //*************** Add to Product**********************
        String exceptedresult = "Logged in as sari";

        String result1 = driver.findElement(By.xpath("//i[@class='fa fa-user']//parent::a")).getText();
        String result2 =driver.findElement(By.xpath("//i[@class='fa fa-user']/parent::a")).getText();
        String actualresult = result1+" "+result2;
        Assert.assertEquals(exceptedresult,actualresult);

        Actions actions = new Actions(driver);
        WebElement product = driver.findElement(By.xpath("(//a[@data-product-id='5'])[1]"));
        actions.moveToElement(product).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        actions.click().perform();

        eventItem("//u[normalize-space()='View Cart']//parent::a");

        //***********Cart Check****************
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='active']")).isDisplayed());
        eventItem("//a[@class='btn btn-default check_out']");

        //*************Checkout***************
        Assert.assertTrue(driver.findElement(By.xpath("//h3[normalize-space()='Your delivery address']")).isDisplayed());
        String expectedCartResult ="Winter Top";
        String actualCartResult = driver.findElement(By.xpath("//td[@class='cart_description']//a")).getText();
        Assert.assertEquals(expectedCartResult,actualCartResult);


        String message = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
        eventItem("//textarea[@name='message']",message);
        eventItem("//a[@class='btn btn-default check_out']");
        //***********Payment*************
        String cardName = faker.name().firstName()+" "+faker.name().lastName();
        String cardNumber = faker.finance().creditCard();
        String cvc = Integer.toString(faker.number().numberBetween(0,1000));
        String expiry_month = Integer.toString(faker.number().numberBetween(1,12));
        Date date = new Date();
        String expiry_year = Integer.toString(faker.number().numberBetween(date.getYear(),2050));
        eventItem("//input[@name='name_on_card']",cardName);
        eventItem("//input[@name='card_number']",cardNumber);
        eventItem("//input[@name='cvc']",cvc);
        eventItem("//input[@name='expiry_month']",expiry_month);
        eventItem("//input[@name='expiry-year']",expiry_year);
        eventItem("//button[@id='submit']");
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Order Placed!']"))
                .isDisplayed());
        //**********Delete Account********************
        eventItem("//a[normalize-space()='Delete Account']");
        Assert.assertTrue(driver.findElement(By.xpath("//b[normalize-space()='Account Deleted!']")).isDisplayed());
        eventItem("//a[@class='btn btn-primary']");

    }

    /**
     * dropSelectItem
     * <pre>
     * Finds the existing dropdown with the given locator information.
     * Selects the given item in dropdown.
     *  !!! example usage :  dropSelectItem("//select[@id='months']","April"); !!!
     * </pre>
     * @param xpathQuery String XPath Locator
     * @param visibletext String Selection Value
     */
    private void dropSelectItem(String xpathQuery,String visibletext){
        WebElement drop = driver.findElement(By.xpath(xpathQuery));
        Select selectDays = new Select(drop);
        selectDays.selectByVisibleText(visibletext);
    }
    /**
     * eventItem
     * <pre>
     * Finds and clicks the element in the given locator
     *  !!! example usage :  eventItem("//a[normalize-space()='Delete Account']"); !!!
     * </pre>
     * @param xpathQuery String XPath Locator
     */
    private void eventItem(String xpathQuery){
            driver.findElement(By.xpath(xpathQuery)).click();
    }
    /**
     * eventItem
     * <pre>
     * Finds the element in the given locator.
     * Writes the given text inside.
     *  !!! example usage :  eventItem("//input[@name='name_on_card']","Card Name"); !!!
     * </pre>
     * @param xpathQuery String XPath Locator
     * @param text String input text
     */
    private void eventItem(String xpathQuery,String text){
            driver.findElement(By.xpath(xpathQuery))
                    .sendKeys(text);
    }

    @AfterClass
    public static void tearDown(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       // driver.close();

    }
}
