package a2.conf;

/**
 * 
 * 
 * @author julian
 *
 */
public class ViolatedMatrixMultiplicationRules extends RuntimeException {

    private static final long serialVersionUID = -5116101128767750844L;

    /**
     * Constructs an <code>ViolatedMatrixMultiplikationRules</code> with no
     * detail message.
     */
    public ViolatedMatrixMultiplicationRules() {
        super("Die Regeln der Matrizenmultiplikation "
                + "wurden nicht eingehalten.\nBeachten sie dass bei einer "
                + "Matrizenmultiplikation die erste Matrix\ndies selbe Anzahl an Spalten "
                + "hat wie die Anzahl der Zeilen der zweiten Matrix");
    }
    
    /**
     * Constructs an <code>ViolatedMatrixMultiplikationRules</code> class
     * with the specified detail message.
     *
     * @param   s   the detail message.
     */
    public ViolatedMatrixMultiplicationRules(String s) {
        super(s);
    }

}
