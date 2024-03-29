package client;

import client.Controller.ControllerMain;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * Navigation bar class allowing the user to change between the different
 * screens of the application.
 */
public class NavBar extends javax.swing.JPanel {

    MainLayout mainLayout;
    CardLayout cardLayout;
    JPanel cards;

    /**
     * NavBar constructor
     *
     * @param cardLayout The cardlayout of the parent frame.
     * @param cards The parent panel which the navbar controls
     */
    public NavBar(CardLayout cardLayout, JPanel cards, MainLayout mainLayout) {
        this.cardLayout = cardLayout;
        this.cards = cards;
        this.mainLayout = mainLayout;
        initComponents();
        loginUpdate();
    }

    /**
     * Login Update method will show or hide elements in the navbar depending on
     * whether the user is logged in or not.
     */
    public void loginUpdate() {

        boolean loggedIn = ControllerMain.loggedIn;

        profileBtn.setVisible(loggedIn);
        mybooksBtn.setVisible(loggedIn);
        loginRegisterBtn.setVisible(!loggedIn);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profileBtn = new javax.swing.JButton();
        mybooksBtn = new javax.swing.JButton();
        loginRegisterBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(700, 50));

        profileBtn.setBackground(new java.awt.Color(255, 255, 255));
        profileBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(102, 102, 102));
        profileBtn.setText("Profile");
        profileBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        mybooksBtn.setBackground(new java.awt.Color(255, 255, 255));
        mybooksBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        mybooksBtn.setForeground(new java.awt.Color(102, 102, 102));
        mybooksBtn.setText("myBooks");
        mybooksBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        mybooksBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mybooksBtnActionPerformed(evt);
            }
        });

        loginRegisterBtn.setBackground(new java.awt.Color(255, 255, 255));
        loginRegisterBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        loginRegisterBtn.setForeground(new java.awt.Color(102, 102, 102));
        loginRegisterBtn.setText("Register/Login");
        loginRegisterBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        loginRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginRegisterBtnActionPerformed(evt);
            }
        });

        browseBtn.setBackground(new java.awt.Color(255, 255, 255));
        browseBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        browseBtn.setForeground(new java.awt.Color(102, 102, 102));
        browseBtn.setText("Browse");
        browseBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        browseBtn.setPreferredSize(new java.awt.Dimension(69, 25));
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 255));
        jLabel1.setText("BookBnB");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mybooksBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(loginRegisterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mybooksBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(browseBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profileBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginRegisterBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        cardLayout.show(cards, "PROFILE");
        //client.Screens.ProfileScreen.displayFollows();
        MainLayout.profileCard.displayProfileDetails();
        MainLayout.profileCard.displayFollowing();

    }//GEN-LAST:event_profileBtnActionPerformed

    private void mybooksBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mybooksBtnActionPerformed
        mainLayout.setCurrentScreen("MYBOOKS");
    }//GEN-LAST:event_mybooksBtnActionPerformed

    private void loginRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginRegisterBtnActionPerformed
        cardLayout.show(cards, "LOGIN");
    }//GEN-LAST:event_loginRegisterBtnActionPerformed

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        mainLayout.setCurrentScreen("BROWSE");
    }//GEN-LAST:event_browseBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginRegisterBtn;
    private javax.swing.JButton mybooksBtn;
    private javax.swing.JButton profileBtn;
    // End of variables declaration//GEN-END:variables
}
