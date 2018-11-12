package a4.client;

import java.util.Scanner;

import a4.ClientType;
import a4.VoteType;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = 7825;

    /** The port of the server to send the incoming messages. */
    private final static String SERVER = "DESKTOP-Q99AK62.fh-reutlingen.de";

    /** The scanner for reading the users input. */
    private final static Scanner scanner = new Scanner(System.in);

    /** The current type of the client.  */
    private static ClientType clientType = null;
    
    /** The current type of the vote.  */
    private static VoteType voteType = null;

    /** The current topic of the client.  */
    private static String topic = "";

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) { 
        while (true) {
            startConsole();

            ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER, PORT);
            Client client = 
                    new Client(clientCommunicator, ClientType.VOTING_CLIENT, topic, voteType);
            client.start();

            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------------------------------------------------------------------------------------------

    private static void startConsole() {

        System.out.println("Sie haben folgende Auswahlmöglichkeiten:\n");
        System.out.println("Geben sie das Wort \"Vote\" ein, " 
                        + "um für ein Thema das sie interessiert eine Stimme abzugeben ");
        System.out.println("Geben sie das Wort \"Topic\" ein, " 
                    + "um ein neues Thema erstellen ");
        System.out.println("Geben sie das Wort \"Result\" ein, " 
                + "um die gesammelten Ergebnisse eines Themas zu betrachten ");
        System.out.println("Geben sie das Wort \"Exit\" ein, " 
                + "um das Programm zu beenden ");
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
            
            System.out.println("Geben sie \"Approval\" ein, "
                    + "wenn sie dem Thema ihr Zustimmung geben wollen");
            System.out.println("Geben sie \"Refusal\" ein, "
                    + "wenn sie das Thema Ablehnen wollen");
            System.out.println("Geben sie \"Abstention\" ein, "
                    + "wenn sie sich bei dem Thema enthalten wollen");
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
        while (true) {
            System.out.println("\nGeben sie den Namen des Themas ein,"
                    + " von welchem sie die gesammelten Ergebnisse betrachten wollen:");
            System.out.println("Thema: ");
            topic = scanner.nextLine();

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
