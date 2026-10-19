package za.ac.rosebank.registrationlogin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginTest {

    private static final String VALID_USERNAME = "kyl_1";
    private static final String VALID_PASSWORD = "Ch&&sec@ke99!";
    private static final String VALID_PHONE_NUMBER = "+27838968976";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    private Login createValidLogin() {
        return new Login(
                VALID_USERNAME,
                VALID_PASSWORD,
                VALID_PHONE_NUMBER,
                FIRST_NAME,
                LAST_NAME
        );
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = createValidLogin();

        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login(
                "kyle!!!!!!!",
                VALID_PASSWORD,
                VALID_PHONE_NUMBER,
                FIRST_NAME,
                LAST_NAME
        );

        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login login = createValidLogin();

        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login(
                VALID_USERNAME,
                "password",
                VALID_PHONE_NUMBER,
                FIRST_NAME,
                LAST_NAME
        );

        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = createValidLogin();

        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login(
                VALID_USERNAME,
                VALID_PASSWORD,
                "08966553",
                FIRST_NAME,
                LAST_NAME
        );

        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testRegistrationSuccessful() {
        Login login = createValidLogin();

        assertEquals(
                "User registered successfully.",
                login.registerUser()
        );
    }

    @Test
    public void testRegistrationUsernameIncorrect() {
        Login login = new Login(
                "kyle!!!!!!!",
                VALID_PASSWORD,
                VALID_PHONE_NUMBER,
                FIRST_NAME,
                LAST_NAME
        );

        assertEquals(
                "Username is not correctly formatted; please ensure that "
                        + "your username contains an underscore and is no more "
                        + "than five characters in length.",
                login.registerUser()
        );
    }

    @Test
    public void testRegistrationPasswordIncorrect() {
        Login login = new Login(
                VALID_USERNAME,
                "password",
                VALID_PHONE_NUMBER,
                FIRST_NAME,
                LAST_NAME
        );

        assertEquals(
                "Password is not correctly formatted; please ensure that "
                        + "the password contains at least eight characters, a "
                        + "capital letter, a number, and a special character.",
                login.registerUser()
        );
    }

    @Test
    public void testLoginSuccessful() {
        Login login = createValidLogin();

        assertTrue(login.loginUser(VALID_USERNAME, VALID_PASSWORD));
    }

    @Test
    public void testLoginFailsWithIncorrectUsername() {
        Login login = createValidLogin();

        assertFalse(login.loginUser("wrong_username", VALID_PASSWORD));
    }

    @Test
    public void testLoginFailsWithIncorrectPassword() {
        Login login = createValidLogin();

        assertFalse(login.loginUser(VALID_USERNAME, "wrong_password"));
    }

    @Test
    public void testSuccessfulLoginStatus() {
        Login login = createValidLogin();
        login.loginUser(VALID_USERNAME, VALID_PASSWORD);

        assertEquals(
                "Welcome John, Doe it is great to see you again.",
                login.returnLoginStatus()
        );
    }

    @Test
    public void testFailedLoginStatus() {
        Login login = createValidLogin();
        login.loginUser("wrong_username", "wrong_password");

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus()
        );
    }
}