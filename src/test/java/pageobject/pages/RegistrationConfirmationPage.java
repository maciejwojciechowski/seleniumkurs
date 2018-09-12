package pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationConfirmationPage {

    @FindBy(linkText = "sign-in")
    private WebElement signInLink;

    public void clickOnSignInLink() {
        signInLink.click();
    }
}
