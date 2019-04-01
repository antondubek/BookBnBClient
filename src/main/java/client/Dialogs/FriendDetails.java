/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Dialogs;

import client.Book;
import client.Controller;
import client.Screens.MyBooksScreen;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author er205
 */
public class FriendDetails extends javax.swing.JDialog {

    
    /**
     * 
     * @param parent
     * @param modal
     * @param user 
     */
    public FriendDetails(java.awt.Frame parent, boolean modal, client.User user) {
        super(parent, "Friend Profile", modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(parent);
        setUsersDetails(user);
        populateTable(user.email);
        
    }

    public void setUsersDetails(client.User user){
        name.setText(user.name + "'s Profile");
        email.setText("Email: "+user.email);
        city.setText("City: "+user.city);
    }
    
    /**
     * Sets the modelf for the browse books table and the user books table. This will also populate the tables
     * with data collected from the server via the controller.
     * Also adds a popup menu and listener to the browse books table which allows the user to request a book by right
     * clicking on an entry in the table.
     */
    public void populateTable(String email) {
        ArrayList<Book> userBooks;
        userBooks = Controller.getSearchedUserBooks(email);
        TableModelUser userBooksTableModel = new TableModelUser(userBooks);

        bookTable.setModel(userBooksTableModel);
        bookTable.setAutoCreateRowSorter(true);

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

        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        name.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        name.setForeground(new java.awt.Color(0, 204, 255));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Name");

        email.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("Email");

        city.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        city.setForeground(new java.awt.Color(102, 102, 102));
        city.setText("City");

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(bookTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email)
                            .addComponent(city))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name)
                .addGap(34, 34, 34)
                .addComponent(email)
                .addGap(18, 18, 18)
                .addComponent(city)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookTable;
    private javax.swing.JLabel city;
    private javax.swing.JLabel email;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
