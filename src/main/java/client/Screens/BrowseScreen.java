/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Screens;

import client.Book;
import client.Controller;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amakepeace
 */
public class BrowseScreen extends javax.swing.JPanel implements ActionListener {

    private ArrayList<Book> allBooks;
    
    private JMenuItem menuItemRequest;
    private JPopupMenu popupMenu;
    
    /**
     * Creates new form BrowseScreen
     */
    public BrowseScreen() {
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

        allBooks = Controller.getAllBooks();

        TableModelAll allBooksTableModel = new TableModelAll(allBooks);

        browseBooksTable.setModel(allBooksTableModel);
        browseBooksTable.setAutoCreateRowSorter(true);

        popupMenu = new JPopupMenu();
        menuItemRequest = new JMenuItem("Request Book");
        popupMenu.add(menuItemRequest);
        menuItemRequest.addActionListener(this);

        browseBooksTable.setComponentPopupMenu(popupMenu);
        browseBooksTable.addMouseListener(new TableMouseListener(browseBooksTable));

    }
    
    /**
     * Method to setup the listeners for any buttons
     */
//    private void setupListeners() {
//        addBookBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AddBook addBookDialog = new AddBook(frame);
//                addBookDialog.setVisible(true);
//                populateTable();
//            }
//        });
//    }
    
    /**
     * Action listener which deals with the clicking on the tables
     * @param e Event e, generated by the action listener.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menu = (JMenuItem) e.getSource();

        if (menu == menuItemRequest) {
            processBookRequest();
        }
    }

    /**
     * Deals with the retrieval of the book to request and sending it to the controller.
     */
    private void processBookRequest() {
        int row = browseBooksTable.getSelectedRow();

        Book selectedBook = allBooks.get(row);

        System.out.println("client.Book requested");
        System.out.println("Title: " + selectedBook.getTitle());

    }
    
    /**
     * Table model class used to define how the table should look and populates it with books.
     */
    class TableModelAll extends AbstractTableModel {

        private ArrayList<Book> books;
        String headers[] = new String[]{"Title", "Author", "ISBN"};

        public TableModelAll(ArrayList<Book> books) {
            this.books = books;
        }

        @Override
        public int getRowCount() {
            return books.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
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
                default:
                    return "";
            }

        }

        @Override
        public String getColumnName(int column) {
            return headers[column];
        }

    }

    /**
     * Mouse listener for the tables which gets the row when a row is clicked on.
     */
    class TableMouseListener extends MouseAdapter {

        private JTable table;

        TableMouseListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point point = e.getPoint();
            int currentRow = table.rowAtPoint(point);
            table.setRowSelectionInterval(currentRow, currentRow);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        browseBooksTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 204, 255));

        jScrollPane1.setBackground(new java.awt.Color(0, 204, 255));

        browseBooksTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(browseBooksTable);

        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Browse Books");

        jLabel2.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Please right click to request a book");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(261, 261, 261))))
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable browseBooksTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
