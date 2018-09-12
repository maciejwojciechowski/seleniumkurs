package utils.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {

    private final WebDriver driver;

    public WebDriverProvider(WebDriverCreator creator) {
        driver = creator.create();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
