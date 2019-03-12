package client;

import client.Controller;
import client.PasswordHasher;
import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login screen is the main screen that the user will see when launching the app. It will allow them to either
 * register with the service or login.
 */
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
    private JPanel RegisterButton;
    private JPanel LoginBtn;
    private JLabel errorTxt;

    private JFrame frame;

    /**
     * Constructor which creates the main frame of the app.
     */
    public LoginScreen() {
        frame = new JFrame("BookBnB");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setupListeners();
    }

    /**
     * Method to setup the listners for the login and register buttons.
     */
    private void setupListeners() {

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onLogin();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onRegister();
            }
        });
    }

    /**
     * Method called when the login button pressed. Will read in the values given, check the email and password
     * with the server. If the authentication is correct then will load the mainscreen.
     * <p>
     * Currently a bypass put in for an 'admin' user for during development.
     */
    private void onLogin() {
        //Reset the error text
        errorTxt.setText("");

        String email = loginEmailTxt.getText().trim().toLowerCase();

        boolean isAuthenticated = false;


        //Check the email is valid
        if (!validateEmail(email)) {
            return;
        }

        String password = PasswordHasher.hashPassword(new String(loginPasswordlTxt.getPassword()));

        isAuthenticated = Controller.authenticate(email, password);


        if (isAuthenticated) {
            Controller.email = email;
            new MainScreen(frame);
        } else {
            errorTxt.setText("Error: Unable to login, email or password not recognised");
        }
    }

    /**
     * Method called when the register button pressed. Will read in the data, check the data is valid, hash the password
     * and then send it to the controller for registering with the server. If registration correct then will load
     * the main screen.
     */
    private void onRegister() {
        //Reset the error text
        errorTxt.setText("");

        String name = registerNameTxt.getText().trim().toLowerCase();
        String email = registerEmailTxt.getText().trim().toLowerCase();
        String password = PasswordHasher.hashPassword(new String(registerPasswordTxt.getPassword()));
        String city = registerCityTxt.getText().trim();

        //Check what has been inputted is valid
        if (!checkRegisterInfo(name, email, city)) {
            return;
        }

        boolean isRegistered = Controller.register(name, email, password, city);

        if (isRegistered) {
            Controller.email = email;
            new MainScreen(frame);
        } else {
            errorTxt.setText("Error: Unable to register, please check details and try again");
        }
    }

    /**
     * Uses the apache email validator to check that the email given is of a valid format.
     *
     * @param email Email to check the format of.
     * @return True (Email is valid) False (Email is not)
     */
    public boolean validateEmail(String email) {

        EmailValidator validator = EmailValidator.getInstance();

        if (validator.isValid(email)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check the registration details of the person. Looks for null values, empty strings and non
     * alphanumeric characters. Will also validate the email using the method above.
     * Any issues encountered will set the error text with the corresponding issue.
     *
     * @param name  Name the user inputted
     * @param email email the user inputted
     * @param city  City the user inputted
     * @return true (everything validates successfully) false (something fails)
     */
    public boolean checkRegisterInfo(String name, String email, String city) {

        if (name == null || email == null || city == null) {
            errorTxt.setText("Error: null values are not permitted");
            return false;
        }

        if (name.isEmpty() || email.isEmpty() || city.isEmpty()) {
            errorTxt.setText("Error: Please ensure you have filled in all fields");
            return false;
        }

        if (!validateEmail(email)) {
            return false;
        }

//        if (!StringUtils.isAlphanumeric(name) || StringUtils.isAlphanumeric(city)) {
//            errorTxt.setText("Error: Please use alphanumeric characters only");
//            return false;
//        }

        return true;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        Heading = new JPanel();
        Heading.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(Heading, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Please login or register");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        Heading.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Welcome to BookBnb");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        Heading.add(label2, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        Heading.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        Heading.add(spacer2, gbc);
        Login = new JPanel();
        Login.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(Login, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Email");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Login.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        Login.add(label4, gbc);
        loginPasswordlTxt = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Login.add(loginPasswordlTxt, gbc);
        loginEmailTxt = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Login.add(loginEmailTxt, gbc);
        LoginBtn = new JPanel();
        LoginBtn.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(LoginBtn, gbc);
        loginBtn = new JButton();
        loginBtn.setText("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        LoginBtn.add(loginBtn, gbc);
        Register = new JPanel();
        Register.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(Register, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Name");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        Register.add(label5, gbc);
        registerNameTxt = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Register.add(registerNameTxt, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Email");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        Register.add(label6, gbc);
        registerEmailTxt = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Register.add(registerEmailTxt, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        Register.add(label7, gbc);
        registerPasswordTxt = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Register.add(registerPasswordTxt, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("City");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        Register.add(label8, gbc);
        registerCityTxt = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Register.add(registerCityTxt, gbc);
        RegisterButton = new JPanel();
        RegisterButton.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(RegisterButton, gbc);
        registerBtn = new JButton();
        registerBtn.setText("Register");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        RegisterButton.add(registerBtn, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 0, 0);
        mainPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 225, 0, 0);
        mainPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 225, 0, 0);
        mainPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer8, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel1, gbc);
        errorTxt = new JLabel();
        errorTxt.setForeground(new Color(-65536));
        errorTxt.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(errorTxt, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
