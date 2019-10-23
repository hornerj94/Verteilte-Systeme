package examples.distributed_programming.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import examples.distributed_programming.corba.serverPackage.Reply;

public class Client {
    private static ORB orb;
    private SDS_Server sds_server;

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

    public Client(String suchname) {
        Reply reply = null;

        try {

            NamingContext context = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            NameComponent nameComponent = new NameComponent("SDS_Server", "SDS_Server");
            NameComponent[] nameComponents = { nameComponent };

            org.omg.CORBA.Object obj = context.resolve(nameComponents);

            sds_server = SDS_ServerHelper.narrow(obj);
            if (sds_server == null) {
                System.out.println("Stringified object reference is of wrong type");
                System.exit(-1);
            }

            reply = sds_server.investigate(suchname);
            outputReply(reply);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Syntax: java Client -ORBInitialHost <server> <suchname>");
            System.exit(1);
        }
        orb = ORB.init(args, null);
        Client client = new Client(args[2]);
    }

} // Client