public class Controller {

    public static String user;

    public static boolean authenticate(String username, String password){

        if(username.equals("anthony") && password.equals("password")) {
            user = username;
            return true;
        }

        return false;

    }
}
