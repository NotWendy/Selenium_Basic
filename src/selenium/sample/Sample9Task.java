package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Sample9Task {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();

        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        Thread.sleep(5000);

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();

        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement loadGreenButton = driver.findElement(By.id("start_green"));
        loadGreenButton.click();

        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreenText.isDisplayed());

        WebDriverWait wait = (WebDriverWait)
                new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */

        //This is annoying, I might finish it later
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement loadGreenAndBlue = driver.findElement(By.id("start_green_and_blue"));
        loadGreenAndBlue.click();

        WebElement greenLoading = driver.findElement(By.id("loading_green_without_blue"));
        assertFalse(loadGreenAndBlue.isDisplayed());
        assertTrue(greenLoading.isDisplayed());

        WebElement greenAndBlueLoading = driver.findElement(By.id("loading_green_with_blue"));
        assertFalse(loadGreenAndBlue.isDisplayed());
        assertFalse(greenLoading.isDisplayed());
        assertTrue(greenAndBlueLoading.isDisplayed());



    }

}