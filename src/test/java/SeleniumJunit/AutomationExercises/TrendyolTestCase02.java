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
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;

public class TrendyolTestCase02 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void addToCart() throws InterruptedException {
        driver.get("https://www.trendyol.com/");
        if (checkAnyElementIsActiveOrDisplayedXPATH("//div[@class='modal-content']")){
            driver.findElement(By.xpath("//div[@class='modal-close']")).click();
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Erkek']"))).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Erkek']/parent::li//a[text()='Giyim']/parent::div//ul[@class='sub-item-list']//a[text()='Tişört']"))
                .click();
        String firstWindowHandleCode = driver.getWindowHandle();
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='p-card-wrppr with-campaign-view']"));
        products.get(0).click();


        Set<String> handleCodes = driver.getWindowHandles();
        String secondPageWindowhandleCode ="";
        for (var each :handleCodes) {
            if(!each.equals(firstWindowHandleCode)){
                secondPageWindowhandleCode =each;
            }
        }
        driver.switchTo().window(secondPageWindowhandleCode);

        Thread.sleep(3000);
        if(checkAnyElementIsActiveOrDisplayedXPATH("//div[@class='campaign-pointer']")){
            driver.findElement(By.xpath("//div[@class='campaign-button bold']")).click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Black Sokak']")).isDisplayed());
        Actions actionsColor = new Actions(driver);
        List<WebElement> colors = driver.findElements(By.xpath("//a[@class='slc-img']"));
        for (var each: colors) {
            actionsColor.moveToElement(each).perform();
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//div[text()='L']")).click();
        driver.findElement(By.xpath("//div[@class='product-button-container']//div[normalize-space()='Sepete Ekle']"))
                .click();
        driver.findElement(By.xpath("//p[normalize-space()='Sepetim']/parent::a")).click();
        driver.findElement(By.xpath("//span[text()='Sepeti Onayla']/parent::a")).click();


    }

    /**
     * checkAnyElementIsActiveOrDisplayedXPATH
     * Checks whether the given locator is visible and active.
     * @param xPathLocator String type XPath Locator parameter
     * @return return type boolean
     */
    public boolean checkAnyElementIsActiveOrDisplayedXPATH(String xPathLocator){
        List<WebElement> elementList = driver.findElements(By.xpath(xPathLocator));
        if (elementList.size()>0){
            return true;
        }
        else
            return false;
    }
    @After
    public void tearDown(){
        driver.close();
    }
}
