package client.Screens;

import client.Book;
import client.Controller.ControllerBook;
import client.Controller.ControllerMain;
import client.Dialogs.BookInfoDialog;
import client.TabelModels.ClassicBookTableModel;
import java.awt.Frame;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

/**
 * Class loads a screen where a user can browse all the books in the repository
 */
public class BrowseScreen extends javax.swing.JPanel {

    private ArrayList<Book> allBooks;
    private TableRowSorter rowSorter;

    /**
     * Creates new form BrowseScreen
     */
    public BrowseScreen() {
        initComponents();
        populateTable();
    }

    /**
     * Sets the model for the browse books table and the user books table. This
     * will also populate the tables with data collected from the server via the
     * controller. Also adds a popup menu and listener to the browse books table
     * which allows the user to request a book by right clicking on an entry in
     * the table.
     */
    public void populateTable() {

        if (ControllerMain.isAvailable) {
            //Get all the books from the server
            allBooks = ControllerBook.getAllBooks();
        } else {
            return;
        }

        //Create a instance of the custom table model from below
        ClassicBookTableModel allBooksTableModel = new ClassicBookTableModel(allBooks);

        //Set the model to the custom model
        browseBooksTable.setModel(allBooksTableModel);
        browseBooksTable.setAutoCreateRowSorter(true);

        //Add a row sorter so that the user can filter the table
        rowSorter = new TableRowSorter<ClassicBookTableModel>(allBooksTableModel);
        browseBooksTable.setRowSorter(rowSorter);

    }

    /**
     * Deals with the retrieval of the book to request and sending it to the
     * controller.
     */
    private void processBookInfo(String ISBN) {

        Book selectedBook = null;

        for (Book book : allBooks) {
            if (book.getISBN().equals(ISBN)) {
                selectedBook = book;
                break;
            }
        }

        System.out.println("View info clicked");
        System.out.println("Title: " + selectedBook.getTitle());

        //Launch a new bookInfoDialog
        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        BookInfoDialog bookInfoDialog = new BookInfoDialog(topFrame, true, selectedBook);
        bookInfoDialog.setVisible(true);

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
        jLabel3 = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 204, 255));
        setPreferredSize(new java.awt.Dimension(740, 335));

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
        browseBooksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(browseBooksTable);

        jLabel1.setFont(new java.awt.Font("Lantinghei SC", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Browse Books");

        jLabel2.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Double click to view book info");

        jLabel3.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Search");

        searchTxt.setForeground(new java.awt.Color(102, 102, 102));
        searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(205, 205, 205))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtKeyReleased

        //Sets the row filter to the text, regardless of case
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxt.getText()));
    }//GEN-LAST:event_searchTxtKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        Point point = evt.getPoint();
        int currentRow = browseBooksTable.rowAtPoint(point);
        browseBooksTable.setRowSelectionInterval(currentRow, currentRow);
        String ISBN = (String) browseBooksTable.getValueAt(currentRow, 2);

        if (evt.getClickCount() == 2) {
            processBookInfo(ISBN);
        }
    }//GEN-LAST:event_tableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable browseBooksTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchTxt;
    // End of variables declaration//GEN-END:variables
}
