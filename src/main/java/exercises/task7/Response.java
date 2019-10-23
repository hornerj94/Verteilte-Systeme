package exercises.task7;

import java.io.Serializable;

/**
 * The class for different type of response.
 * 
 * @author julian
 *
 */
public class Response implements Serializable {
    // ---------------------------------------------------------------------------------------------

    /** The serial version uid. */
    private static final long serialVersionUID = -1673347120076834716L;

    // =============================================================================================

    /** The vote of the response. */
    private Vote vote;
    
    /** The text of the reponse. */
    private String text;
    
    // ---------------------------------------------------------------------------------------------

    /**.
     * Creates an Response with the given parameters
     * 
     * @param vote The vote of the response
     * @param text The text of the response
     */
    public Response(final Vote vote, final String text) {
        this.vote = vote;
        this.text = text;
    }

    // ---------------------------------------------------------------------------------------------

    public Vote getVote() {
        return vote;
    }

    public String getText() {
        return text;
    }

    // ---------------------------------------------------------------------------------------------
}
