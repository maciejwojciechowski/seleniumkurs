package ddt;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomePage;
import pageobject.pages.LoginPage;
import pageobject.pages.RegistrationConfirmationPage;
import pageobject.pages.RegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class RegistrationDDTWithJUnit {

    private static final String PAGE_URL = "http://newtours.demoaut.com";

    private WebDriver driver;

    private HomePage homePage;
    private RegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private LoginPage loginPage;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /* Instead of String[] we can use Object[] or other type. */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new String[] {"jan12345", "pass12345", "Jan", "Nowak", "505505505", "jannowak@gmail.com", "ul. Grunwaldzka 452",
                        "Mieszkanie nr 12", "Gdansk", "Pomorskie", "12345", "POLAND"},
                new String[] {"John33", "fdser", "John", "Snow", "111222333", "john@gmail.com", "ul. Pomorska 222",
                        "Mieszkanie nr 33", "Gdansk", "Pomorskie", "12345", "POLAND"},
                new String[] {"mich2", "wert555", "Michal", "Wozniak", "303987345", "mich3@gmail.com", "ul. Piastowska 3",
                        "Mieszkanie nr 132", "Gdansk", "Pomorskie", "12345", "POLAND"});
    }

    public RegistrationDDTWithJUnit(String userName, String password, String firstName, String lastName, String phoneNumber,
                                    String email, String address1, String address2, String city, String state, String zipCode, String country) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

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
    public void registerNewUserTest() {
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
