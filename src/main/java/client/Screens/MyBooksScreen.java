package client.Screens;

import client.Book;
import client.BorrowedBook;
import client.Controller.ControllerBook;
import client.Controller.ControllerMain;
import client.Dialogs.AddBookDialog;
import client.Dialogs.ProcessBookRequestDialog;
import client.TabelModels.BorrowedBooksTableModel;
import client.TabelModels.MyBooksTableModel;
import client.TabelModels.TableMouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class creates the screen for displaying the user's books
 */
public class MyBooksScreen extends javax.swing.JPanel implements ActionListener {

    private ArrayList<Book> userBooks;
    private ArrayList<BorrowedBook> borrowedBooks;
    private ArrayList<BorrowedBook> loanedBooks;

    private JMenuItem menuItemRecall;
    private JMenuItem menuItemApprove;
    private JMenuItem menuItemReturn;
    private JPopupMenu popupMenu;

    /**
     * Creates new form MyBooksScreen
     */
    public MyBooksScreen() {
        initComponents();
        populateTables();
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                populateTables();
            }
        });
    }

    /**
     * This will run the methods below and populate all the tables. Will be
     * called once the user logs in.
     */
    public void populateTables() {
        populateMyBooksTable();
        populateBorrowedBooksTable();
        populateLoanedBooksTable();
    }

    /**
     * This will populate the my books table with data collected from the server
     * via the controller and set the table model.
     */
    public void populateMyBooksTable() {

        if (ControllerMain.loggedIn) {
            userBooks = ControllerBook.getUserBooks();
        } else {
            userBooks = new ArrayList<>();
        }

        MyBooksTableModel userBooksTableModel = new MyBooksTableModel(userBooks, this);

        yourBooksTable.setModel(userBooksTableModel);
        yourBooksTable.setAutoCreateRowSorter(true);
        
        setupDaysLoanedColumn(yourBooksTable, yourBooksTable.getColumnModel().getColumn(4));

    }
    
    
    /**
     * Sets up the combo box so that the user can select how long to loan a book for.
     * Also implements a listener so that it updates the book value when a new selection
     * is made. This new value will be used to update the server from the book model.
     * @param table Table to add the combo box to
     * @param loanLengthColumn The column to add the combo box to.
     */
    public void setupDaysLoanedColumn(JTable table,TableColumn loanLengthColumn) {

        JComboBox comboBox = new JComboBox();
        int maxDaysToLoan = 20;
        for(int i=1; i< maxDaysToLoan; i++){
            comboBox.addItem(Integer.toString(i));
        }

        loanLengthColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click to select loan length");
        loanLengthColumn.setCellRenderer(renderer);
    }

    /**
     * This will populate the borrowed book table with data collected from the
     * server via the controller and set the table model.
     */
    private void populateBorrowedBooksTable() {

        if (ControllerMain.loggedIn) {
            borrowedBooks = ControllerBook.getBorrowedBooks();
        } else {
            borrowedBooks = new ArrayList<>();
        }

        BorrowedBooksTableModel borrowedBooksTableModel = new BorrowedBooksTableModel(borrowedBooks);

        borrowedBooksTable.setModel(borrowedBooksTableModel);

    }

    /**
     * This will populate the loaned books table with data collected from the
     * server via the controller and set the table model.
     *
     */
    private void populateLoanedBooksTable() {

        if (ControllerMain.loggedIn) {
            loanedBooks = ControllerBook.getLoanedBooks();
        } else {
            loanedBooks = new ArrayList<>();
        }

        BorrowedBooksTableModel loanedBooksTableModel = new BorrowedBooksTableModel(loanedBooks, true);
        loanedBooksTable.setModel(loanedBooksTableModel);

        //Add a popup menu with the request book item and add a listener
        popupMenu = new JPopupMenu();
        menuItemApprove = new JMenuItem("Approve/Deny Request");
        menuItemRecall = new JMenuItem("Recall Book");
        menuItemReturn = new JMenuItem("Mark Book Returned");

        popupMenu.add(menuItemRecall);
        popupMenu.add(menuItemApprove);
        popupMenu.add(menuItemReturn);

        menuItemRecall.addActionListener(this);
        menuItemApprove.addActionListener(this);
        menuItemReturn.addActionListener(this);

        loanedBooksTable.setComponentPopupMenu(popupMenu);
        loanedBooksTable.addMouseListener(new TableMouseListener(loanedBooksTable));

    }

    /**
     * Action listener which deals with the clicking on the tables
     *
     * @param e Event e, generated by the action listener.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menu = (JMenuItem) e.getSource();

        if (menu == menuItemRecall) {
            recallBook();
        }

        if (menu == menuItemApprove) {
            bookApproval();
        }

        if (menu == menuItemReturn) {
            returnBook();
        }
    }

    /**
     * Recalls a specific book. Will send the request to the server to recall a
     * book. Loads a dialog box informing the user whether the recall was
     * successful or not and will then update the table to show the new status.
     *
     */
    private void recallBook() {

        int row = loanedBooksTable.getSelectedRow();

        BorrowedBook selectedBook = loanedBooks.get(row);

        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);

        if (selectedBook.getStatus().equals("pending")) {
            JOptionPane.showMessageDialog(topFrame, "Book cannot be recalled at this time");
            return;
        }

        boolean response = ControllerBook.recallBook(selectedBook);
        if (response) {
            JOptionPane.showMessageDialog(topFrame, "Book recall sent");
        } else {
            JOptionPane.showMessageDialog(topFrame, "Book recall failed, please try again later");
        }

        populateLoanedBooksTable();
    }

    /**
     * Approves a request to borrow a book. Will load a dialog box to check that
     * a user wishes to approve or deny the loan request and then update the
     * table with the new status.
     */
    private void bookApproval() {

        int row = loanedBooksTable.getSelectedRow();

        BorrowedBook selectedBook = loanedBooks.get(row);
        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        if(selectedBook.getStatus().equals("pending")){
            //Launch a new dialog
            ProcessBookRequestDialog bookProcessDialog = new ProcessBookRequestDialog(topFrame, true, selectedBook);
            bookProcessDialog.setVisible(true);

            populateLoanedBooksTable();
        } else {
            JOptionPane.showMessageDialog(topFrame, "Decision has already been made");
            populateLoanedBooksTable();
        }

        
    }

    /**
     ** Allows the user to return a book. Will get the current book from the
     * table and send its request number to the server to set as returned.
     *
     */
    private void returnBook() {

        int row = loanedBooksTable.getSelectedRow();

        BorrowedBook selectedBook = loanedBooks.get(row);

        boolean response = ControllerBook.returnBook(selectedBook);

        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        if (response) {
            JOptionPane.showMessageDialog(topFrame, "Book return complete");
            populateLoanedBooksTable();
        } else {
            JOptionPane.showMessageDialog(topFrame, "Book return failed, please try again later");
        }

    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
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
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 204, 255));
        setName("MyBooks"); // NOI18N
        setPreferredSize(new java.awt.Dimension(740, 335));

        jLabel1.setBackground(new java.awt.Color(0, 204, 255));
        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MyBooks");

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabbedPaneFocusGained(evt);
            }
        });

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
        yourBooksTable.setGridColor(new java.awt.Color(0, 204, 255));
        jScrollPane1.setViewportView(yourBooksTable);

        addBookBtn.setBackground(new java.awt.Color(0, 204, 255));
        addBookBtn.setFont(new java.awt.Font("Lantinghei SC", 0, 24)); // NOI18N
        addBookBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBookBtn.setText("Add Book");
        addBookBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 255), 2, true));
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(myBooksPanelLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(addBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myBooksPanelLayout.setVerticalGroup(
            myBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myBooksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addBookBtn)
                .addGap(12, 12, 12))
        );

        tabbedPane.addTab("My Books", myBooksPanel);

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
        borrowedBooksTable.setGridColor(new java.awt.Color(0, 204, 255));
        jScrollPane2.setViewportView(borrowedBooksTable);

        javax.swing.GroupLayout borrowedBooksPanel2Layout = new javax.swing.GroupLayout(borrowedBooksPanel2);
        borrowedBooksPanel2.setLayout(borrowedBooksPanel2Layout);
        borrowedBooksPanel2Layout.setHorizontalGroup(
            borrowedBooksPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowedBooksPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
        borrowedBooksPanel2Layout.setVerticalGroup(
            borrowedBooksPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowedBooksPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Borrowed Books", borrowedBooksPanel2);

        LoanedBooksTab.setBackground(new java.awt.Color(255, 255, 255));
        LoanedBooksTab.setForeground(new java.awt.Color(102, 102, 102));

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
        loanedBooksTable.setGridColor(new java.awt.Color(0, 204, 255));
        loanedBooksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loanedBooksTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(loanedBooksTable);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 255));
        jLabel2.setText("Right Click a Book for Options");

        javax.swing.GroupLayout LoanedBooksTabLayout = new javax.swing.GroupLayout(LoanedBooksTab);
        LoanedBooksTab.setLayout(LoanedBooksTabLayout);
        LoanedBooksTabLayout.setHorizontalGroup(
            LoanedBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoanedBooksTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(LoanedBooksTabLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LoanedBooksTabLayout.setVerticalGroup(
            LoanedBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoanedBooksTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Loaned Books", LoanedBooksTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tabbedPane.getAccessibleContext().setAccessibleName("MyBooks");
    }// </editor-fold>//GEN-END:initComponents

    private void addBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookBtnActionPerformed

        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        AddBookDialog addBookDialog = new AddBookDialog(topFrame, true);
        addBookDialog.setVisible(true);
        populateMyBooksTable();
    }//GEN-LAST:event_addBookBtnActionPerformed

    private void tabbedPaneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabbedPaneFocusGained

    }//GEN-LAST:event_tabbedPaneFocusGained

    private void loanedBooksTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loanedBooksTableMouseClicked

  
    }//GEN-LAST:event_loanedBooksTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LoanedBooksTab;
    private javax.swing.JButton addBookBtn;
    private javax.swing.JPanel borrowedBooksPanel2;
    private javax.swing.JTable borrowedBooksTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable loanedBooksTable;
    private javax.swing.JPanel myBooksPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable yourBooksTable;
    // End of variables declaration//GEN-END:variables
}
