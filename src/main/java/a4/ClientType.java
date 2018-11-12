package a4;

/**
 * The type of client
 * 
 * @author julian
 *
 */
public enum ClientType {
    // ---------------------------------------------------------------------------------------------
    
    VOTING_CLIENT("Vote"),
    
    CURRENT_STATE_CLIENT("Result"),
    
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

    /**
     * Gets the name of the value.
     * 
     * @return The name of the value
     */
    public String getName() {
        return name;
    }

    // ---------------------------------------------------------------------------------------------
}
