package client.Screens;

import client.Controller;
import java.awt.*;

/**
 * Class that displays the welcome screen blurb to the user.
 */
public class WelcomeScreen extends javax.swing.JPanel {

    /**
     * Creates new form WelcomeScreen
     */
    public WelcomeScreen() {
        initComponents();
        checkServerStatus();
    }

    /**
     * Checks whether the server is available and set the status accordingly
     */
    public void checkServerStatus() {

        if (Controller.isAvailable) {
            statusBtn.setText("Available");
            statusBtn.setBackground(new Color(0,255,0));
            statusBtn.setForeground(new Color(255,255,255));
        } else {
            statusBtn.setText("Unavailable");
            statusBtn.setBackground(new Color(255,0,0));
            statusBtn.setForeground(new Color(255,255,255));
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

        title = new javax.swing.JLabel();
        blurb = new javax.swing.JLabel();
        blurb1 = new javax.swing.JLabel();
        statusTxt = new javax.swing.JLabel();
        statusBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 204, 255));
        setPreferredSize(new java.awt.Dimension(740, 335));

        title.setFont(new java.awt.Font("Lantinghei SC", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Welcome to  BookBnB");

        blurb.setFont(new java.awt.Font("Lantinghei SC", 0, 18)); // NOI18N
        blurb.setForeground(new java.awt.Color(255, 255, 255));
        blurb.setText("<html>BookBnB is a peer to peer book lending platform which allows users in a local area to publish and share books with one another.");

        blurb1.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        blurb1.setForeground(new java.awt.Color(255, 255, 255));
        blurb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blurb1.setText("Please register or login");

        statusTxt.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        statusTxt.setForeground(new java.awt.Color(255, 255, 255));
        statusTxt.setText("Service Status: ");

        statusBtn.setBackground(new java.awt.Color(255, 102, 102));
        statusBtn.setForeground(new java.awt.Color(255, 255, 255));
        statusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 134, Short.MAX_VALUE)
                .addComponent(blurb, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addComponent(blurb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(statusTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(title)
                .addGap(26, 26, 26)
                .addComponent(blurb, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blurb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusTxt)
                    .addComponent(statusBtn))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void statusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusBtnActionPerformed
        //Will refresh the status indicator
        Controller.getServerStatus();
        checkServerStatus();
    }//GEN-LAST:event_statusBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blurb;
    private javax.swing.JLabel blurb1;
    private javax.swing.JButton statusBtn;
    private javax.swing.JLabel statusTxt;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
