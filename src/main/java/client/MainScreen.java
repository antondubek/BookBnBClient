package client;
import client.Book;
import client.Controller;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The main screen of the application, this is where the majority of the user will interface with the system.
 */
public class MainScreen implements ActionListener {

    private JFrame frame;
    private JPanel homeScreen;
    private JTable yourBooksTable;
    private JTabbedPane tabbedPane1;
    private JPanel menuBar;
    private JPanel tabPane;
    private JScrollPane yourBooks;
    private JScrollPane browseBooks;
    private JTable browseBooksTable;
    private JLabel welcomeTxt;
    private JPanel Buttons;
    private JButton addBookBtn;

    private JMenuItem menuItemRequest;
    private JPopupMenu popupMenu;

    ArrayList<Book> userBooks;
    ArrayList<Book> allBooks;

    /**
     * Constructor which will setup the frame, listeners and populate the tables.
     * @param loginFrame Frame of the previous window so that it can close it once it has loaded.
     */
    public MainScreen(JFrame loginFrame) {
        frame = new JFrame("BookBnB");

        frame.setContentPane(homeScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        loginFrame.setVisible(false);

        if(Controller.getUser()){
            welcomeTxt.setText("Welcome " + Controller.name + " to BookBnB in " + Controller.city);
        } else {
            welcomeTxt.setText("Welcome to BookBnB!");
        }


        setupListeners();

        populateTable();

    }

    /**
     * Method to setup the listeners for any buttons
     */
    private void setupListeners() {
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook addBookDialog = new AddBook(frame);
                addBookDialog.setVisible(true);
                populateTable();
            }
        });
    }


    /**
     * Sets the modelf for the browse books table and the user books table. This will also populate the tables
     * with data collected from the server via the controller.
     * Also adds a popup menu and listener to the browse books table which allows the user to request a book by right
     * clicking on an entry in the table.
     */
    public void populateTable() {

        userBooks = Controller.getUserBooks();
        allBooks = Controller.getAllBooks();

        TableModelUser userBooksTableModel = new TableModelUser(userBooks);

        yourBooksTable.setModel(userBooksTableModel);
        yourBooksTable.setAutoCreateRowSorter(true);

        TableModelAll allBooksTableModel = new TableModelAll(allBooks);

        browseBooksTable.setModel(allBooksTableModel);
        browseBooksTable.setAutoCreateRowSorter(true);

        popupMenu = new JPopupMenu();
        menuItemRequest = new JMenuItem("Request client.Book");
        popupMenu.add(menuItemRequest);
        menuItemRequest.addActionListener(this);

        browseBooksTable.setComponentPopupMenu(popupMenu);
        browseBooksTable.addMouseListener(new TableMouseListener(browseBooksTable));

    }

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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        homeScreen = new JPanel();
        homeScreen.setLayout(new GridBagLayout());
        menuBar = new JPanel();
        menuBar.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        homeScreen.add(menuBar, gbc);
        welcomeTxt = new JLabel();
        welcomeTxt.setText("Welcome Username");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        menuBar.add(welcomeTxt, gbc);
        tabPane = new JPanel();
        tabPane.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        homeScreen.add(tabPane, gbc);
        tabbedPane1 = new JTabbedPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        tabPane.add(tabbedPane1, gbc);
        yourBooks = new JScrollPane();
        tabbedPane1.addTab("Your Books", yourBooks);
        yourBooksTable = new JTable();
        yourBooks.setViewportView(yourBooksTable);
        browseBooks = new JScrollPane();
        tabbedPane1.addTab("Browse Books", browseBooks);
        browseBooksTable = new JTable();
        browseBooks.setViewportView(browseBooksTable);
        Buttons = new JPanel();
        Buttons.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        homeScreen.add(Buttons, gbc);
        addBookBtn = new JButton();
        addBookBtn.setText("Add Book");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Buttons.add(addBookBtn, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return homeScreen;
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


}