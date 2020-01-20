import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.xml.xpath.XPath;

public class HomeWorkSearch {
    ChromeDriver driver;

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.chrome.driver", DriversCons.CHROME_WEBDRIVER);
        driver = new ChromeDriver();

    }

    @AfterSuite
    public void quitBrowser() {
        driver.quit();
    }


    @Test
    public void testYahooSearch() {

        navigateToMainPage("https://www.yahoo.com/");
        typeSearchQuery("/html/body/header/div[2]/div/div/div[1]/div[1]/div[1]/div/form/input[1]",
                "Weather forecast in Seattle");
        submitSearch("/html/body/header/div[2]/div/div/div[1]/div[1]/div[1]/div/form/input[1]");

        String resultAssert = "/html/body/div[1]/div[3]/div/div/div[1]/div/ol[2]/li/div/div/span";
        waitForResult(resultAssert);
        assertResultPage(resultAssert);

    }

    @Test
    public void testSearchMavenRep() {

        navigateToMainPage("https://mvnrepository.com/");
        typeSearchQuery("/html/body/div[1]/div[1]/div[2]/form/input[1]", "Java");
        submitSearch("/html/body/div[1]/div[1]/div[2]/form/input[1]");
        waitForResult("/html/body/div[1]/div[3]/h2");
        assertResultPage("/html/body/div[1]/div[3]/h2");

    }

    private void assertResultPage(String xpath) {
        boolean isResultsInfoDisplayed = driver.findElementByXPath(xpath).isDisplayed();
        Assert.assertTrue(isResultsInfoDisplayed);
    }

    private void waitForResult(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    private void submitSearch( String searchPath) {
        WebElement element = driver.findElementByXPath(searchPath);
        element.submit();
    }

    private void typeSearchQuery(String xpath, String keys) {
        WebElement element = driver.findElementByXPath(xpath);
        element.sendKeys(keys);
    }

    private void navigateToMainPage(String url) {
        driver.get(url);
    }

}
