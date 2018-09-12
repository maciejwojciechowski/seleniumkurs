package ddt;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomePage;
import pageobject.pages.LoginPage;
import pageobject.pages.RegistrationConfirmationPage;
import pageobject.pages.RegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.junit.Assert.assertTrue;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "src/test/resources/registration.xml" }, loaderType = LoaderType.XML, writeData = false)
public class RegistrationDDTWithEasyTest {

    private static final String pageURL = "http://newtours.demoaut.com";

    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX_GECKO).getDriver();
        driver.manage().window().maximize();

        homePage = PageFactory.initElements(driver, HomePage.class);
        registrationPage = PageFactory.initElements(driver,
                RegistrationPage.class);
        registrationConfirmationPage = PageFactory.initElements(driver,
                RegistrationConfirmationPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        driver.get(pageURL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registerUserTest(@Param(name = "userName") String userName,
                                 @Param(name = "password") String password,
                                 @Param(name = "firstName") String firstName,
                                 @Param(name = "lastName") String lastName,
                                 @Param(name = "phoneNumber") String phoneNumber,
                                 @Param(name = "email") String email,
                                 @Param(name = "address1") String address1,
                                 @Param(name = "address2") String address2,
                                 @Param(name = "city") String city,
                                 @Param(name = "state") String state,
                                 @Param(name = "zipCode") String zipCode,
                                 @Param(name = "country") String country) {

        homePage.clickOnRegisterLink();
        registrationPage.inputContactInformationForm(firstName, lastName,
                phoneNumber, email);
        registrationPage.inputMailingInformationForm(address1, address2, city,
                state, zipCode, country);
        registrationPage.inputUserInformationForm(userName, password, password);
        registrationPage.clickOnSubmitButton();
        registrationConfirmationPage.clickOnSignInLink();
        loginPage.userLogin(userName, password);
        assertTrue(homePage.isUserIsLoggedIn());
    }
}
