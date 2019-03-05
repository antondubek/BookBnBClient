

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

public class Controller {

    private static String address = "http://138.251.29.36:8080";
    public static String email;

    public static boolean authenticate(String email, String password){

        //TODO Remove this when finished
        if(email.equals("admin")){
            return true;
        }

        JSONObject data = new JSONObject();

        data.put("email", email);
        data.put("password", password);

        return sendPOSTRequest("/login", data);

    }

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


        return sendPOSTRequest("/register", data);

    }

    public static boolean addBook(String ISBN, String author, String title, String edition){

        JSONObject data = new JSONObject();
        data.put("command", "add");
        data.put("ISBN", ISBN);
        data.put("author", author);
        data.put("title", title);
        data.put("edition", edition);

        return sendPOSTRequest("/book", data);
    }


    private static boolean sendPOSTRequest(String directory, JSONObject data){

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

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                client.close();
                return true;
            } else {
                client.close();
                return false;
            }

//            System.out.println(response);
//            System.out.println(EntityUtils.toString(response.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;


    }

    public static void getDataTest(){

        try {
            URL url = new URL("http://138.251.29.36:8080" +
                    "" +
                    "HTT/login");

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

                JSONObject json = new JSONObject(response.toString());
                System.out.println("ID = " + json.get("id").toString());
                System.out.println("content = " + json.getString("content"));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
