package a4.server;

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

    // ---------------------------------------------------------------------------------------------

    public Vote(final String topic) {
        this.topic = topic;
        this.approval =  0;
        this.refusal = 0;
        
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
