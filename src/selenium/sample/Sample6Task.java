package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample6Task {
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
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        WebElement elem = driver.findElement(By.xpath("//*[@id='heading_1']"));
        System.out.println(elem.getText());
        elem = driver.findElement(By.xpath("/html/body/h2[1]"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 1"
        elem = driver.findElement(By.xpath("/html/body/div[4]/p[1]"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 2"
        elem = driver.findElement(By.xpath("/html/body/div[4]/p[2]"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 3"
        elem = driver.findElement(By.xpath("/html/body/div[5]/p[1]"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 4"
        elem = driver.findElement(By.xpath("/html/body/div[5]/p[1]"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 5"
        elem = driver.findElement(By.xpath("/html/body/div[6]/p[1]"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "This is also a button"
        elem = driver.findElement(By.xpath("//*[@id=\"buttonId\"]"));
        System.out.println(elem.getAttribute("value"));

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        WebElement elem = driver.findElement(By.cssSelector("#heading_2"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 1"
        elem = driver.findElement(By.cssSelector("#test1 > p:nth-child(1)"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 2"
        elem = driver.findElement(By.cssSelector(".twoTest"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "Test Text 3"
        elem = driver.findElement(By.cssSelector("#test3 > p:nth-child(1)"));
        System.out.println(elem.getText());
//        1-2 ways to find text: "This is also a button"
        elem = driver.findElement(By.cssSelector("#buttonId"));
        System.out.println(elem.getAttribute("value"));
    }
}
