package client;

import static org.junit.Assert.*;
import org.junit.Test;
import client.PasswordHasher;

/**
 * Testing that the Password Hashing function works correctly. Also testing that the same two
 * values generate the same hash digest.
 */

public class PasswordHasherTest {

    private String password = "testingthepasswordhasher123";

    @Test
    public void hashPassword() {
        String hashedPassword = PasswordHasher.hashPassword(password);
        assertEquals(PasswordHasher.hashPassword(password), hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    @Test
    public void samePassword() {
        String hashedPassword1 = PasswordHasher.hashPassword(password);
        String hashedPassword2 = PasswordHasher.hashPassword(password);

        assertEquals(hashedPassword1, hashedPassword2);
    }
}
