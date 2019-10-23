package examples.distributed_programming.web_service.bookservice;

import java.util.HashMap;
import java.util.Map;

public class BookImpl implements BookIF {
    // ---------------------------------------------------------------------------------------------

    /** Map contains ISBN/Book-title pairs. */
    private Map<String, String> books;

    // ---------------------------------------------------------------------------------------------

    /**
     * Default constructor. Stores the ISBN/Book-Title pairs in HashMap
     */
    public BookImpl() {
        books = new HashMap<String, String>();

        books.put("0130895601", "Advanced Java 2 Platform How to Program");
        books.put("0130895717", "C++ How to Program, Third edition");
        books.put("0130293636", "Visual Basic .NET How to Program");
        books.put("0130284173", "XML How to Program");
        books.put("0130923613", "Python How to Program");
    }

    // ---------------------------------------------------------------------------------------------

    /** service to obtain book title associated with ISBN number. */
    @Override
    public String getBookTitle(String ISBN) {
        return books.get(ISBN);
    }

    // ---------------------------------------------------------------------------------------------
}
