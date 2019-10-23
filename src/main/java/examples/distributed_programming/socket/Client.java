package examples.distributed_programming.socket;

public class Client {
    // ---------------------------------------------------------------------------------------------

    private ClientCommunicator communicator;

    // ---------------------------------------------------------------------------------------------

    public Client(String[] argumente) {
        communicator = new ClientCommunicator(argumente[0]);
        Reply reply = communicator.communicate(argumente[1]);
        outputReply(reply);
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Syntax: java Client <server> <suchname>");
            System.exit(1);
        }

        Client client = new Client(args);
    }

    // =============================================================================================

    private void outputReply(Reply reply) {
        if (reply.status == Reply.FOUND) {
            System.out.println("\nSuchname gefunden");
            System.out.println("   Name: " + reply.name);
            System.out.println("   Vorname: " + reply.vorname);
            System.out.println("   Personalnummer: " + reply.personalnummer);
            System.out.println("   Wohnort: " + reply.wohnort);
        } else
            System.out.println("\nSuchname nicht gefunden");
    }

    // ---------------------------------------------------------------------------------------------
}