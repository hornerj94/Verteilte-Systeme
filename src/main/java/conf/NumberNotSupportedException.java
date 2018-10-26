package conf;

/**
 * Exception for not supported numbers.
 * 
 * @author julian
 *
 */
public class NumberNotSupportedException extends RuntimeException {

    private static final long serialVersionUID = -51161011281232144L;

    /**
     * Constructs an <code>NumberNotSupportedException</code> with no detail
     * message.
     */
    public NumberNotSupportedException() {
        super("Diese Nummer wird nicht unterstützt");
    }

    /**
     * Constructs an <code>NumberNotSupportedException</code> exception with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public NumberNotSupportedException(String s) {
        super(s);
    }

}
