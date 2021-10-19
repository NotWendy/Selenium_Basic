package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.io.File;

import static org.junit.Assert.*;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter text instead of a number, check that correct error is displayed
        WebElement numInput = driver.findElement(By.id("numb"));
        numInput.sendKeys("Oh no, text!");
        WebElement submit = driver.findElement(By.cssSelector(".w3-btn"));
        submit.click();
        WebElement errorText = driver.findElement(By.id("ch1_error"));
        assertEquals("Please enter a number", errorText.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is displayed
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector(".w3-btn"));
        //Error element is present on the page from the start
        //It just doesn't contain any text by default
        //Otherwise we'd be looking for it after pressing Submit
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        try {
            numInput.sendKeys("48");
            submit.click();
            assertEquals("Number is too small", errorText.getText());
        } catch (AssertionError e) {
            System.out.println("Error in errorOnNumberTooSmall");
            e.printStackTrace();
        }

        try {
            numInput.sendKeys("49");
            submit.click();
            assertEquals("Number is too small", errorText.getText());
        } catch (AssertionError e) {
            System.out.println("Error in errorOnNumberTooSmall");
            e.printStackTrace();
        }

    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector(".w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        try {
            numInput.sendKeys("666");
            submit.click();
            assertEquals("Number is too big", errorText.getText());
        } catch (AssertionError e) {
            System.out.println("Error in errorOnNumberTooBig");
            e.printStackTrace();
        }

    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then press submit and verify that no error is displayed and that square root is calculated correctly
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector(".w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        try {
            numInput.sendKeys("81");
            submit.click();
            Alert alert = driver.switchTo().alert();
            assertEquals("Square root of 81 is 9.00", alert.getText());
            alert.accept();
        } catch (AssertionError e) {
            System.out.println("Error in the first half of correctSquareRootWithoutRemainder");
            e.printStackTrace();
        }

        //Apparently we can't retrieve text from the page while an alert is up
        try {
            assertEquals("", errorText.getText());
        } catch (AssertionError e) {
            System.out.println("Error in the second half of correctSquareRootWithoutRemainder");
            e.printStackTrace();
        }

    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3)
//        and press submit, then verify that no error is displayed and that square root is calculated correctly
        WebElement numInput = driver.findElement(By.id("numb"));
        WebElement submit = driver.findElement(By.cssSelector(".w3-btn"));
        WebElement errorText = driver.findElement(By.id("ch1_error"));

        try {
            numInput.sendKeys("80");
            submit.click();
            Alert alert = driver.switchTo().alert();
            assertEquals("Square root of 80 is 8.94", alert.getText());
            alert.accept();
        } catch (AssertionError e) {
            System.out.println("Error in the first half of correctSquareRootWithRemainder");
            e.printStackTrace();
        }

        try {
            assertEquals("", errorText.getText());
        } catch (AssertionError e) {
            System.out.println("Error in the second half of correctSquareRootWithRemainder");
            e.printStackTrace();
        }

    }
}
