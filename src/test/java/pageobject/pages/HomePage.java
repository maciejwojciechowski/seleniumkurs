package pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(linkText = "SIGN-ON")
    private WebElement signOnLinkElement;

    @FindBy(linkText = "SIGN-OFF")
    private WebElement signOffLinkElement;

    @FindBy(linkText = "REGISTER")
    private WebElement registerLinkElement;

    @FindBy(linkText = "Home")
    private WebElement homeLinkElement;

    @FindBy(name = "userName")
    private WebElement userNameFieldElement;

    @FindBy(name = "password")
    private WebElement passwordFieldElement;

    @FindBy(name = "login")
    private WebElement signInButton;

    public void clickOnSignOnLink() {
        signOnLinkElement.click();
    }

    public void clickOnRegisterLink() {
        registerLinkElement.click();
    }

    public void clickOnHomeLink() {
        homeLinkElement.click();
    }

    public void typeInUserName(String userName) {
        userNameFieldElement.sendKeys(userName);
    }

    public void typeInPassword(String password) {
        passwordFieldElement.sendKeys(password);
    }

    public void clickOnSignInButton() {
        signInButton.click();
    }

    public boolean isUserIsLoggedIn() {
        return signOffLinkElement.isDisplayed();
    }

}
