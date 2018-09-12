package ddt;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomePage;
import pageobject.pages.LoginPage;
import pageobject.pages.RegistrationConfirmationPage;
import pageobject.pages.RegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.junit.Assert.assertTrue;

public class RegistrationWithoutDDT {

    private static final String PAGE_URL = "http://newtours.demoaut.com";

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
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationConfirmationPage = PageFactory.initElements(driver, RegistrationConfirmationPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void registerNewUserTest_1() {
        String userName = "jan12345";
        String password = "pass12345";
        String firstName = "Jan";
        String lastName = "Nowak";
        String phoneNumber = "505505505";
        String email = "jannowak@gmail.com";
        String address1 = "ul. Grunwaldzka 452";
        String address2 = "Mieszkanie nr 12";
        String city = "Gdansk";
        String state = "Pomorskie";
        String zipCode = "12345";
        String country = "POLAND";

        homePage.clickOnRegisterLink();
        registrationPage.inputContactInformationForm(firstName, lastName,
                phoneNumber, email);
        registrationPage.inputMailingInformationForm(address1, address2, city,
                state, zipCode, country);
        registrationPage.inputUserInformationForm(userName, password, password);
        registrationPage.clickOnSubmitButton();
        registrationConfirmationPage.clickOnSignInLink();
        loginPage.userLogin(userName, password);

        assertTrue("User is not logged in.", homePage.isUserIsLoggedIn());
    }

    @Test
    public void registerNewUserTest_2() {
        String userName = "fdskfsd";
        String password = "dd334ff";
        String firstName = "John";
        String lastName = "Snow";
        String phoneNumber = "999888777";
        String email = "snow@gmail.com";
        String address1 = "ul. Grunwaldzka 452";
        String address2 = "Mieszkanie nr 12";
        String city = "Gdansk";
        String state = "Pomorskie";
        String zipCode = "12345";
        String country = "POLAND";

        homePage.clickOnRegisterLink();
        registrationPage.inputContactInformationForm(firstName, lastName,
                phoneNumber, email);
        registrationPage.inputMailingInformationForm(address1, address2, city,
                state, zipCode, country);
        registrationPage.inputUserInformationForm(userName, password, password);
        registrationPage.clickOnSubmitButton();
        registrationConfirmationPage.clickOnSignInLink();
        loginPage.userLogin(userName, password);

        assertTrue("User is not logged in.", homePage.isUserIsLoggedIn());
    }

    @Test
    public void registerNewUserTest_3() {
        String userName = "jfkdsdfas";
        String password = "fafsasfas";
        String firstName = "Michal";
        String lastName = "Nowak";
        String phoneNumber = "333444555";
        String email = "mich44@gmail.com";
        String address1 = "ul. Pomorska 22";
        String address2 = "Mieszkanie nr 4";
        String city = "Gdansk";
        String state = "Pomorskie";
        String zipCode = "12345";
        String country = "POLAND";

        homePage.clickOnRegisterLink();
        registrationPage.inputContactInformationForm(firstName, lastName,
                phoneNumber, email);
        registrationPage.inputMailingInformationForm(address1, address2, city,
                state, zipCode, country);
        registrationPage.inputUserInformationForm(userName, password, password);
        registrationPage.clickOnSubmitButton();
        registrationConfirmationPage.clickOnSignInLink();
        loginPage.userLogin(userName, password);

        assertTrue("User is not logged in.", homePage.isUserIsLoggedIn());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
