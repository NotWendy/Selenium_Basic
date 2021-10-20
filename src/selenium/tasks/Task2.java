package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AgeSamplePage;
import selenium.pages.FeedbackPage;
import static org.junit.Assert.*;

import java.io.File;

public class Task2 {
    static WebDriver driver;
    static FeedbackPage fPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        fPage = PageFactory.initElements(driver, FeedbackPage.class);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        fPage.checkFormClear();
        fPage.checkInputButtonColors();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letters and red with white letters)
        fPage.clickSubmit();
        fPage.checkCheckPageClear();
        WebElement yes = fPage.getCheckPageYes();
        WebElement no = fPage.getCheckPageNo();
        fPage.checkCheckPageButtonColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        int[] langs = {0, 1};
        fPage.submitAndVerifyForm("MY NAME", 30, langs, 0, "MY COMMENT");
        fPage.checkCheckPageButtonColors();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        String name = "MY NAME";
        fPage.setName(name);
        fPage.clickSubmit();
        fPage.getCheckPageYes().click();
        fPage.checkThankYouMessage(name);
        fPage.checkThankYouColors();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        fPage.clickSubmit();
        fPage.getCheckPageYes().click();
        fPage.checkThankYouMessage("");
        fPage.checkThankYouColors();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        int[] langs = {0, 1};
        fPage.submitForm("MY NAME", 30, langs, 0, "MY COMMENT");
        fPage.getCheckPageNo().click();
        fPage.refreshValues(driver);    //Refreshing the WebElement values, so that Selenium sees input fields again
        fPage.checkFields("MY NAME", 30, langs, 0, "MY COMMENT");
    }

}
