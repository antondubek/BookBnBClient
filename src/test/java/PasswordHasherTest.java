import static org.junit.Assert.*;
import org.junit.Test;
import client.PasswordHasher;

public class PasswordHasherTest {

    private String password = "testingthepasswordhasher123";

    PasswordHasher hasher = new PasswordHasher();

    @Test
    public void hashPassword() {
        String hashedPassword = hasher.hashPassword(password);
        assertEquals(this.hasher.hashPassword(password), hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    @Test
    public void samePassword() {
        String hashedPassword1 = hasher.hashPassword(password);
        String hashedPassword2 = hasher.hashPassword(password);

        assertEquals(hashedPassword1, hashedPassword2);
    }
}
