package selenium.sample;

import com.sun.source.tree.AssertTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
//         check that value of second button is "This is also a button"
        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
        System.out.println("Number of test class elements: " + allElementsWithClass.size());
        String buttonText = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals("This is also a button",buttonText);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        String buttonText = driver.findElement(By.id("buttonId")).getAttribute("value").toLowerCase(Locale.ROOT);
        assertTrue("Expecting the text of the second button in lowercase to be 'this is also a button'",
                buttonText.equals("unbelievable sample text"));
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String buttonText = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertFalse(buttonText.equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test" contain number 190
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'\"190\"')]"));
        assertTrue("Text not found!", list.size() > 0);
    }
}
