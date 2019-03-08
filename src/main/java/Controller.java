

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
 * Controller class which does all of the backend jobs, primarily sending and receiving data to and
 * from the server.
 */
public class Controller {

    private static String address = "http://localhost:8080";
    public static String email;

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

        CloseableHttpResponse response = sendPOSTRequest("/loginfail", data);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        } else {
            return false;
        }

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


        CloseableHttpResponse response = sendPOSTRequest("/register", data);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        } else {
            return false;
        }

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
        data.put("command", "add");
        data.put("ISBN", ISBN);
        data.put("author", author);
        data.put("title", title);
        data.put("edition", edition);

        CloseableHttpResponse response = sendPOSTRequest("/book", data);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the books belonging to the user
     * @return An arraylist of all the book objects which belong to the user
     */
    public static ArrayList<Book> getUserBooks(){

        //Use either get or post to retrieve the data

        //Iterate through whatever you get back
            //Add it to an arraylist

        //Return the arraylist


        //Post request parsing to get data
        //System.out.println(response);
        //System.out.println(EntityUtils.toString(response.getEntity()));


        //For testing
        Book book1 = new Book("1234", "test user books", "hello world");
        Book book2 = new Book("4567", "JKRowling", "Harry Potter & the half blood prince");

        ArrayList<Book> books = new ArrayList<Book>();

        books.add(book1);
        books.add(book2);

        return books;
    }

    /**
     * Retrieve all of the browsable books of the system
     * @return Arraylist containing all the book objects
     */
    public static ArrayList<Book> getAllBooks(){

        //JSONObject response = sendGetRequest("/searchSpecificUser?command=all");

        //System.out.println(response);

        Book book1 = new Book("1234", "test all books", "hello world");
        Book book2 = new Book("4567", "JKRowling", "Harry Potter & the chamber of secrets");

        ArrayList<Book> books = new ArrayList<Book>();

        books.add(book1);
        books.add(book2);

        return books;

    }


    private static CloseableHttpResponse sendPOSTRequest(String directory, JSONObject data){

        String url = address + directory;


        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

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

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);

            System.out.println(response.toString());

            client.close();

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }


    public static JSONObject sendGetRequest(String directory){

        JSONObject json = new JSONObject();

        try {

            String website = address + directory;

            URL url = new URL(website);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            String readLine = null;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer response = new StringBuffer();

                while((readLine = in.readLine()) != null){
                    response.append(readLine);
                } in.close();

                System.out.println(response.toString());

                json = new JSONObject(response.toString());

                return json;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
