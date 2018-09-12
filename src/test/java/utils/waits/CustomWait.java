package utils.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomWait {

    private static final int DEFAULT_TIMEOUT_IN_SEC = 20;
    private static final int PULLING_TIMEOUT_IN_SEC = 5;
    private static final int EXPLICIT_WAIT_TIMEOUT = 10;

    private WebDriver driver;

    public CustomWait(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForDocumentDisappear(List<WebElement> listOfDocuments){
        FluentWait wait = new FluentWait(driver).withTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS).pollingEvery(PULLING_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        wait.until((ExpectedCondition) driver -> (listOfDocuments.size() < 4));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        WebElement waitForElement = wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
        WebElement waitForElement = wait.until(ExpectedConditions.visibilityOf(element));
    }
}
