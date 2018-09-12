package basics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Module1_Locators {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Tested with Firefox 46.0. Does not work with the newest version of FF.
        // System.setProperty("webdriver.firefox.marionette", "src/test/resources/drivers/firefox/geckodriverMac");
        // driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriverMac");
        driver = new ChromeDriver();

        /*

        Better way, needs imports:
        import utils.driver.WebDriverCreators;
        import utils.driver.WebDriverProvider;

        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();

        Top tip - you can use library for driver:
        https://github.com/bonigarcia/webdrivermanager

        */

        // We have many methods hidden here. Let's explore.
        driver.manage().window().maximize();
    }

    @Test
    public void verifyButtonVisibilityById() throws InterruptedException {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement buttonById = driver.findElement(By.id("but1"));

        // Bad practice. Added only to see how it works.
        Thread.sleep(3000);

        assertTrue("Button is not displayed.", buttonById.isDisplayed());
    }

    @Test
    public void verifyButtonVisibilityByName() {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement buttonByName = driver.findElement(By.name("but2"));

        assertTrue("Button is not displayed.", buttonByName.isDisplayed());
    }

    @Test
    public void verifyButtonVisibilityByXPath() {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement buttonByXPath = driver.findElement(By.xpath("//input[contains(@value,'Verify this')]"));

        assertTrue("Button is not displayed.", buttonByXPath.isDisplayed());
    }

    @Test
    public void verifyButtonVisibilityByXPathWithClass() {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement buttonByXPath = driver.findElement(By.xpath("//div[@class='leftdiv']/input[2]"));

        assertTrue("Button is not displayed.", buttonByXPath.isDisplayed());
    }

    @Test
    public void verifySiblingButtonVisibilityByXPath() {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement buttonByXPath = driver.findElement(By.xpath("//input[@value='Button with ID']/following-sibling::input[@value='Sibling Button']"));

        assertTrue("Button is not displayed.", buttonByXPath.isDisplayed());
    }

    @Test
    public void verifyLinkVisibilityByLink() {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement linkByXPath = driver.findElement(By.linkText("Index"));

        assertTrue("Link is not displayed.", linkByXPath.isDisplayed());
    }

    @Test
    public void verifyLinkVisibilityByCSS() {
        driver.get("http://book.theautomatedtester.co.uk/chapter2");
        WebElement buttonByCSS = driver.findElement(By.cssSelector("div.leftdiv input"));

        assertTrue("Link is not displayed.", buttonByCSS.isDisplayed());
    }

    @Test
    public void verifyNumberOfLinks() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        List<WebElement> allLinksContainClickKeyword = driver.findElements(By.xpath("//div[contains(text(), 'Click this')]"));

        assertEquals("Page has wrong number of links containing Click keyword.", 3, allLinksContainClickKeyword.size());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
