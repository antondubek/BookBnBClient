
import client.MainLayout;
import client.Screens.LoginRegisterScreen;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Testing the Login Screen. Checking the email validation by feeding in valid
 * and invalid emails. Also checking that the registration fields don't accept
 * invalid inputs.
 */
public class LoginRegisterScreenTest {

    private static final String[] VALID_EMAILS = {"email@domain.com", "firstname.lastname@domain.com", "email@subdomain.domain.com",
        "firstname+lastname@domain.com", "email@[123.123.123.123]", "\"email\"@domain.com",
        "1234567890@domain.com", "email@domain-one.com", "_______@domain.com", "email@domain.name", "email@domain.co.jp", "firstname-lastname@domain.com"};

    private static final String[] INVALID_EMAILS = {"plainaddress", "#@%^%#$@#$@#.com", "@domain.com ",
        "Joe Smith <email@domain.com>", "email.domain.com", "email@domain@domain.com",
        ".email@domain.com", "email.@domain.com", "email..email@domain.com", "email@domain.com (Joe Smith)", "email@domain", "email@-domain.com",
        "email@domain.web", "email@111.222.333.44444", "email@domain..com"};

    private MainLayout mainLayout = new MainLayout();
    private LoginRegisterScreen loginScreen = new LoginRegisterScreen(mainLayout);

    @Test
    public void checkRegisterInfo() {

        assertTrue(this.loginScreen.checkRegisterInfo("Test", "test@Emailgmail.com", "London"));
        assertFalse(this.loginScreen.checkRegisterInfo("", "", ""));
        assertFalse(this.loginScreen.checkRegisterInfo("Joh%%n", "hello@google.com", "Bristol"));

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
