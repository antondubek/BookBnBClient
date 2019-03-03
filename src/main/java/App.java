import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton btnLogin;
    private JPanel mainPanel;
    private JLabel txtWelcome;
    private JButton getDataButton;
    private JButton postDataButton;
    private JButton loginBtn;
    private JButton registerBtn;

    private JFrame frame;

    public App() {
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
        getDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.getDataTest();
            }
        });

        postDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.postDataTest();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Login loginDialog = new Login(frame);
                loginDialog.setVisible(true);
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Register registerDialog = new Register(frame);
                registerDialog.setVisible(true);
            }
        });
    }

}
