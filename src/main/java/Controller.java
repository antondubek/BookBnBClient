

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

public class Controller {

    public static String user;

    public static boolean authenticate(String username, String password){

        if(username.equals("anthony") && password.equals("password")) {
            user = username;
            return true;
        }

        return false;

    }

    public static boolean register(String name, String email, String password, String city){

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("City: " + city);



        return false;
    }

    public static void getDataTest(){

        try {
            URL url = new URL("http://138.251.29.36:8080/login");

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

    public static void postDataTest(){

        JSONObject data = new JSONObject();

        data.put("name", "Rick Sanchez");
        data.put("email", "rick@pickled.com");
        data.put("password", "12345");
        data.put("city", "hull");

        String url = "http://138.251.29.36:8080/login";

        CloseableHttpClient client = HttpClients.createDefault();

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
            System.out.println(response);
            System.out.println(EntityUtils.toString(response.getEntity()));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
