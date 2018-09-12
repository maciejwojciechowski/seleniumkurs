package basics;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenShotOnFailure;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import utils.waits.CustomWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Module3_Waits {

    private static WebDriver driver;
    private CustomWait customWait;

    @BeforeClass
    public static void setDriver() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
    }

    @Before
    public void setUp() {
        customWait = new CustomWait(driver);
        driver.manage().window().maximize();
    }

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);

    @Test
    public void implicitWaitTest() {
        driver.get("http://robertkaszubowski.com");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement homeButton = driver.findElement(By.linkText("HOME"));

        assertTrue("HOME button is not visible", homeButton.isDisplayed());
    }

    @Test
    public void explicitWaitTest() {
        driver.get("http://robertkaszubowski.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement aboutMeButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("ABOUT ME111")));

        aboutMeButton.click();

        WebElement aboutMeHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text() = 'About the Author']")));

        // Other and better way
        WebElement header = driver.findElement(By.xpath("//h2[text() = 'About the Author']"));
        customWait.waitForElementToBeVisible(header);

        assertTrue("Header is not visible", aboutMeHeader.isDisplayed());

    }

    @Test
    public void fluentWaitTest() {
        driver.get("http://marcojakob.github.io/dart-dnd/basic/web/");

        List<WebElement> listOfDocuments = driver.findElements(By.xpath("//img[@class = 'document']"));

        WebElement firstDocument = listOfDocuments.get(0);
        WebElement trash = driver.findElement(By.xpath("//div[@class = 'trash']"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(firstDocument, trash).perform();

        // After removing elements list needs to be refreshed.
        List<WebElement> refreshedListOfDocuments = driver.findElements(By.xpath("//img[@class = 'document']"));

        customWait.waitForDocumentDisappear(refreshedListOfDocuments);

        assertEquals("Document has not been deleted.", 3, refreshedListOfDocuments.size());
    }

    @Test
    public void threadSleepTest() {
        driver.get("http://robertkaszubowski.com");

        // This is a big no no! Use only when there is no other options.

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement homeButton = driver.findElement(By.linkText("HOME"));

        assertTrue("HOME button is not visible", homeButton.isDisplayed());
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

}