package exercises.task4;

import java.io.Serializable;

/**
 * The vote class. Contains the vote topic and the current state of the vote
 * 
 * @author julian
 *
 */
public class Vote implements Serializable {
    // ---------------------------------------------------------------------------------------------
    
    /** Generated serial version uid.  */
    private static final long serialVersionUID = 587920183853056490L;

    /** The topic of the vote. */
    private String topic;
    
    /** The approvals count of the vote. */
    private int approval;
    
    /** The refusals count of the vote. */
    private int refusal;

    /** The abstention count of the vote. */
    private int abstention;

    /** Whether the vote already exists or not. */
    private boolean exists;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    public Vote() { 
    	exists = true;
    }

    /**
     * Creates an vote with the given parameters.
     * 
     * @param topic The topic of the vote
     */
    public Vote(final String topic) {
        this.topic = topic;
        this.approval =  0;
        this.refusal = 0;
    	exists = true;
        
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Gets the topic of the vote
     * 
     * @return The topic of the vote
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the vote
     * 
     * @param topic The topic of the vote
     */
    public void setTopic(final String topic) {
        this.topic = topic;
    }

    /**
     * Gets the approval count of the vote
     * 
     * @return The approval count of the vote
     */
    public int getApproval() {
        return approval;
    }

    /**
     * Gets the refusal count of the vote
     * 
     * @return The refusal count of the vote
     */
    public int getRefusal() {
        return refusal;
    }

    /**
     * Gets the abstention count of the vote
     * 
     * @return The abstention count of the vote
     */
    public int getAbstention() {
        return abstention;
    }

    /**
     * Gets whether the vote exists or not.
     * 
     * @return Whether the vote exists or not
     */
    public boolean isExisting() {
        return exists;
    }

    /**
     * Sets whether the vote exists or not.
     * 
     * @param Whether the vote exists or not
     */
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    // ---------------------------------------------------------------------------------------------

    public void addApproval() {
        approval++;
    }

    public void addRefusal() {
        refusal++;
    }

    public void addAbstention() {
        abstention++;
    }

    // ---------------------------------------------------------------------------------------------
}
