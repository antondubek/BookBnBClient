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
/**
 *
 * @author er205
 */
public class ISBNLookUp {
    private static final String APPLICATION_NAME = "GoogleAPI-Test";
    private static final String API_KEY = "AIzaSyDV9t8ZAEoVx1H31Pgub3FyIJSHjYJ_rmk";
    
     private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();
    
    private static String[] queryGoogleBooks(JsonFactory jsonFactory, String query) throws Exception {
        String title = "";
        java.util.List<String> authors = new ArrayList<String>();
        String thumbnailLink = "";
        String info = "";
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
            System.out.println("No matches found.");
            //return;
        }

        // Output results.
        for (Volume volume : volumes.getItems()) {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            Volume.SaleInfo saleInfo = volume.getSaleInfo();
            System.out.println("==========");
            // Title.
            title = volumeInfo.getTitle();

            // Getting the url to the thumbnail
            Volume.VolumeInfo.ImageLinks thumbnail = volumeInfo.getImageLinks();
            thumbnailLink = (thumbnail).getThumbnail();
            System.out.println("Link: " + thumbnailLink);

//           System.out.println("Title: " + volumeInfo.getTitle());
            System.out.println("Title: " + title);
            // Author(s).
            authors = volumeInfo.getAuthors();
            if (authors != null && !authors.isEmpty()) {
                System.out.print("Author(s): ");
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
            System.out.println("Description: " + volumeInfo.getDescription());
        }
        // Ratings (if any).
        if (volumeInfo.getRatingsCount() != null && volumeInfo.getRatingsCount() > 0) {
            int fullRating = (int) Math.round(volumeInfo.getAverageRating());
            System.out.print("User Rating: ");
            for (int i = 0; i < fullRating; ++i) {
                System.out.print("*");
            }
            System.out.println(" (" + volumeInfo.getRatingsCount() + " rating(s))");
        }
        // Price (if any).
        if (saleInfo != null && "FOR_SALE".equals(saleInfo.getSaleability())) {
            double save = saleInfo.getListPrice().getAmount() - saleInfo.getRetailPrice().getAmount();
            if (save > 0.0) {
                System.out.print("List: " + CURRENCY_FORMATTER.format(saleInfo.getListPrice().getAmount())
                        + "  ");
            }
            System.out.print("Google eBooks Price: "
                    + CURRENCY_FORMATTER.format(saleInfo.getRetailPrice().getAmount()));
            if (save > 0.0) {
                System.out.print("  You Save: " + CURRENCY_FORMATTER.format(save) + " ("
                        + PERCENT_FORMATTER.format(save / saleInfo.getListPrice().getAmount()) + ")");
            }
            System.out.println();
        }
        }
        info = String.join(",", authors);
        String[] details = {title, info, thumbnailLink};
        return details;
    }
     public static void searchBook(String ISBN) throws Exception {

        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        String cleanISBN = sanitizeISBN(ISBN);
        String prefix = "isbn:";
        String query = prefix + cleanISBN;

        try {
             String[] details = queryGoogleBooks(jsonFactory, query);
             System.out.println("=====DETAILS==== " + Arrays.toString(details));
            // Success!
//            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
     private static String sanitizeISBN(String ISBN) throws Exception{
        
        final String isbn = ISBN.replaceAll("[^\\d]", "");

        if (isbn.length() != 10 && isbn.length() != 13) {

            throw new Exception("ISBN must be 10 or 13 characters long");
        }

        return isbn;
    }
}
