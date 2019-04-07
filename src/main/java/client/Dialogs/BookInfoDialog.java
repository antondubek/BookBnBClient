/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Dialogs;

import client.Book;
import client.Controller;
import client.Lender;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * 
 */
public class BookInfoDialog extends javax.swing.JDialog {

    private Book selectedBook;
    private ArrayList<Lender> lenders;
    private String ISBN;
    private String author;
    private String title;
    
    /**
     * Creates new form BookInfoDialog
     */
    public BookInfoDialog(java.awt.Frame parent, boolean modal, Book selectedBook) {
        super(parent, "Book Information", modal);
        
        this.selectedBook = selectedBook;
        
        ISBN = selectedBook.getISBN();
        author = selectedBook.getAuthor();
        title = selectedBook.getTitle();
        
        initComponents();
        
        populateLenderTable();
        
    }
    
    private void populateLenderTable(){
        
        if(Controller.isAvailable){
            //Get all the books from the server
            lenders = Controller.getLenders(ISBN);
        } else {
            return;
        }
        
        
        
        

    }
    
    /**
     * Table model class used to define how the table should look and populates it with books.
     */
    class TableModelLenders extends AbstractTableModel {

        private ArrayList<Lender> lenders;
        String headers[] = new String[]{"Name", "City", "Loan Length"};

        public TableModelLenders(ArrayList<Lender> lenders) {
            this.lenders = lenders;
        }

        @Override
        public int getRowCount() {
            return lenders.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            Lender lender = lenders.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return lender.getName();
                case 1:
                    return lender.getCity();
                case 2:
                    return lender.getLoanLength() + " days";
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
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
