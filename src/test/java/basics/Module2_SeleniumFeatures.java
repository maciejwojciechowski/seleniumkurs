package basics;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import utils.waits.CustomWait;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Module2_SeleniumFeatures {

    private final static String PAGE_HEADER_TEXT = "Selenium: Beginners Guide";
    private final static String CONFIRMATION_BOX_MESSAGE = "This is double click";
    private final static String SCREENSHOT_FILE_PATH = "Screenshot/screenshot.png";

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyTextOnPage() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement pageHeader = driver.findElement(By.xpath("//div[@class= 'mainheading']"));

        // This is a good practice to add negative message showing reason why test fails.
        assertEquals("Page header is incorrect.", PAGE_HEADER_TEXT, pageHeader.getText());
    }

    @Test
    public void doubleClickOnButtonTest() {
        driver.get("http://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        WebElement buttonToDoubleClick = driver.findElement(By.xpath("//input[contains(@value,'Double')]"));

        Actions builder = new Actions(driver);
        builder.doubleClick(buttonToDoubleClick).build().perform();

        WebElement confirmationBox = driver.findElement(By.id("box"));

        assertEquals("Button was not double clicked.", CONFIRMATION_BOX_MESSAGE, confirmationBox.getText());
    }

    @Test
    public void dragAndDropTest() {
        driver.get("http://marcojakob.github.io/dart-dnd/basic/web/");

        List<WebElement> listOfDocuments = driver.findElements(By.xpath("//img[@class = 'document']"));

        WebElement firstDocument = listOfDocuments.get(0);
        WebElement trash = driver.findElement(By.xpath("//div[@class = 'trash']"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(firstDocument, trash).build().perform();

        // After removing elements list needs to be refreshed.
        List<WebElement> refreshedListOfDocuments = driver.findElements(By.xpath("//img[@class = 'document']"));

        CustomWait customWait = new CustomWait(driver);
        customWait.waitForDocumentDisappear(refreshedListOfDocuments);

        assertEquals("Document has not been deleted.", 3, refreshedListOfDocuments.size());
    }

    @Test
    public void javaScriptCallTest() {
        driver.get("http://www.google.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = (String) js.executeScript("return document.title");

        assertEquals("Google", title);

        long links = (Long) js.executeScript("var links = document.getElementsByTagName ('A'); return links.length");

        assertEquals("Wrong number of links.",44, links);
    }

    @Test
    public void screenShotTest() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement pageHeader = driver.findElement(By.xpath("//div[@class= 'mainheading']"));

        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Page header is incorrect.", PAGE_HEADER_TEXT, pageHeader.getText());
    }

    @Test
    public void selectFromDropDownTest() {
        driver.get("http://demoqa.com/registration/");

        Select dropDownCountry = new Select(driver.findElement(By.id("dropdown_7")));

        // Verify Dropdown does not support multiple selection
        assertFalse("Dropdown supports multiple choice.", dropDownCountry.isMultiple());

        // Verify Dropdown has 204 options for selection
        assertEquals("There are no 204 countries on the list.", 204, dropDownCountry.getOptions().size());

        dropDownCountry.selectByVisibleText("Poland");
        assertEquals("Poland is not selected country.", "Poland", dropDownCountry.getFirstSelectedOption().getText());
    }

    @Test
    public void setRadioButtonTest() {
        driver.get("http://demoqa.com/registration/");

        WebElement singleRadioButton = driver.findElement(By.xpath("//label[text() = 'Single']/following-sibling::input[1]"));

        if (!singleRadioButton.isSelected())
            singleRadioButton.click();

        assertTrue("Radio button is not selected", singleRadioButton.isSelected());
    }

    @Test
    public void setCheckBoxTest() {
        driver.get("http://demoqa.com/registration/");

        WebElement danceCheckBox = driver.findElement(By.xpath("//input[@value = 'dance']"));

        if (!danceCheckBox.isSelected())
            danceCheckBox.click();

        assertTrue("Checkbox is not selected", danceCheckBox.isSelected());
    }

    @After
    public void tearDown() {
        driver.close();
    }


}
