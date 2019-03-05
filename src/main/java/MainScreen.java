import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MainScreen {

    private JFrame frame;
    private JPanel homeScreen;
    private JTable yourBooksTable;
    private JTabbedPane tabbedPane1;
    private JPanel menuBar;
    private JPanel tabPane;
    private JScrollPane yourBooks;
    private JScrollPane browseBooks;
    private JTable browseBooksTable;

    public MainScreen(JFrame loginFrame){
        frame = new JFrame("BookBnB");

        frame.setContentPane(homeScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        loginFrame.setVisible(false);

        setupListeners();

        populateTable();
    }

    private void setupListeners(){

    }


    private void populateTable(){

        Book book1 = new Book("1234", "test", "hello world");
        Book book2 = new Book("4567", "JKRowling", "Harry Potter");

        ArrayList<Book> books = new ArrayList<Book>();

        books.add(book1);
        books.add(book2);

        TableModel tableModel = new TableModel(books);

        yourBooksTable.setModel(tableModel);
        yourBooksTable.setAutoCreateRowSorter(true);

    }

    class TableModel extends AbstractTableModel {

        private ArrayList<Book> books;
        String headers[] = new String[]{"Title", "Author", "ISBN"};

        public TableModel(ArrayList<Book> books){
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

            switch (columnIndex){
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

}
