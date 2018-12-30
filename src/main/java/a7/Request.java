package a7;

import java.io.Serializable;

/**
 * The class for different type of requests.
 * 
 * @author julian
 *
 */
public class Request implements Serializable {
    // ---------------------------------------------------------------------------------------------

    /** The serial version uid. */
    private static final long serialVersionUID = 5734233045454598339L;

    // =============================================================================================

    /** The type of the client. */
    private ClientType clientType;

    /** The topic of the request. */
    private String topic;

    /** The type of the vote. */
    private VoteType voteType;

    // ---------------------------------------------------------------------------------------------

    /**
     * Creates Request with the given parameters.
     * 
     * @param topic The topic of the request
     * @param clientType The type of the client
     * @param voteType The type of the vote
     */
    public Request(final String topic, final ClientType clientType, final VoteType voteType) {
        this.topic = topic;
        this.clientType = clientType;
        this.voteType = voteType;
    }

    // ---------------------------------------------------------------------------------------------

    public String getTopic() {
        return topic;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    // ---------------------------------------------------------------------------------------------
}
