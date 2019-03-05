import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JPanel mainPanel;
    private JButton loginBtn;
    private JButton registerBtn;
    private JTextField loginEmailTxt;
    private JPasswordField loginPasswordlTxt;
    private JTextField registerNameTxt;
    private JTextField registerEmailTxt;
    private JPasswordField registerPasswordTxt;
    private JTextField registerCityTxt;
    private JPanel Login;
    private JPanel Register;
    private JPanel Heading;
    private JPanel Error;
    private JLabel errorTxt;

    private JFrame frame;

    public LoginScreen() {
        frame = new JFrame("BookBnB");

        //txtWelcome.setText("Welcome " + Controller.user + " to BookBnB!");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setupListeners();
    }

    private void setupListeners(){

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String email = loginEmailTxt.getText().trim();
                String password = PasswordHasher.hashPassword(new String(loginPasswordlTxt.getPassword()));

                boolean isAuthenticated = Controller.authenticate(email, password);

                if(isAuthenticated){
                    new MainScreen(frame);
                } else {
                    errorTxt.setText("Unable to login, email or password not recognised");
                }

            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name = registerNameTxt.getText().trim().toLowerCase();
                String email = registerEmailTxt.getText().trim().toLowerCase();
                String password = PasswordHasher.hashPassword(new String(registerPasswordTxt.getPassword()));
                String city = registerCityTxt.getText().trim();
                //TODO check that values are not empty and are alphanumeric

                boolean isRegistered = Controller.register(name, email, password, city);

                if(isRegistered){
                    //Move to new screen
                } else {
                    errorTxt.setText("Unable to register, please check details and try again");
                }
            }
        });
    }

}
