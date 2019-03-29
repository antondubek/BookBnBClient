/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author amakepeace
 */
public class LoginRegisterDialog extends javax.swing.JDialog {

    /**
     * Creates new form LoginRegisterDialog
     */
    public LoginRegisterDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

        String password = PasswordHasher.hashPassword(new String(loginPasswordTxt.getPassword()));

        isAuthenticated = Controller.authenticate(email, password);


        if (isAuthenticated) {
            Controller.email = email;
            //new MainScreen(frame);
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
            //new MainScreen(frame);
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

        if (!name.matches("[a-zA-Z0-9]+") || !city.matches("[a-zA-Z0-9]+")) {
            errorTxt.setText("Error: Please use alphanumeric characters only");
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        registerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        registerPasswordTxt = new javax.swing.JPasswordField();
        registerNameTxt = new javax.swing.JTextField();
        registerEmailTxt = new javax.swing.JTextField();
        registerCityTxt = new javax.swing.JTextField();
        registerBtn = new javax.swing.JButton();
        errorTxt = new javax.swing.JLabel();
        loginPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        loginEmailTxt = new javax.swing.JTextField();
        loginPasswordTxt = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        errorTxt1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setText("Email");

        jLabel3.setText("Password");

        jLabel4.setText("City");

        registerPasswordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerPasswordTxtActionPerformed(evt);
            }
        });

        registerBtn.setText("Register");

        errorTxt.setForeground(new java.awt.Color(255, 0, 0));
        errorTxt.setText("Err");

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(registerNameTxt)
                            .addComponent(registerEmailTxt)
                            .addComponent(registerPasswordTxt)
                            .addComponent(registerCityTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(errorTxt)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(registerNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(registerEmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(registerPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(registerCityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Register", registerPanel);

        jLabel5.setText("Email");

        jLabel6.setText("Password");

        loginEmailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginEmailTxtActionPerformed(evt);
            }
        });

        loginBtn.setText("Login");

        errorTxt1.setForeground(new java.awt.Color(255, 0, 0));
        errorTxt1.setText("Err");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginEmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(errorTxt1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginEmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(loginPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorTxt1)
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Login", loginPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerPasswordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerPasswordTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerPasswordTxtActionPerformed

    private void loginEmailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginEmailTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginEmailTxtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginRegisterDialog dialog = new LoginRegisterDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorTxt;
    private javax.swing.JLabel errorTxt1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField loginEmailTxt;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField loginPasswordTxt;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField registerCityTxt;
    private javax.swing.JTextField registerEmailTxt;
    private javax.swing.JTextField registerNameTxt;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JPasswordField registerPasswordTxt;
    // End of variables declaration//GEN-END:variables
}
