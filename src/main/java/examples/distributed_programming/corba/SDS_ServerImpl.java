package examples.distributed_programming.corba;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import examples.distributed_programming.corba.serverPackage.Reply;

public class SDS_ServerImpl extends _SDS_ServerImplBase {
    public SDS_ServerImpl() {
    }

    @Override
    public Reply investigate(String suchname) {

        int result = -1;
        SDS sds;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Reply reply = null;

        System.out.println("Methode investigate aufgerufen, Parameter: " + suchname);
        String filename = "kundendatei.txt";
        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            while (true) {
                sds = (SDS) ois.readObject();
                System.out.println("Ausgelesener Name: " + sds.name);
                if ((sds.name).equals(suchname)) {
                    ois.close();
                    fis.close();
                    System.out.println("Name " + suchname + " gefunden");
                    reply = new Reply(Reply.FOUND, sds.name, sds.vorname, sds.personalnummer, sds.wohnort);
                    break;
                }
            }
            ;
        } catch (Exception e) {
            System.out.println("Suchname " + suchname + " nicht gefunden");
            reply = new Reply();
        }

        System.out.println();
        return reply;
    }

    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            SDS_ServerImpl server = new SDS_ServerImpl();
            orb.connect(server);

            NamingContext context = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

            NameComponent nameComponent = new NameComponent("SDS_Server", "SDS_Server");
            NameComponent[] nameComponents = { nameComponent };
            context.rebind(nameComponents, server);

            System.out.println("SDS_Server erwartet Anfragen");
            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main

} // SDS_ServerImpl
