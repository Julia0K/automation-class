import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

// This is my homework for Session 6 Checkboxes part

public class Checkboxes {

    WebDriver driver;

    @BeforeSuite
    public void setup() {
            System.setProperty("webdriver.gecko.driver", DriversCons.FIREFOX_WEBDRIVER);
            driver = new FirefoxDriver();
    }

    @AfterSuite
    public void quitBrowser() {
        driver.quit();
    }

    @Test
    public void testDifferentWay() {

        //Verified initial statement 2 checkboxes
        openMainPage();
        List<WebElement> listOfInputs = getInputs("input");
        Assert.assertEquals(listOfInputs.size(), 2);

       //Verify 1st checkobox in unchecked
        WebElement checkBox1 = listOfInputs.get(0);
        Assert.assertFalse(checkBox1.isSelected());

        // Verify 2nd is checked
        WebElement checkBox2 = listOfInputs.get(1);
        Assert.assertTrue(checkBox2.isSelected());

        //Verify 1st box changes to checked after selecting
        checkBox1.click();
        Assert.assertTrue(checkBox1.isSelected());

    }

    @Test
    public void testAnotherFlow() {
        openMainPage();
        List<WebElement> listOfInputs = getInputs("input");;
        Assert.assertEquals(listOfInputs.size(), 2);

        WebElement checkbox2 = listOfInputs.get(1);
        Assert.assertTrue(checkbox2.isSelected());

        checkbox2.click();
        Assert.assertFalse(checkbox2.isSelected());
    }

    @Test
    public void testPage() {
        openMainPage();
        List<WebElement> listOfInputs = getInputs("input");

        Assert.assertTrue(listOfInputs.size() == 2);

        WebElement checkBox1 = listOfInputs.get(0);

        String isBox1Checked = checkBox1.getAttribute("checked");

        Assert.assertNull(isBox1Checked);

        checkBox1.click();

        Assert.assertEquals(checkBox1.getAttribute("checked") , "true");

        WebElement checkBox2 = listOfInputs.get(1);

        Assert.assertEquals(checkBox2.getAttribute("checked"), "true");

    }

    @Test
    public void openMainPage() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    private List<WebElement> getInputs(String name) {
        return driver.findElements(By.tagName(name));

    }

}
