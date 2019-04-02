package client;

/**
 * Simple user object which stores the information needed for a user
 * @author er205
 */
public class User {
    public String name;
    public String email;
    public String city;

    /**
     * user Constructor
     * @param name of the user
     * @param email of the user
     * @param city of the user
     */
    public User(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;

    }
}
