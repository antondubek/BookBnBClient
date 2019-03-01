import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel loginPane;
    private JButton btnLogin;
    private JButton btnQuit;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private boolean succeeded = false;

    public Login(Frame parent) {

        super(parent, "BookBnB", true);
        setContentPane(loginPane);
        setModal(true);
        getRootPane().setDefaultButton(btnLogin);

        setupListeners();

        //pack();
        setSize(300,200);
        setResizable(false);
        setLocationRelativeTo(parent);

    }

    private void setupListeners(){
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        loginPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here

        boolean isCorrect = Controller.authenticate(getUsername(), getPassword());

        if(isCorrect){
            JOptionPane.showMessageDialog(Login.this, "Login Successful");
            succeeded = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(Login.this, "Username or Password incorrect \nPlease try again!");
            txtUsername.setText("");
            txtPassword.setText("");
            succeeded = false;
        }
    }

    private void onCancel() {
        // add your code here if necessary
        System.exit(0);
    }

    private String getUsername(){
        return txtUsername.getText().trim();
    }

    private String getPassword(){
        return new String(txtPassword.getPassword());
    }

    protected boolean isSucceeded(){
        return succeeded;
    }

}
