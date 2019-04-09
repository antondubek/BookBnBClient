package client.Dialogs;

import client.Book;
import client.Controller.ControllerMain;
import client.Controller.ControllerUser;
import client.Lender;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

/**
 * Will show all the information of a book and the users which is can be
 * borrowed from.
 */
public class BookInfoDialog extends javax.swing.JDialog implements ActionListener {

    private Book selectedBook;
    private ArrayList<Lender> lenders;
    private String ISBN;
    private String author;
    private String title;
    private String copyID;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemRequest;

    /**
     * Creates new form BookInfoDialog
     */
    public BookInfoDialog(java.awt.Frame parent, boolean modal, Book selectedBook) {
        super(parent, "Book Information", modal);

        this.selectedBook = selectedBook;

        ISBN = selectedBook.getISBN();
        author = selectedBook.getAuthor();
        title = selectedBook.getTitle();
        copyID = selectedBook.getCopyID();

        initComponents();

        populateLenderTable();

    }

    /**
     * Populates the table with users who have the book and adds a popup menu so
     * that a user can right click and select to request a book.
     */
    private void populateLenderTable() {

        if (ControllerMain.isAvailable) {
            //Get all the books from the server
            lenders = ControllerUser.getLenders(ISBN);
        } else {
            return;
        }

        TableModelLenders lendersTableModel = new TableModelLenders(lenders);

        lenderTable.setModel(lendersTableModel);
        lenderTable.setAutoCreateRowSorter(true);

        //Add a popup menu with the request book item and add a listener
        popupMenu = new JPopupMenu();
        menuItemRequest = new JMenuItem("Request Book");
        popupMenu.add(menuItemRequest);
        menuItemRequest.addActionListener(this);

        lenderTable.setComponentPopupMenu(popupMenu);
        lenderTable.addMouseListener(new TableMouseListener(lenderTable));

    }

    /**
     * Action listener which deals with the clicking on the tables
     *
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
     * Deals with the retrieval of the book to request and sending it to the
     * controller.
     */
    private void processBookRequest() {
        int row = lenderTable.getSelectedRow();

        Lender selectedLender = lenders.get(row);

        System.out.println("Register Book Clicked");
        System.out.println("Title: " + selectedBook.getTitle());
        String email = ControllerMain.email;
        String lenderID = selectedLender.getID();
        String copyID = selectedLender.getCopyID();

        if (ControllerMain.loggedIn) {
            // send request
            boolean response = ControllerUser.sendBorrowRequest(email, lenderID, copyID);

            if (response) {
                Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                JOptionPane.showMessageDialog(topFrame, "Request Sent Successfully");
            } else {
                Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                JOptionPane.showMessageDialog(topFrame, "Request Failed");
            }

        } else {
            //show error box
            Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            JOptionPane.showMessageDialog(topFrame, "Error: Please Login");
        }

    }

    /**
     * Table model class used to define how the table should look and populates
     * it with books.
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
     * Mouse listener for the tables which gets the row when a row is clicked
     * on.
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lenderTable = new javax.swing.JTable();
        headingLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(102, 102, 102));
        setPreferredSize(new java.awt.Dimension(530, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        lenderTable.setModel(new javax.swing.table.DefaultTableModel(
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
        lenderTable.setGridColor(new java.awt.Color(0, 204, 255));
        jScrollPane1.setViewportView(lenderTable);

        headingLabel.setForeground(new java.awt.Color(0, 204, 255));
        headingLabel.setText("label");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(headingLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lenderTable;
    // End of variables declaration//GEN-END:variables
}
