/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author amakepeace
 */
public class NavBar extends javax.swing.JPanel {

    CardLayout cardLayout;
    JPanel cards;
    /**
     * Creates new form NavBar
     */
    public NavBar(CardLayout cardLayout, JPanel cards) {
        this.cardLayout = cardLayout;
        this.cards = cards;
        initComponents();
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
        welcomeBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 204, 255));

        profileBtn.setBackground(new java.awt.Color(0, 204, 255));
        profileBtn.setText("Profile");
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        welcomeBtn.setText("Welcome");
        welcomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welcomeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(welcomeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welcomeBtn)
                    .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void welcomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welcomeBtnActionPerformed
        cardLayout.show(cards, "WELCOME");
    }//GEN-LAST:event_welcomeBtnActionPerformed

    
    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        cardLayout.show(cards, "PROFILE");
    }//GEN-LAST:event_profileBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton welcomeBtn;
    // End of variables declaration//GEN-END:variables
}
