package basics;


import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ScreenShotOnFailure;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class Module4_AssertJAssertions {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void checkMenuElementsContainsAllDemoQA() {
        driver.get("http://demoqa.com/menu/");

        List<WebElement> listOfDocuments = driver.findElements(By.xpath("//li[@class = 'ui-corner-left']"));

        List<String> listOfTexts = new ArrayList<>();

        boolean doesContain = false;

        for (WebElement e : listOfDocuments){
            listOfTexts.add(e.getText());
        }

        List<String> elements = Arrays.asList("Home", "About", "Contact", "FAQ", "News");
        doesContain = listOfTexts.containsAll(elements);

        assertTrue("Element doesn't exist", doesContain);
    }

    @Test
    public void checkMenuElementsContainsAllDemoQAWithAssertJ() {
        driver.get("http://demoqa.com/menu/");

        List<WebElement> listOfDocuments = driver.findElements(By.xpath("//li[@class = 'ui-corner-left']"));

        List<String> listOfTexts = new ArrayList<>();
        for (WebElement e : listOfDocuments) listOfTexts.add(e.getText());

        assertThat(listOfTexts).contains("Home", "About", "Contact", "FAQ", "News");

        assertThat(listOfTexts).doesNotContain("Registration");

        assertThat(listOfTexts).contains("Home", "About", "Contact", "FAQ", "News").doesNotContain("Registration");
    }

    @Test
    public void doubleClickOnButtonTestWithAssertJ() {
        driver.get("http://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        WebElement buttonToDoubleClick = driver.findElement(By.xpath("//input[contains(@value,'Double')]"));

        Actions builder = new Actions(driver);
        builder.doubleClick(buttonToDoubleClick).build().perform();

        WebElement confirmationBox = driver.findElement(By.id("box"));

        String confirmationBoxMessage = "This is double click";

        assertEquals("Button was not double clicked.", confirmationBoxMessage, confirmationBox.getText());

        assertThat(confirmationBox.getText())
                .as("Button was not double clicked.").isEqualTo(confirmationBoxMessage);

        assertThat(confirmationBox.getText()).isEqualTo(confirmationBoxMessage).as("Button was not double clicked.");

        assertThat(confirmationBox.getText())
                .as("Button was not double clicked.").startsWith("This is");

        assertThat(confirmationBox.getText())
                .as("Button was not double clicked.").endsWith("double click");

        assertThat(confirmationBox.getText())
                .as("Button was not double clicked.").contains("is double");
    }

    @Test
    public void hardAssertionTestWithAssertJ() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "bug!!!";
        String fifth = "fifth";

        // one failing assertions stops the test

        assertThat(first).isEqualTo("first");
        System.out.println("Done first assertion");

        assertThat(second).isEqualTo("second");
        System.out.println("Done second assertion");

        assertThat(third).isEqualTo("third");
        System.out.println("Done third assertion");

        assertThat(fourth).isEqualTo("fourth");
        System.out.println("Done fourth assertion");

        assertThat(fifth).isEqualTo("fifth");
        System.out.println("Done fifth assertion");
    }

    @Test
    public void softAssertionTestWithAssertJ() {
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "bug!!!";
        String fifth = "fifth";

        // one failing assertions does not stop the test
        // it continues the tests and gives information at the end

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(first).isEqualTo("first");
        System.out.println("Done first assertion");

        softly.assertThat(second).isEqualTo("second");
        System.out.println("Done second assertion");

        softly.assertThat(third).isEqualTo("third");
        System.out.println("Done third assertion");

        softly.assertThat(fourth).isEqualTo("fourth");
        System.out.println("Done fourth assertion");

        softly.assertThat(fifth).isEqualTo("fifth");
        System.out.println("Done fifth assertion");

        softly.assertAll();

        // --- Another way ---

        SoftAssertions.assertSoftly(softlyAnotherWay -> {
            softly.assertThat(assertThat(first).isEqualTo("first"));
            softly.assertThat(assertThat(second).isEqualTo("second"));
            softly.assertThat(assertThat(third).isEqualTo("third"));
            softly.assertThat(assertThat(fourth).isEqualTo("fourth"));
            softly.assertThat(assertThat(fifth).isEqualTo("fifth"));
            // no need to call assertAll, it is done by assertSoftly.
        });
    }

}