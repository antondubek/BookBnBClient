package client;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * client.Controller class which does all of the backend jobs, primarily sending and receiving data to and
 * from the server.
 */
public class Controller {

    private static String address = "http://antondubek-bookbnb.herokuapp.com";
    //private static String address = "http://localhost:8080";
    public static String name;
    public static String email;
    public static String city;
    public static boolean loggedIn = false;

    private static CloseableHttpClient client;


    /**
     * Authenticate method is called to validate the login credentials of the user
     * @param email Email of the user
     * @param password Password of the user, this should already be hashed
     * @return True (User credentials are correct) False (They are not)
     */
    public static boolean authenticate(String email, String password){

        JSONObject data = new JSONObject();

        data.put("email", email);
        data.put("password", password);
        
        System.out.println("LOG: Sending authentication request");

        boolean successful = sendPostGetResponse("/login", data);
        
        if (successful){
            System.out.println("LOG: Authentication successful");
        } else {
            System.out.println("LOG: Authentication denied");
        }
        
        return successful;

    }

    /**
     * Registers the user with the server
     * @param name Name of the user
     * @param email Email of the user
     * @param password Hashed password of the user
     * @param city City of the user
     * @return True (Register was successful) False (Register unsuccessful)
     */
    public static boolean register(String name, String email, String password, String city){

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
        
        if (successful){
            System.out.println("LOG: Registration successful");
        } else {
            System.out.println("LOG: Registration denied");
        }
        
        return successful;

    }

    /**
     * Gets the user profile once the user has logged in.
     * @return boolean whether it successfully got the user info.
     */
    public static boolean getUser(){

        JSONObject data = new JSONObject();
        data.put("email", email);

        JSONObject user = new JSONObject(sendPostGetData("/profile", data));

        name = user.getString("name");
        city = user.getString("city");

        return true;
    }

    /**
     * Adds a book to the servers collection
     * @param ISBN ISBN of the book
     * @param author Author of the book
     * @param title Title of the book
     * @param edition Edition of the book
     * @return True (Successfully added) False (Addition not successful)
     */
    public static boolean addBook(String ISBN, String author, String title, String edition){

        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("ISBN", ISBN);
        data.put("author", author);
        data.put("title", title);
        data.put("edition", edition);

        return sendPostGetResponse("/profile/addBook", data);
    }

    /**
     * Gets the books belonging to the user
     * @return An arraylist of all the book objects which belong to the user
     */
    public static ArrayList<Book> getUserBooks(){

        JSONObject data = new JSONObject();
        data.put("email", email);

        String response = sendPostGetData("/profile/books", data);

        JSONArray userBooks = new JSONArray(response);

        System.out.println(response);

        ArrayList<Book> books = new ArrayList<Book>();
        for(int i = 0; i < userBooks.length(); i++){
            JSONObject currentBook = userBooks.getJSONObject(i);

            books.add(new Book(currentBook.getString("ISBN"), currentBook.getString("title")
                    , currentBook.getString("author"), currentBook.getBoolean("available")));

        }
        
        return books;
    }

    /**
     * Retrieve all of the browsable books of the system
     * @return Arraylist containing all the book objects
     */
    public static ArrayList<Book> getAllBooks(){

        String response = sendGetRequest("/book?command=all");

        JSONArray allBooks = new JSONArray(response.toString());

        System.out.println(response);

        ArrayList<Book> books = new ArrayList<Book>();
        for(int i = 0; i < allBooks.length(); i++){
            JSONObject currentBook = allBooks.getJSONObject(i);

            books.add(new Book(currentBook.getString("ISBN"), currentBook.getString("title")
                    , currentBook.getString("author"), false));
        }

        return books;

    }

    /**
     * Makes a post request to the server with the data.
     * @param directory Directory of the URL to post to ie /login
     * @param data Data to send, normally an email
     * @return Returns True (HTTP OK received) False (HTTP Error)
     */
    private static boolean sendPostGetResponse(String directory, JSONObject data){

        HttpResponse response = sendPOSTRequest(directory, data);

        boolean successful = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return successful;

    }

    /**
     * Makes a post request to the server with the data.
     * @param directory Directory of the URL to post to ie /login
     * @param data  Data to send, normally register data
     * @return Returns a string of the post response, normally in a JSON format.
     */
    private static String sendPostGetData(String directory, JSONObject data){

        HttpResponse response = sendPOSTRequest(directory, data);

        try {
            String content = new BasicResponseHandler().handleResponse(response);
            client.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }


    /**
     * Actually connects to the server and deals with the POST request. The connection
     * has a configurable timeout and is designed to send JSON items.
     * @param directory Directory of the URL to post to ie /login
     * @param data Data to send
     * @return The HttpResponse which can get the content or response codes from.
     */
    private static HttpResponse sendPOSTRequest(String directory, JSONObject data){

        String url = address + directory;


        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = null;
        try {
            entity = new StringEntity(data.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        HttpResponse response = null;
        try {
            response = client.execute(httpPost);

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }


    /**
     * Creates a connection with the server and sends a get request.
     * @param directory Path of the data that you want ie /book
     * @return String of the data received from server. Will most likely be JSON formatted.
     */
    private static String sendGetRequest(String directory){

        StringBuffer response = new StringBuffer();

        try {

            String website = address + directory;

            URL url = new URL(website);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            String readLine = null;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));


                while((readLine = in.readLine()) != null){
                    response.append(readLine);
                } in.close();

                return response.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
