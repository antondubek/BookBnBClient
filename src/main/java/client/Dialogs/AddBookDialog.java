
package client.Dialogs;

import client.Controller;
import client.ISBNLookUp;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author amakepeace
 */
public class AddBookDialog extends javax.swing.JDialog {

    /**
     * Constructor
     * @param parent Parent Frame of the dialog, only used to center the box on top of the parent frame.
     */
    public AddBookDialog(java.awt.Frame parent, boolean modal) {
        super(parent, "Add New Book", modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
   

    /**
     * Method executed when the OK button is pressed.
     * Reads in the data and sends it to the controller for sending to the server.
     * Then disposes itself or gives an error and asks the user to try again.
     */
    private void onOK() {

        String ISBN = ISBNTxt.getText().trim();
        String author = authorTxt.getText().trim();
        String title = titleTxt.getText().trim();
        String edition = editionTxt.getText().trim();
        
        if(!checkBookInput(ISBN, author, title, edition)){
            return;
        }

        boolean addedCorrectly = Controller.addBook(ISBN, author, title, edition);

        System.out.println(addedCorrectly);

        if (addedCorrectly) {
            dispose();
        } else {
            errorTxt.setText("Error occurred, please check values or try later");
            pack();
        }
    }
    
    public void onLookUp() throws Exception{
        String ISBN = isbnGoogle.getText().trim();
        String[] details = ISBNLookUp.searchBook(ISBN);
        if (!details[0].equals("NO MATCHES FOUND")) {
        
            Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            BookDetails bookDetails = new BookDetails(topFrame, true, details, ISBN);
            bookDetails.setVisible(true);
            
        } else {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Book not found, you can add the book manually ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("NOT FOUND");
        }
        
        
    }

    /**
     * Method called when the Cancel button is pressed.
     * Simply closes the dialog box.
     */
    public void onCancel() {
        dispose();
    }
    
    /**
     * Checks that the input text for adding a book is correct by checking for
     * null, empty and non alphanumeric characters. Will set error message if
     * needed to something relevant.
     * @param ISBN ISBN input text
     * @param author Author input text
     * @param title Title input text
     * @param edition Edition input text
     * @return True, everything passed and is okay, False there is an error.
     */
    public boolean checkBookInput(String ISBN, String author, String title, 
            String edition) {
        if (ISBN == null || author == null || title == null || edition == null) {
            errorTxt.setText("Error: null values are not permitted");
            return false;
        }

        if (ISBN.isEmpty() || author.isEmpty() || title.isEmpty()) {
            errorTxt.setText("Error: Please ensure you have filled in mandatory fields");
            return false;
        }

        //TODO: Add in further validation of ISBN / check if this is correct
        if(!ISBN.matches("[0-9]+")) {
            errorTxt.setText("Error: Please insert the ISBN as numbers only");
            return false;
        }

        
        if (!author.matches("[a-zA-Z0-9 ]+") || !title.matches("[a-zA-Z0-9 ]+") 
                || !title.matches("[a-zA-Z0-9 ]+")) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buttonOK = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        ISBNTxt = new javax.swing.JTextField();
        authorTxt = new javax.swing.JTextField();
        titleTxt = new javax.swing.JTextField();
        editionTxt = new javax.swing.JTextField();
        errorTxt = new javax.swing.JLabel();
        isbnGoogle = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lookUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Please input the details of the book you would like to add");

        jLabel2.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("Add Book");

        jLabel3.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Labels marked with * are mandatory");

        jLabel4.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("ISBN*");

        jLabel5.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Author*");

        jLabel6.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Title*");

        jLabel7.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Edition");

        buttonOK.setBackground(new java.awt.Color(0, 204, 255));
        buttonOK.setFont(new java.awt.Font("Lantinghei SC", 1, 13)); // NOI18N
        buttonOK.setForeground(new java.awt.Color(102, 102, 102));
        buttonOK.setText("Add");
        buttonOK.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        buttonCancel.setBackground(new java.awt.Color(0, 204, 255));
        buttonCancel.setFont(new java.awt.Font("Lantinghei SC", 1, 13)); // NOI18N
        buttonCancel.setForeground(new java.awt.Color(102, 102, 102));
        buttonCancel.setText("Cancel");
        buttonCancel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        titleTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTxtActionPerformed(evt);
            }
        });

        errorTxt.setFont(new java.awt.Font("Lantinghei SC", 0, 13)); // NOI18N
        errorTxt.setForeground(new java.awt.Color(255, 0, 0));
        errorTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Lantinghei SC", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Insert ISBN and look book up on Google");

        lookUp.setBackground(new java.awt.Color(0, 204, 255));
        lookUp.setFont(new java.awt.Font("Lantinghei SC", 1, 13)); // NOI18N
        lookUp.setForeground(new java.awt.Color(102, 102, 102));
        lookUp.setText("Look Up");
        lookUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
        lookUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(errorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ISBNTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(authorTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(isbnGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lookUp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(26, 26, 26))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(338, 338, 338))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(isbnGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(lookUp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ISBNTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(authorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(titleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(editionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorTxt)
                .addGap(18, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        onOK();
    }//GEN-LAST:event_buttonOKActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        onCancel();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void titleTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTxtActionPerformed

    private void lookUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookUpActionPerformed
        try {
            onLookUp();
        } catch (Exception ex) {
            Logger.getLogger(AddBookDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lookUpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ISBNTxt;
    private javax.swing.JTextField authorTxt;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOK;
    private javax.swing.JTextField editionTxt;
    private javax.swing.JLabel errorTxt;
    private javax.swing.JTextField isbnGoogle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lookUp;
    private javax.swing.JTextField titleTxt;
    // End of variables declaration//GEN-END:variables
}
