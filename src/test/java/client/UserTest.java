package client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User testUser;
    private String name = "Jimbo";
    private String email = "jim@bob.com";
    private String city = "Sydney";

    @Before
    public void Setup(){
        testUser = new User(name, email, city);
    }

    @Test
    public void testConstructor(){
        assertEquals(testUser.name, name);
        assertEquals(testUser.email, email);
        assertEquals(testUser.city, city);
    }
}