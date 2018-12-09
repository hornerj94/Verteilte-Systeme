package a4;

/**
 * The type of client
 * 
 * @author julian
 *
 */
public enum ClientType {
    // ---------------------------------------------------------------------------------------------
    
    /** Voting Type. */
    VOTING_CLIENT("Vote"),
    
    /** Result Type. */
    CURRENT_STATE_CLIENT("Result"),
    
    /** Topic Type. */
    NEW_TOPIC_CLIENT("Topic");
    
    // ---------------------------------------------------------------------------------------------
    
    /** The name of the value. */
    private final String name;

    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an enum value with the given parameter.
     * 
     * @param name The name of the enum.
     */
    private ClientType(final String name) {
        this.name = name;
    }
    
    // ---------------------------------------------------------------------------------------------
}
