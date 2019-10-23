package exercises.task7.client;

import java.util.Scanner;

import exercises.task7.ClientType;
import exercises.task7.Response;
import exercises.task7.Vote;
import exercises.task7.VoteType;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The scanner for reading the users input. */
    private final static Scanner scanner = new Scanner(System.in);

    /** The current type of the client. */
    private static ClientType clientType = null;

    /** The current type of the vote. */
    private static VoteType voteType = null;

    /** The current topic of the client. */
    private static String topic = "";

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        while (true) {
            clientType = null;
            topic = "";
            voteType = null;

            startConsole();

            if (clientType != null && !topic.equals("")) {
                Client client = new Client(clientType, topic, voteType);
                Response clientResponse = client.request();

                printClientResponse(clientResponse);
            }
        }
    }

    // ---------------------------------------------------------------------------------------------

    private static void startConsole() {

        System.out.println("Sie haben folgende Auswahlmöglichkeiten:\n");
        System.out.println(
                "Geben sie das Wort \"Vote\" ein, " + "um für ein Thema das sie interessiert eine Stimme abzugeben ");
        System.out.println("Geben sie das Wort \"Topic\" ein, " + "um ein neues Thema erstellen ");
        System.out.println(
                "Geben sie das Wort \"Result\" ein, " + "um die gesammelten Ergebnisse eines Themas zu betrachten ");
        System.out.println("Geben sie das Wort \"Exit\" ein, " + "um das Programm zu beenden ");
        System.out.println("\nEingabe: ");

        String input = scanner.nextLine();

        switch (input) {
        case "Vote":
            vote();
            clientType = ClientType.VOTING_CLIENT;
            break;

        case "Topic":
            topic();
            clientType = ClientType.NEW_TOPIC_CLIENT;
            break;

        case "Result":
            result();
            clientType = ClientType.CURRENT_STATE_CLIENT;
            break;

        case "Exit":
            System.exit(0);
            break;

        default:
            System.out.println("Ungültige Eingabe");
            break;
        }
    }

    private static void vote() {
        while (true) {
            System.out.println("\nGeben sie das Thema ein zu dem sie eine Stimme abgeben wollen:");
            System.out.println("Thema: ");
            topic = scanner.nextLine();

            System.out.println("Geben sie \"Approval\" ein, " + "wenn sie dem Thema ihr Zustimmung geben wollen");
            System.out.println("Geben sie \"Refusal\" ein, " + "wenn sie das Thema Ablehnen wollen");
            System.out.println("Geben sie \"Abstention\" ein, " + "wenn sie sich bei dem Thema enthalten wollen");
            System.out.println("Stimme: ");

            voteType = convertToEnum(scanner.nextLine());
            if (voteType == null) {
                System.out.println("Ungültige Eingabe\n");
            } else {
                break;
            }
        }
    }

    private static void topic() {
        System.out.println("\nGeben sie den Namen des Themas ein das sie erstellen wollen:");
        System.out.println("Thema: ");
        topic = scanner.nextLine();
    }

    private static void result() {
        System.out.println("\nGeben sie den Namen des Themas ein,"
                + " von welchem sie die gesammelten Ergebnisse betrachten wollen:");
        System.out.println("Thema: ");
        topic = scanner.nextLine();
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Prints the response to the console.
     * 
     * @param clientResponse The response to print
     */
    private static void printClientResponse(final Response clientResponse) {
        Vote vote = clientResponse.getVote();
        if (vote != null) {
            System.out.println("Aktueller Stand der Umfrage: " + vote.getTopic() + "\n");
            System.out.println("Zustimmungen: " + vote.getApproval());
            System.out.println("Ablehnungen: " + vote.getRefusal());
            System.out.println("Enthaltungen: " + vote.getAbstention());
            System.out.println("\n");
        } else {
            System.out.println(clientResponse.getText());
            System.out.println("\n");
        }
    }

    // ---------------------------------------------------------------------------------------------

    private static VoteType convertToEnum(final String voteType) {
        switch (voteType) {
        case "Approval":
            return VoteType.APPROVAL;
        case "Refusal":
            return VoteType.REFUSAL;
        case "Abstention":
            return VoteType.ABSTENTION;
        default:
            return null;
        }
    }

    // ---------------------------------------------------------------------------------------------
}
