/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Dialogs;

import client.Controller.ControllerBook;
import client.Controller.ControllerUser;
import client.User;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * Class which allows the user to add a review, used for both books and users.
 */
public class AddReviewDialog extends javax.swing.JDialog {
    String name;
    String ISBN;
    String email;
    boolean userMode = false;
    
    /**
     * Creates new form AddReviewDialog
     */
    public AddReviewDialog(java.awt.Frame parent, boolean modal, String name, String ISBN) {
        super(parent, modal);
        this.name = name;
        this.ISBN = ISBN;
        initComponents();
       
        nameLabel.setText(name);
    }
    /**
     * Creates new form AddReviewDialog
     */
    public AddReviewDialog(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        this.name = user.name;
        this.email = user.email;
        this.userMode = true;
        initComponents();
       
        nameLabel.setText(name);
    }
    
    /**
     * Listens when the user is typing in the box
     */
    private void onKeyTyped() {
        int length = reviewText.getText().length();
        if (length > 500) {
            characterLabel.setText(length + "/500 Please reduce characters");
        } else {
            characterLabel.setText(length + "/500");
        }
        
    }
    
    /**
     * Gets called when user clicks submit
     */
    private void onSubmit(){
        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        
        String review = reviewText.getText();
        String rating = (String)ratingComboBox.getSelectedItem();
        
        if(review.length() > 500){
            JOptionPane.showMessageDialog(topFrame, "Please make review less than 500 characters");
            return;
        }
        
        boolean response;
        if(userMode){
            response = ControllerUser.setUserRating(email, review, rating);
        } else {
            response = ControllerBook.setBookRating(ISBN, review, rating);
        }
        
        
        
        if(response){
            JOptionPane.showMessageDialog(topFrame, "Review Sent");
        } else {
            JOptionPane.showMessageDialog(topFrame, "Review Failed");
        }
        
        dispose();
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reviewText = new javax.swing.JTextArea();
        characterLabel = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        submit = new javax.swing.JButton();
        ratingComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        nameLabel.setText("jLabel1");

        reviewText.setColumns(20);
        reviewText.setLineWrap(true);
        reviewText.setRows(5);
        reviewText.setText("Enter Review Here.\t\t\tRate here ^");
        reviewText.setWrapStyleWord(true);
        reviewText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reviewTextKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(reviewText);

        characterLabel.setText("0/500");

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        ratingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ratingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(characterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel)
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(ratingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(characterLabel)
                    .addComponent(submit)
                    .addComponent(cancel))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reviewTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reviewTextKeyTyped
        onKeyTyped();
    }//GEN-LAST:event_reviewTextKeyTyped

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        onSubmit();
    }//GEN-LAST:event_submitActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_cancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JLabel characterLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> ratingComboBox;
    private javax.swing.JTextArea reviewText;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
