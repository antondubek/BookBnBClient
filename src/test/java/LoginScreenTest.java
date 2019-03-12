import static org.junit.Assert.*;
import org.junit.Test;

import client.LoginScreen;

public class LoginScreenTest {

    private static final String[] VALID_EMAILS = {"email@domain.com", "firstname.lastname@domain.com", "email@subdomain.domain.com",
            "firstname+lastname@domain.com", "email@[123.123.123.123]", "\"email\"@domain.com",
            "1234567890@domain.com", "email@domain-one.com", "_______@domain.com", "email@domain.name", "email@domain.co.jp", "firstname-lastname@domain.com" };

    private static final String[] INVALID_EMAILS = { "plainaddress", "#@%^%#$@#$@#.com",  "@domain.com ",
            "Joe Smith <email@domain.com>", "email.domain.com", "email@domain@domain.com",
            ".email@domain.com", "email.@domain.com", "email..email@domain.com", "email@domain.com (Joe Smith)", "email@domain", "email@-domain.com",
            "email@domain.web", "email@111.222.333.44444", "email@domain..com" };

    private LoginScreen loginScreen = new LoginScreen();

    @Test
    public void checkRegisterInfo() {

        assertTrue(this.loginScreen.checkRegisterInfo("Test", "test@Emailgmail.com", "London"));
        assertFalse(this.loginScreen.checkRegisterInfo("", "", ""));

    }

    @Test
    public void validateEmail() {

        for (String validEmail : VALID_EMAILS) {
            assertTrue(this.loginScreen.validateEmail(validEmail));
        }

        for (String invalidEmail : INVALID_EMAILS) {
            assertFalse(this.loginScreen.validateEmail(invalidEmail));
        }

    }
}
