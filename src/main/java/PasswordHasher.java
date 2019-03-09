import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Simple class to hash a password
 */
public class PasswordHasher {

    /**
     * Hashes the password passed to it using SHA-1
     * @param password Password to be hashed
     * @return Hashed password
     */
    public static String hashPassword(String password) {
        String generatedPassword = null;

        try {

            byte[] salt = getSalt();

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //md.update(salt);
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest();
            generatedPassword = Base64.getEncoder().encodeToString(bytes);
            System.out.println(generatedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    /**
     * Method to generate a salt which is used in the hashing algorithm
     * @return The salt
     * @throws NoSuchAlgorithmException
     */
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}
