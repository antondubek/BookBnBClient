package client.Screens;

import client.Book;
import client.BorrowedBook;
import client.Controller;
import static client.Controller.email;
import client.Dialogs.AddBookDialog;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

/**
 * Class creates the screen for displaying the user's books
 */
public class MyBooksScreen extends javax.swing.JPanel {

    private ArrayList<Book> userBooks;
    private ArrayList<BorrowedBook> borrowedBooks;

    /**
     * Creates new form MyBooksScreen
     */
    public MyBooksScreen() {
        initComponents();
        populateTables();
    }

    public void populateTables() {
        populateMyBooksTable();
        populateBorrowedBooksTable();
    }

    /**
     * Sets the model for the browse books table and the user books table. This
     * will also populate the tables with data collected from the server via the
     * controller. Also adds a pop up menu and listener to the browse books
     * table which allows the user to request a book by right clicking on an
     * entry in the table.
     */
    private void populateMyBooksTable() {

        if (Controller.loggedIn) {
            userBooks = Controller.getUserBooks();
        } else {
            userBooks = new ArrayList<>();
        }

        MyBooksTableModel userBooksTableModel = new MyBooksTableModel(userBooks);

        yourBooksTable.setModel(userBooksTableModel);
        yourBooksTable.setAutoCreateRowSorter(true);

    }

    private void populateBorrowedBooksTable() {

        if (Controller.loggedIn) {
            borrowedBooks = Controller.getBorrowedBooks();
        } else {
            borrowedBooks = new ArrayList<>();
        }

        BorrowedBooksTableModel borrowedBooksTableModel = new BorrowedBooksTableModel(borrowedBooks);

        borrowedBooksTable.setModel(borrowedBooksTableModel);

    }

    /**
     * Table model class used to define how the table should look and populates
     * it with books.
     */
    class MyBooksTableModel extends AbstractTableModel {

        private ArrayList<Book> books;
        String headers[] = new String[]{"Title", "Author", "ISBN", "Available"};

        public MyBooksTableModel(ArrayList<Book> books) {
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
            if (columnIndex == 3) {
                return Boolean.class;
            } else {
                return String.class;
            }
        }

        //Removed the isCell editable functionality so that user cannot check or uncheck the box until
        //back end functionality has been implemented.
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 3;
        }

        /**
         * listener for the tickBox which sets the availability of the selected
         * book
         *
         * @param aValue
         * @param rowIndex
         * @param columnIndex
         */
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            if (aValue instanceof Boolean && columnIndex == 3) {
                books.get(rowIndex).setAvailability((Boolean) aValue);

                String ISBN = books.get(rowIndex).ISBN;
                Boolean available = books.get(rowIndex).availability;
                String copyID = books.get(rowIndex).copyID;

                client.Controller.updateBookAvailability(email, ISBN, available, copyID);
                populateMyBooksTable();
            }
        }
    }

    /**
     * Table model class used to define how the table should look and populates
     * it with books.
     */
    class BorrowedBooksTableModel extends AbstractTableModel {

        private ArrayList<BorrowedBook> books;
        String headers[] = new String[]{"Title", "Author", "Borrowed From", "Status"};

        public BorrowedBooksTableModel(ArrayList<BorrowedBook> books) {
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

            BorrowedBook book = books.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return book.getTitle();
                case 1:
                    return book.getAuthor();
                case 2:
                    return book.getLenderName();
                case 3:
                    return book.getStatus();
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

            return String.class;

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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        myBooksPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        yourBooksTable = new javax.swing.JTable();
        addBookBtn = new javax.swing.JButton();
        borrowedBooksPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        borrowedBooksTable = new javax.swing.JTable();
        LoanedBooksTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        loanedBooksTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 204, 255));
        setName("MyBooks"); // NOI18N
        setPreferredSize(new java.awt.Dimension(740, 335));

        jLabel1.setBackground(new java.awt.Color(0, 204, 255));
        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MyBooks");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        myBooksPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        addBookBtn.setBackground(new java.awt.Color(0, 204, 255));
        addBookBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 24)); // NOI18N
        addBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBookBtn.setText("Add Book");
        addBookBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        addBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myBooksPanelLayout = new javax.swing.GroupLayout(myBooksPanel);
        myBooksPanel.setLayout(myBooksPanelLayout);
        myBooksPanelLayout.setHorizontalGroup(
            myBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myBooksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(myBooksPanelLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(addBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myBooksPanelLayout.setVerticalGroup(
            myBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myBooksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addBookBtn)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("My Books", myBooksPanel);

        borrowedBooksPanel2.setBackground(new java.awt.Color(255, 255, 255));
        borrowedBooksPanel2.setForeground(new java.awt.Color(102, 102, 102));

        borrowedBooksTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(borrowedBooksTable);

        javax.swing.GroupLayout borrowedBooksPanel2Layout = new javax.swing.GroupLayout(borrowedBooksPanel2);
        borrowedBooksPanel2.setLayout(borrowedBooksPanel2Layout);
        borrowedBooksPanel2Layout.setHorizontalGroup(
            borrowedBooksPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowedBooksPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        borrowedBooksPanel2Layout.setVerticalGroup(
            borrowedBooksPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowedBooksPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Borrowed Books", borrowedBooksPanel2);

        loanedBooksTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(loanedBooksTable);

        javax.swing.GroupLayout LoanedBooksTabLayout = new javax.swing.GroupLayout(LoanedBooksTab);
        LoanedBooksTab.setLayout(LoanedBooksTabLayout);
        LoanedBooksTabLayout.setHorizontalGroup(
            LoanedBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        LoanedBooksTabLayout.setVerticalGroup(
            LoanedBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoanedBooksTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Loaned Books", LoanedBooksTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("MyBooks");
    }// </editor-fold>//GEN-END:initComponents

    private void addBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookBtnActionPerformed

        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        AddBookDialog addBookDialog = new AddBookDialog(topFrame, true);
        addBookDialog.setVisible(true);
        populateMyBooksTable();
    }//GEN-LAST:event_addBookBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LoanedBooksTab;
    private javax.swing.JButton addBookBtn;
    private javax.swing.JPanel borrowedBooksPanel2;
    private javax.swing.JTable borrowedBooksTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable loanedBooksTable;
    private javax.swing.JPanel myBooksPanel;
    private javax.swing.JTable yourBooksTable;
    // End of variables declaration//GEN-END:variables
}
