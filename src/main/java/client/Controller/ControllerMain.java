package client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 *
 * @author acm35
 */
public class ControllerMain {

    //private static String address = "http://antondubek-bookbnb.herokuapp.com";
//    private static String address = "http://localhost:8080";
    //private static String address = "http://138.251.30.68:8080";
    private static String address = "http://138.251.29.123:8080";


    public static boolean isAvailable;
    public static String name;
    public static String email;
    public static String city;
    public static boolean loggedIn = false;

    private static CloseableHttpClient client;

    /**
     * Checks whether the server is reachable Sends a simple get request to the
     * server and returns true if it receives a correct response.
     *
     * @return True (Server is contactable) or False (it is not)
     */
    public static boolean getServerStatus() {

        String website = address + "/alive";

        URL url = null;
        try {

            url = new URL(website);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                isAvailable = true;
                return true;
            } else {
                isAvailable = false;
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        isAvailable = false;
        return false;

    }

    /**
     * Makes a post request to the server with the data.
     *
     * @param directory Directory of the URL to post to ie /login
     * @param data Data to send, normally an email
     * @return Returns True (HTTP OK received) False (HTTP Error)
     */
    public static boolean sendPostGetResponse(String directory, JSONObject data) {

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
     *
     * @param directory Directory of the URL to post to ie /login
     * @param data Data to send, normally register data
     * @return Returns a string of the post response, normally in a JSON format.
     */
    public static String sendPostGetData(String directory, JSONObject data) {

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
     * Actually connects to the server and deals with the POST request. The
     * connection has a configurable timeout and is designed to send JSON items.
     *
     * @param directory Directory of the URL to post to ie /login
     * @param data Data to send
     * @return The HttpResponse which can get the content or response codes
     * from.
     */
    public static HttpResponse sendPOSTRequest(String directory, JSONObject data) {

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
     *
     * @param directory Path of the data that you want ie /book
     * @return String of the data received from server. Will most likely be JSON
     * formatted.
     */
    public static String sendGetRequest(String directory) {

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

                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();

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
