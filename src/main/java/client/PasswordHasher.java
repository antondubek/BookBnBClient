package client;

import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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

        String generatedPassword = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(password.getBytes("utf8"));
            generatedPassword = String.format("%040x", new BigInteger(1, md.digest()));
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println( generatedPassword );

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
