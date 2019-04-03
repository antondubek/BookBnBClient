
package client.Screens;

import client.Controller;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import client.Dialogs.FriendDetails;
import java.util.ArrayList;

/**
 * Profile screen
 * 
 */
public class ProfileScreen extends javax.swing.JPanel {

    /**
     * Creates new form ProfileScreen
     */
    public ProfileScreen() {
        initComponents();
        displayProfileDetails();
        displayFollows();
    }

    public void displayFollows(){
        ArrayList<String> emails = client.Controller.getFollows();
    }
    
    public void displayProfileDetails(){
        name.setText(client.Controller.name);
        email.setText(client.Controller.email);
        city.setText(client.Controller.city);
        //name.setVisible(true);
    }

    public void onSearch() {
        String email = searchBox.getText().trim();
        client.User friendUser = Controller.getUserSearch(email);
        if (friendUser.email.equals("")) {
            userNotFound.setText("User not found");
        } else {
            userNotFound.setText("");
            Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            FriendDetails friendDetails = new FriendDetails(topFrame, true, friendUser);
            friendDetails.setVisible(true);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        searchBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buttonOK = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        userNotFound = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Profile Page");

        jLabel2.setText("Search for new friends");

        buttonOK.setBackground(new java.awt.Color(0, 204, 255));
        buttonOK.setFont(new java.awt.Font("Lantinghei SC", 1, 13)); // NOI18N
        buttonOK.setForeground(new java.awt.Color(102, 102, 102));
        buttonOK.setText("Search");
        buttonOK.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 204, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setText("Followers");

        email.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("Email");

        city.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        city.setForeground(new java.awt.Color(102, 102, 102));
        city.setText("City");

        name.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(102, 102, 102));
        name.setText("Name");

        userNotFound.setForeground(new java.awt.Color(204, 0, 0));
        userNotFound.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNotFound.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email)
                    .addComponent(city)
                    .addComponent(name))
                .addGap(245, 245, 245)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(userNotFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(152, 152, 152))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonOK))
                                .addGap(8, 8, 8)
                                .addComponent(userNotFound)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(name)
                                .addGap(18, 18, 18)
                                .addComponent(email)
                                .addGap(18, 18, 18)
                                .addComponent(city)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        onSearch();
    }//GEN-LAST:event_buttonOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOK;
    private javax.swing.JLabel city;
    private javax.swing.JLabel email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel name;
    private javax.swing.JTextField searchBox;
    private javax.swing.JLabel userNotFound;
    // End of variables declaration//GEN-END:variables
}
