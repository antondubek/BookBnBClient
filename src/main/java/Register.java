import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JDialog {
    private JPanel registerPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JTextField txtCity;

    public Register(Frame parent) {

        super(parent, "BookBnB : Register", true);
        setContentPane(registerPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setupListeners();

        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void setupListeners(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
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
        registerPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String name = txtName.getText();
        String email = txtEmail.getText();
        //TODO Hash password
        String password = new String(txtPassword.getPassword());
        String city = txtCity.getText();

        //TODO User already registered functionality
        boolean response = Controller.register(name, email, password, city);

        if(response){
            dispose();
        } else {
            //TODO Show error
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
