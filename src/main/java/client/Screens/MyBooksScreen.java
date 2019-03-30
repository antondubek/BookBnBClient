/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Screens;

import client.Book;
import client.Controller;
import client.Dialogs.AddBookDialog;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amakepeace
 */
public class MyBooksScreen extends javax.swing.JPanel {

    private ArrayList<Book> userBooks;
    
    /**
     * Creates new form MyBooksScreen
     */
    public MyBooksScreen() {
        initComponents();
        populateTable();
    }
    
    /**
     * Sets the modelf for the browse books table and the user books table. This will also populate the tables
     * with data collected from the server via the controller.
     * Also adds a popup menu and listener to the browse books table which allows the user to request a book by right
     * clicking on an entry in the table.
     */
    public void populateTable() {

        if(Controller.loggedIn){
            userBooks = Controller.getUserBooks();
        } else {
            userBooks = new ArrayList<>();
        }

        TableModelUser userBooksTableModel = new TableModelUser(userBooks);

        yourBooksTable.setModel(userBooksTableModel);
        yourBooksTable.setAutoCreateRowSorter(true);

    }
    
    /**
     * Table model class used to define how the table should look and populates it with books.
     */
    class TableModelUser extends AbstractTableModel {

        private ArrayList<Book> books;
        String headers[] = new String[]{"Title", "Author", "ISBN", "Available"};

        public TableModelUser(ArrayList<Book> books) {
            this.books = books;
        }

        @Override
        public int getRowCount() {
            return books.size();
        }

        @Override
        public int getColumnCount() {
            return headers.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            Book book = books.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return book.getTitle();
                case 1:
                    return book.getAuthor();
                case 2:
                    return book.getISBN();
                case 3:
                    return book.getAvailability();
                default:
                    return "";
            }

        }

        @Override
        public String getColumnName(int column) {
            return headers[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex == 3){
                return Boolean.class;
            } else {
                return String.class;
            }
        }

        //Removed the isCell editable functionality so that user cannot check or uncheck the box until
        //back end functionality has been implemented.
//        @Override
//        public boolean isCellEditable(int rowIndex, int columnIndex) {
//            return columnIndex == 3;
//        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            if(aValue instanceof Boolean && columnIndex == 3){
                books.get(rowIndex).setAvailability((Boolean) aValue);
                //client.Controller.updateBookAvailability();
            }
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        yourBooksTable = new javax.swing.JTable();
        addBookBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 204, 255));
        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MyBooks");

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        yourBooksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(yourBooksTable);

        addBookBtn.setBackground(new java.awt.Color(255, 255, 255));
        addBookBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 24)); // NOI18N
        addBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBookBtn.setText("Add Book");
        addBookBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(addBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookBtnActionPerformed
        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        AddBookDialog addBookDialog = new AddBookDialog(topFrame, true);
        addBookDialog.setVisible(true);
        populateTable();
    }//GEN-LAST:event_addBookBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable yourBooksTable;
    // End of variables declaration//GEN-END:variables
}
