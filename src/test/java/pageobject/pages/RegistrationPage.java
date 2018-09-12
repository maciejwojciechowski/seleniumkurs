package pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    @FindBy(name = "firstName")
    private WebElement nameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "phone")
    private WebElement phoneField;

    @FindBy(name = "userName")
    private WebElement emailField;

    @FindBy(name = "address1")
    private WebElement adress1Field;

    @FindBy(name = "address2")
    private WebElement adress2Field;

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "state")
    private WebElement stateField;

    @FindBy(name = "postalCode")
    private WebElement postalCodeField;

    @FindBy(name = "country")
    private WebElement countryField;

    @FindBy(name = "email")
    private WebElement userNameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(name = "register")
    private WebElement submitButton;

    public void inputContactInformationForm(String name, String lastName,
                                            String phoneNumber, String email) {
        nameField.sendKeys(name);
        lastNameField.sendKeys(lastName);
        phoneField.sendKeys(phoneNumber);
        emailField.sendKeys(email);
    }

    public void inputMailingInformationForm(String address1, String address2,
                                            String city, String state, String postalCode, String country) {
        adress1Field.sendKeys(address1);
        adress2Field.sendKeys(address2);
        cityField.sendKeys(city);
        stateField.sendKeys(state);
        postalCodeField.sendKeys(postalCode);
        new Select(countryField).selectByVisibleText(country);
    }

    public void inputUserInformationForm(String userName, String password,
                                         String confirmPassword) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }
}
