package a4.server;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Server class for online votes with variable topics.
 * 
 * @author julian
 *
 */
public class Server {
    // ---------------------------------------------------------------------------------------------

    /** The path to the voteResults file. */
    private final static String path = "src/main/java/a4/server/voteResults";

    /** The file that contains the vote results. */
    private static File voteResults = new File(path);

    // =============================================================================================

    /**
     * Creates the file that contains the vote results.
     */
    public static void createFileIfNotExist() {
        try {
            voteResults.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Check whether the topic exists or not.
     * 
     * @param topic The topic to check
     * @return Whether the topic exists or not
     */
    public static boolean checkTopicExists(final String topic) {
        for (Vote currentVote : readCurrentVoteObjects()) {
            if (currentVote.getTopic().equals(topic)) {
                return true;

            }
        }
        return false;

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Adds an vote of the given type to the given topic.
     * 
     * @param topic    The topic to add an vote
     * @param voteType The type of the vote
     */
    public static void addVoteToTopic(final String topic, final VoteType voteType) {
        List<Vote> votes = readCurrentVoteObjects();

        for (Vote vote : votes) {
            if (vote.getTopic().equals(topic)) {
                switch (voteType) {
                case APPROVAL:
                    vote.addApproval();
                    break;
                case REFUSAL:
                    vote.addRefusal();
                    break;
                case ABSTENTION:
                    vote.addAbstention();
                    break;
                }
                writeVoteObjects(votes);

            }
        }

    }

    /**
     * Gets the current state of the vote with the given topic.
     * 
     * @param topic The topic to check the votes for
     * @return The vote object corresponding to the given topic
     */
    public static Vote getCurrentState(final String topic) {
        Vote vote = null;

        for (Vote currentVote : readCurrentVoteObjects()) {
            if (currentVote.getTopic().equals(topic)) {
                vote = currentVote;
                break;
            }
        }
        return vote;

    }

    /**
     * Adds a new topic to the file but only if it not exist yet.
     * 
     * @param topic The topic to add
     */
    public static void addNewTopic(final String topic) {
        List<Vote> votes = readCurrentVoteObjects();
        for (Vote vote : votes) {
            if (vote.getTopic().equals(topic)) {
                return;

            }
        }
        votes.add(new Vote(topic));
        writeVoteObjects(votes);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Write the given lists of the votes to the file.
     * 
     * @param votes The votes to write to the file
     */
    private static synchronized void writeVoteObjects(final List<Vote> votes) {
        try {
            FileOutputStream fos = new FileOutputStream(voteResults);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Vote vote : votes) {
                oos.writeObject(vote);
            }
            oos.flush();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the current votes from the file and return them as a list.
     * 
     * @return The list of votes
     */
    private static List<Vote> readCurrentVoteObjects() {
        List<Vote> votes = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(voteResults);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    votes.add((Vote) ois.readObject());
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            ois.close();
        } catch (EOFException e) {
            return votes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return votes;

    }

    // ---------------------------------------------------------------------------------------------
}
