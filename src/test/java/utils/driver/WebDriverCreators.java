package utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class WebDriverCreators {

    public static final WebDriverCreator FIREFOX_GECKO = () -> {
        startGeckoDriverServer();
        return (WebDriver) new FirefoxDriver();
    };

    public static final WebDriverCreator CHROME = () -> {
        startChromeDriverServer();
        return (WebDriver) new ChromeDriver();
    };

    private static void startGeckoDriverServer() {
        System.setProperty("webdriver.firefox.marionette", Objects.requireNonNull(GeckoDriverExecutor.getProperDriverExecutable()));
    }

    private static void startChromeDriverServer() {
        System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(ChromeDriverExecutor.getProperDriverExecutable()));
    }

}

