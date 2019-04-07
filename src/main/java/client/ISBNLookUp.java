package client;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Class which uses Google Book's API to retrieve information about a book given its ISBN
 * @author er205
 */
public class ISBNLookUp {
    private static final String APPLICATION_NAME = "GoogleAPI-Test";
    private static final String API_KEY = "AIzaSyDV9t8ZAEoVx1H31Pgub3FyIJSHjYJ_rmk";
    
//    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
//    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();
    
    /**
     * Given a ISBN number this class returns the Title, Author and Description found on Google Books
     * @param jsonFactory  used for constructing a JSON parser
     * @param query contains the ISBN of the book to look up
     * @return details of the book if any are found
     * @throws Exception 
     */
    private static String[] queryGoogleBooks(JsonFactory jsonFactory, String query) throws Exception {
        String title = "";
        java.util.List<String> authors = new ArrayList<String>();
        String thumbnailLink = "";
        String info;
        String description = "";
        String[] details = {""};
        //ClientCredentials.errorIfNotSpecified();

        // Set up Google Books client.
        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
                .build();

        // Set query string and filter only Google eBooks.
        System.out.println("Query: [" + query + "]");
        List volumesList = books.volumes().list(query);
        volumesList.setFilter("ebooks");

        // Execute the query.
        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            details[0] = "NO MATCHES FOUND";
            return details;
            
        }

        // Output results.
        for (Volume volume : volumes.getItems()) {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
//            Volume.SaleInfo saleInfo = volume.getSaleInfo();
            
            // Title.
            title = volumeInfo.getTitle();

            // Getting the url to the thumbnail
            Volume.VolumeInfo.ImageLinks thumbnail = volumeInfo.getImageLinks();
            thumbnailLink = (thumbnail).getThumbnail();
           
            // Author(s).
            authors = volumeInfo.getAuthors();
            if (authors != null && !authors.isEmpty()) {
//                System.out.print("Author(s): ");
                for (int i = 0; i < authors.size(); ++i) {
                    System.out.print(authors.get(i));
                    if (i < authors.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

        // Description (if any).
        if (volumeInfo.getDescription() != null && volumeInfo.getDescription().length() > 0) {
            description = volumeInfo.getDescription();
        }
       }
        
        info = String.join(",", authors);
        String[] detailS = {title, info, thumbnailLink, description};
        return detailS;
    }
    /**
     * Method which constructs the query after sanitising the ISBN 
     * @param ISBN parameter entered by the user
     * @return details of the book associated with the ISBN
     * @throws Exception 
     */
     public static String[] searchBook(String ISBN) throws Exception {
         
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        String cleanISBN = "";
      
        cleanISBN = sanitizeISBN(ISBN);
        String query = "isbn:" + cleanISBN;
        String[] details = {};

        try {
             details = queryGoogleBooks(jsonFactory, query);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return details;
    }
     /**
      * Sanitises the ISBN getting rid of spaces and making sure that the its 
      * length is equal to 10 or 13
      * @param ISBN entered by the user
      * @return cleanISBN sanitised ISBN
      * @throws Exception 
      */
     private static String sanitizeISBN(String ISBN) throws Exception{
        
        final String isbn = ISBN.replaceAll("[^\\d]", "");

        // A message error will pup up on screen
        if (isbn.length() != 10 && isbn.length() != 13) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "ISBN must be 10 or 13 characters long", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isbn;
    }
}
