package examples.distributed_programming.rmi.sdsApplication;

import java.rmi.Naming;

public class Client {
    // ---------------------------------------------------------------------------------------------

    private SDS_Server sds_server;

    // ---------------------------------------------------------------------------------------------

    public Client(String[] argumente) {
        Reply reply = null;
        try {
            sds_server = (SDS_Server) Naming.lookup("rmi://" + argumente[0] + "/SDS_Server");
            reply = sds_server.investigate(argumente[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        outputReply(reply);
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Syntax: java Client <server> <suchname>");
            System.exit(1);
        }

        new Client(args);
    }

    // =============================================================================================

    private void outputReply(Reply reply) {
        if (reply.status == Reply.FOUND) {
            System.out.println("\nSuchname gefunden");
            System.out.println("   Name: " + reply.name);
            System.out.println("   Vorname: " + reply.vorname);
            System.out.println("   Personalnummer: " + reply.personalnummer);
            System.out.println("   Wohnort: " + reply.wohnort);
        } else {
            System.out.println("\nSuchname nicht gefunden");
        }
    }

    // ---------------------------------------------------------------------------------------------
}