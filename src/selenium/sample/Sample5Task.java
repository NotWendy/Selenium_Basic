package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Sample5Task {
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
        driver.get("https://kristinek.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
//        switch to alert
//        click ok
//        switch to second alert
//        verify alert text
//        click ok on second alert
//        verify that the correct page is opened
        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/button[4]"));
        button.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        alert = driver.switchTo().alert();
        assertEquals("Booooooooo!", alert.getText());
        alert.accept();
        assertEquals("https://kristinek.github.io/site/examples/alerted_page", driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
//        switch to alert
//        click cancel
//        verify the text on page
        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/button[4]"));
        button.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String text = driver.findElement(By.id("textForAlerts")).getText();
        assertEquals("So you desided to say? Good!", text);
    }
}
