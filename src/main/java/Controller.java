
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
            URL url = new URL("http://localhost:8080/greeting");

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

        data.put("first name", "Rick");
        data.put("last name", "Sanchez");
        data.put("email", "rick@pickled.com");
        data.put("city", "hull");

        try {
            URL url = new URL("http://localhost:8080/post");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            System.out.println("POST response code: " + responseCode);
            System.out.println("POST response message: " + connection.getResponseMessage());



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
