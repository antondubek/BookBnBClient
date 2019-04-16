package client.Dialogs;

import client.Book;
import client.Controller.ControllerBook;
import client.Controller.ControllerMain;
import client.Controller.ControllerUser;
import client.ISBNLookUp;
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
import client.StarRater;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
        initComponents();
        getBookInfo();
        displayStars();
        populateLenderTable();
    }
    
    public void displayStars(){
         StarRater starRater = new StarRater(5, 5, 1);
         System.out.println("CREATED Star Rater");
         starRater.addStarListener(
            new StarRater.StarListener()   {
                public void handleSelection(int selection) {
                    System.out.println(selection);
                }
            });
//         starPanel.add(starRater);
//         starPanel.revalidate();
//         starPanel.repaint();
         System.out.println("PANEL ADDED");
    }
    private void getBookInfo() {
        ISBN = selectedBook.getISBN();
//        String rating = ControllerBook.getBookRating(ISBN);
//        bookRating.setText(rating);
        String[] details = {};
        try {
            details = ISBNLookUp.searchBook(ISBN);
        } catch(Exception e) {
            System.out.println("Exception getting book info" + e.getMessage());
        }
        

        if (!details[0].equals("NO MATCHES FOUND")) {
            String bookTitle = details[0];
            String author = details[1];
            String path = details[2];
            String ratingFromGoogle = details[4];
            String price = details[5];
            

            headingLabel.setText(bookTitle);
            bookAuthors.setText(author);
            bookISBN.setText(ISBN);
            googleRating.setText(ratingFromGoogle + "/5");
            googlePrice.setText("£" + price);
            // Read in the imgae from URL path
            
            try {
                URL url = new URL(path);
                BufferedImage image = ImageIO.read(url);
                ImageIcon icon = new ImageIcon(image);
                bookCover.setIcon(icon);
                bookCover.setText("");
            } catch (MalformedURLException ex) {
                Logger.getLogger(BookInfoDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BookInfoDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Set the ImageIcon of the label

        } else {
            author = selectedBook.getAuthor();
            title = selectedBook.getTitle();
            copyID = selectedBook.getCopyID();
            bookCover.setText("No image found");
            headingLabel.setText(title);
            bookAuthors.setText(author);
            bookISBN.setText(ISBN);
            googleRating.setText("NA");
            googleRating.setText("NA");
        }
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
        bookRating = new javax.swing.JLabel();
        bookISBN = new javax.swing.JLabel();
        bookAuthors = new javax.swing.JLabel();
        bookCover = new javax.swing.JLabel();
        googleRating = new javax.swing.JLabel();
        googlePrice = new javax.swing.JLabel();
        fromGoogle = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("label");

        bookRating.setText("jLabel1");

        bookISBN.setText("jLabel1");

        bookAuthors.setText("jLabel2");

        bookCover.setText("jLabel1");

        googleRating.setText("jLabel1");

        googlePrice.setText("jLabel1");

        fromGoogle.setText("From Google:");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Review");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(googlePrice)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bookISBN)
                            .addComponent(bookRating)
                            .addComponent(bookAuthors)
                            .addComponent(googleRating)
                            .addComponent(fromGoogle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookCover, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headingLabel)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bookRating)
                        .addGap(26, 26, 26)
                        .addComponent(bookISBN)
                        .addGap(26, 26, 26)
                        .addComponent(bookAuthors)
                        .addGap(65, 65, 65)
                        .addComponent(fromGoogle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(googleRating))
                    .addComponent(bookCover, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(googlePrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookAuthors;
    private javax.swing.JLabel bookCover;
    private javax.swing.JLabel bookISBN;
    private javax.swing.JLabel bookRating;
    private javax.swing.JLabel fromGoogle;
    private javax.swing.JLabel googlePrice;
    private javax.swing.JLabel googleRating;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lenderTable;
    // End of variables declaration//GEN-END:variables
}
