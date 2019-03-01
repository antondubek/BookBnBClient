import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton btnLogin;
    private JPanel mainPanel;
    private JLabel txtWelcome;
    private JPasswordField txtPassword;
    private JTextField txtUsername;

    public App() {
        JFrame frame = new JFrame("BookBnB");

        Login loginDialog = new Login(frame);
        loginDialog.setVisible(true);

        txtWelcome.setText("Welcome " + Controller.user + " to BookBnB!");

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600, 480);
        frame.setVisible(true);

        setupListeners();


    }

    private void setupListeners(){
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());
                JOptionPane.showMessageDialog(null, "Hello \n Your username is: " +
                username + "\n Your password is: " + password);
            }
        });
    }

}
