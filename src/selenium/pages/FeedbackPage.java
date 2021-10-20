package selenium.pages;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

import static org.junit.Assert.*;


public class FeedbackPage extends GenericSamplePage {
    //Feedback input page elements
    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement heading;   //Heading 1 of the page
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;   //Text input box for the name
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;    //Text input box for age
    @FindBy(how = How.CLASS_NAME, using = "w3-check")
    private List<WebElement> langBoxes; //A list of all checkboxes, used for picking the language
    @FindBy(how = How.CLASS_NAME, using = "w3-radio")
    private List<WebElement> genderButtons; //A list of all radio buttons, used for picking the gender
    @FindBy(how = How.ID, using = "like_us")
    private WebElement likeUsElement;  //Select element for general opinion feedback
    @FindBy(how = How.NAME, using = "comment")
    private WebElement comment; //Textarea for comments
    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement submitButton;    //Button which submits the form

    //Feedback check page elements
    @FindBy(how = How.ID, using = "name")
    private WebElement checkPageName;    //Name
    @FindBy(how = How.ID, using = "age")
    private WebElement checkPageAge;    //Age
    @FindBy(how = How.ID, using = "language")
    private WebElement checkPageLang;    //Language list
    @FindBy(how = How.ID, using = "gender")
    private WebElement checkPageGender; //Gender
    @FindBy(how = How.ID, using = "option") //Yes, it says 'option' on the page
    private WebElement checkPageOpinion; //Opinion
    @FindBy(how = How.ID, using = "comment")
    private WebElement checkPageComment; //Comment
    @FindBy(how = How.CLASS_NAME, using = "w3-btn")
    private List<WebElement> checkPageButtons; //Both buttons at the bottom

    //Feedback thank you page elements
    @FindBy(how = How.ID, using = "message")
    private WebElement thankYouMessage;    //Thank you message
    @FindBy(how = How.CLASS_NAME, using = "w3-green")
    private WebElement thankYouBackground;  //Background of the thank you message

    //Array containing names of lanugages, as they appear on the input page
    private final String[] languageNames = {"English", "French", "Spanish", "Chinese"};

    //Standard getters for elements of the input page
    public String getHeading() { return heading.getText(); }
    public WebElement getNameInput() { return nameInput; }
    public WebElement getAgeInput() { return ageInput; }
    public List<WebElement> getLangBoxes() { return langBoxes; }
    public List<WebElement> getGenderButtons() { return genderButtons; }
    public WebElement getLikeUsElement() { return likeUsElement; }
    public WebElement getComment() { return comment; }
    public WebElement getSubmitButton() { return submitButton; }

    //Standard getters for elements of the feedback check page
    public WebElement getCheckPageName() { return checkPageName; }
    public WebElement getCheckPageAge() { return checkPageAge; }
    public WebElement getCheckPageLang() { return checkPageLang; }
    public WebElement getCheckPageGender() { return checkPageGender; }
    public WebElement getCheckPageOpinion() { return checkPageOpinion; }
    public WebElement getCheckPageComment() { return checkPageComment; }
    public List<WebElement> getCheckPageButtons() { return checkPageButtons; }

    //Standard getters for elements of the thank you page
    public WebElement getThankYouMessage() { return thankYouMessage; }
    public WebElement getThankYouBackground() { return thankYouBackground; }

    //Getting Yes and No buttons from the check feedback page
    public WebElement getCheckPageYes() { return checkPageButtons.get(0); }
    public WebElement getCheckPageNo() { return checkPageButtons.get(1); }

    //Methods for quickly interacting with the elements

    //Set values for input fields. Sends keys, clicks on buttons, and such
    public void setName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void setAge(int age) { setAge(String.valueOf(age)); }

    public void setAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void setLanguages(int[] checkThese) {
        for(int i = 0; i < checkThese.length; i++) {
            try {
                langBoxes.get(checkThese[i]).click();
            } catch (Exception e) {
                System.out.println("Trying to click on nonexistent language checkboxes!");
                fail();
            }
        }
    }

    public void setGender(int i) {
        try {
            genderButtons.get(i).click();
        } catch (Exception e) {
            System.out.println("Trying to click on a nonexistent gender radio button!");
            fail();
        }
    }

    public void setComment(String s) {
        comment.sendKeys(s);
    }

    //Click the submit button
    public void clickSubmit() {
        submitButton.click();
    }

    //Refresh all element values taken from the input page
    public void refreshValues(WebDriver driver) {
        nameInput = driver.findElement(By.id("fb_name"));
        ageInput = driver.findElement(By.id("fb_age"));
        langBoxes = driver.findElements(By.className("w3-check"));
        genderButtons = driver.findElements(By.className("w3-radio"));
        likeUsElement = driver.findElement(By.id("like_us"));
        comment = driver.findElement(By.name("comment"));
        submitButton = driver.findElement(By.tagName("button"));
    }

    //Methods for checking if various parts of the form are free from user inputs

    //Asserts that language selection checkboxes are all unchecked
    public void checkLangBoxesClear() {
        assertFalse(langBoxes.get(0).isSelected());
        assertFalse(langBoxes.get(1).isSelected());
        assertFalse(langBoxes.get(2).isSelected());
        assertFalse(langBoxes.get(3).isSelected());
    }

    //Asserts that gender selection is set to 'Don't know', the default value
    public void checkGenderButtonsClear() {
        assertTrue(genderButtons.get(2).isSelected());
    }

    //Asserts that the default option is picked in the general feedback selector
    public void checkLikeUsClear() {
        Select likeUsSelect = new Select(likeUsElement);
        assertEquals("Choose your option", likeUsSelect.getFirstSelectedOption().getText());
    }

    //Performs checks for the entire form
    public void checkFormClear() {
        assertEquals("Give us your feedback!", heading.getText());
        assertEquals("", nameInput.getAttribute("value"));
        assertEquals("", ageInput.getAttribute("value"));
        checkLangBoxesClear();
        checkGenderButtonsClear();
        checkLikeUsClear();
        assertEquals("", comment.getAttribute("value"));
    }

    //Asserts that all fields on the check feedback page are empty
    public void checkCheckPageClear() {
        assertEquals("", getCheckPageName().getText());
        assertEquals("", getCheckPageAge().getText());
        assertEquals("", getCheckPageLang().getText());
        assertEquals("null", getCheckPageGender().getText());
        assertEquals("null", getCheckPageOpinion().getText());
        assertEquals("", getCheckPageComment().getText());
    }

    //Color checks

    //Verify that the colors of buttons on the input page are correct
    public void checkInputButtonColors() {
        assertEquals("rgba(33, 150, 243, 1)", submitButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", submitButton.getCssValue("color"));
    }

    //Verify that the colors of buttons on the check feedback page are correct
    public void checkCheckPageButtonColors() {
        WebElement yes = getCheckPageYes();
        WebElement no = getCheckPageNo();
        assertEquals("rgba(76, 175, 80, 1)", yes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yes.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", no.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", no.getCssValue("color"));
    }

    //Verify that the colors of the thank you message are correct
    public void checkThankYouColors() {
        assertEquals("rgba(76, 175, 80, 1)", thankYouBackground.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", thankYouMessage.getCssValue("color"));
    }

    //Feedback submission and verification

    //Fill in the form, submit
    public void submitForm(String name, int age, int[] langs, int gender, String comment) {
        setName(name);
        setAge(age);
        setLanguages(langs);
        setGender(gender);
        setComment(comment);
        clickSubmit();
    }

    //Verify check feedback page output by input values
    public void verifyForm(String name, int age, int[] langs, int gender, String comment) {
        assertEquals(name, getCheckPageName().getText());
        assertEquals(String.valueOf(age), getCheckPageAge().getText());

        //Verification for the language array is a bit messy
        //First, make an array of booleans, where each index corresponds to a language index
        //based on the langs array we received from the text file
        boolean[] selectedLangs = {false, false, false, false};
        for (int i = 0; i < langs.length; i++) {
            try {
                selectedLangs[langs[i]] = true;
            } catch(Exception e) {
                System.out.println("Trying to input a nonexistent language index!");
                fail();
            }
        }
        //Then, generate an expected string, which should appear on the check feedback page,
        //using the selectedLangs array and languageNames array, defined near the top of this class
        String expected = "";
        for (int i = 0; i < 4; i++) {
            if (selectedLangs[i]) {
                expected = expected + languageNames[i] + ",";
            }
        }
        //Chop off the additional comma at the end
        expected = expected.substring(0, expected.length()-1);
        //Finally, compare
        assertEquals(expected, getCheckPageLang().getText());

        //Checks for gender are less messy
        //Generate an expected string by looking at the index
        switch (gender) {
            case 0:
                expected = "male";
                break;
            case 1:
                expected = "female";
                break;
            default:
                expected = "null";
        }
        assertEquals(expected, getCheckPageGender().getText());
        assertEquals(comment, getCheckPageComment().getText());
    }

    public void submitAndVerifyForm(String name, int age, int[] langs, int gender, String comment) {
        submitForm(name, age, langs, gender, comment);
        verifyForm(name, age, langs, gender, comment);
    }

    //Verify that the thank you message displays the correct text
    public void checkThankYouMessage(String name) {
        if (name != "") {
            assertEquals("Thank you, " + name + ", for your feedback!", thankYouMessage.getText());
        }
        else {
            assertEquals("Thank you for your feedback!", thankYouMessage.getText());
        }
    }

    //Verify that data inside the input fields corresponds to the input
    //Used for the last test, checking whether the input fields are filled correctly
    //after pressing Submit and No
    public void checkFields(String name, int age, int[] langs, int gender, String comment) {
        assertEquals(name, nameInput.getAttribute("value"));
        assertEquals(String.valueOf(age), ageInput.getAttribute("value"));
        for (int i = 0; i < langs.length; i++) {
            assertTrue(langBoxes.get(langs[i]).isSelected());
        }
        assertTrue(genderButtons.get(gender).isSelected());
        assertEquals(comment, this.comment.getAttribute("value"));
    }

}
