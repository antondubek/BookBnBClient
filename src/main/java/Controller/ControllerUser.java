/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import client.BorrowedBook;
import client.Lender;
import client.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author acm35
 */
public class ControllerUser extends ControllerMain {

    /**
     * Authenticate method is called to validate the login credentials of the
     * user
     *
     * @param email Email of the user
     * @param password Password of the user, this should already be hashed
     * @return True (User credentials are correct) False (They are not)
     */
    public static boolean authenticate(String email, String password) {

        JSONObject data = new JSONObject();

        data.put("email", email);
        data.put("password", password);

        System.out.println("LOG: Sending authentication request");

        boolean successful = sendPostGetResponse("/login", data);

        if (successful) {
            System.out.println("LOG: Authentication successful");
        } else {
            System.out.println("LOG: Authentication denied");
        }

        return successful;

    }

    /**
     * Registers the user with the server
     *
     * @param name Name of the user
     * @param email Email of the user
     * @param password Hashed password of the user
     * @param city City of the user
     * @return True (Register was successful) False (Register unsuccessful)
     */
    public static boolean register(String name, String email, String password, String city) {

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("City: " + city);

        JSONObject data = new JSONObject();
        data.put("name", name);
        data.put("email", email);
        data.put("password", password);
        data.put("city", city);

        System.out.println("LOG: Sending registration request");

        boolean successful = sendPostGetResponse("/register", data);

        if (successful) {
            System.out.println("LOG: Registration successful");
        } else {
            System.out.println("LOG: Registration denied");
        }

        return successful;

    }

    /**
     * Gets the user profile once the user has logged in.
     *
     * @return boolean whether it successfully got the user info.
     */
    public static boolean getUser() {

        JSONObject data = new JSONObject();
        data.put("email", email);

        System.out.println("LOG: Requesting user data");

        JSONObject user = new JSONObject(sendPostGetData("/profile", data));

        name = user.getString("name");
        city = user.getString("city");

        return true;
    }

    /**
     * Gets the user profile searched
     *
     * @param email of the user to search
     * @return the user searched
     */
    public static User getUserSearch(String email) {

        JSONObject data = new JSONObject();
        data.put("email", email);

        System.out.println("LOG: Requesting user data");

        JSONObject userDetails = new JSONObject(sendPostGetData("/profile", data));
        name = userDetails.getString("name");
        city = userDetails.getString("city");
        email = userDetails.getString("email");
        User user = new User(name, email, city);

        return user;
    }

    /**
     * Returns the users followed by the current user logged in
     *
     * @return Array List of users
     */
    public static ArrayList<String> getFollowing() {
        JSONObject data = new JSONObject();
        data.put("email", email);

        String response = sendPostGetData("/follow/fetch", data);
        JSONArray userEmail = new JSONArray(response);

        ArrayList<String> users = new ArrayList<String>();

        for (int i = 0; i < userEmail.length(); i++) {
            JSONObject currentUser = userEmail.getJSONObject(i);
            users.add(currentUser.getString("email"));
        }
        return users;

    }

    /**
     * Sends a post request in order to follow a or unfollow a user
     *
     * @param friendEmail email of the user to follow or unfollow
     * @param directory Directory of the URL to post to (either /follow or
     * /follow/delete)
     * @return response true if the request was successful, false otherwise
     */
    public static Boolean followUnfollowUser(String friendEmail, String directory) {
        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("friendEmail", friendEmail);

        Boolean response = sendPostGetResponse(directory, data);
        return response;
    }

    /**
     * Checks if already following a user
     *
     * @param friendEmail email of the user to check
     * @return already following or not following ie true or false
     */
    public static Boolean isFollowing(String friendEmail) {
        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("friendEmail", friendEmail);

        String response = sendPostGetData("/follow/true", data);

        JSONObject booleanJsonObject = new JSONObject(response);

        return booleanJsonObject.getBoolean("userIsFollowed");
    }

    /**
     * Retrieves all of the users that you can lend that specific book from
     *
     * @ISBN ISBN of the book you want to check for lenders
     * @return An arraylist of lender objects
     *
     */
    public static ArrayList<Lender> getLenders(String ISBN) {

        String response = sendGetRequest("/book/lenders?ISBN=" + ISBN);

        System.out.println("LOG: Retrieving lenders for ISBN = " + ISBN);

        System.out.println(response);

        if (response.length() == 0) {
            return new ArrayList<>();
        }

        JSONArray bookLenders = new JSONArray(response.toString());

        ArrayList<Lender> lenders = new ArrayList<Lender>();
        for (int i = 0; i < bookLenders.length(); i++) {
            JSONObject currentLender = bookLenders.getJSONObject(i);

            lenders.add(new Lender(currentLender.getString("name"), currentLender.getString("loanLength"),
                    currentLender.getString("city"), currentLender.getString("id"), currentLender.getString("copyID")));
        }

        return lenders;

    }

    /**
     * Sends request to borrow a book
     *
     * @param email Email of the user who wants to borrow
     * @param lenderID The ID of the lender you wish to borrow from
     * @param copyID Copy ID of the book
     * @return True (Request successful) False (Request Failed)
     */
    public static boolean sendBorrowRequest(String email, String lenderID, String copyID) {

        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("lenderID", lenderID);
        data.put("copyID", copyID);

        return sendPostGetResponse("/request", data);

    }

    public static boolean processRequest(BorrowedBook book) {

        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("status", book.getStatus());
        data.put("copyID", book.getCopyID());
        data.put("requestNumber", book.getRequestNo());

        System.out.println(data);

        Date currentDate = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

        data.put("startDate", date);

        boolean response = sendPostGetResponse("/request/approveOrDenyRequest", data);

        return response;

    }

}
