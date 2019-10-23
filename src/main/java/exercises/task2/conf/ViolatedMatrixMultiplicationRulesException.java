package exercises.task2.conf;

/**
 * Exception for violated matrix multiplication rules.
 * 
 * @author julian
 *
 */
public class ViolatedMatrixMultiplicationRulesException extends RuntimeException {

    private static final long serialVersionUID = -5116101128767750844L;

    /**
     * Constructs an <code>ViolatedMatrixMultiplikationRules</code> with no detail
     * message.
     */
    public ViolatedMatrixMultiplicationRulesException() {
        super("Die Regeln der Matrizenmultiplikation " + "wurden nicht eingehalten.\n"
                + "Beachten sie dass bei einer Matrizenmultiplikation die erste Matrix\n"
                + "dies selbe Anzahl an Spalten hat wie die Anzahl der Zeilen der zweiten Matrix");
    }

    /**
     * Constructs an <code>ViolatedMatrixMultiplikationRules</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public ViolatedMatrixMultiplicationRulesException(String s) {
        super(s);
    }

}
