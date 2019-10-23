package examples.distributed_programming.socket;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Server {
    // ---------------------------------------------------------------------------------------------

    public Reply investigate(String suchname) {

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
                    reply = new Reply(
                            Reply.FOUND, sds.name, sds.vorname, sds.personalnummer, sds.wohnort);
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

    // ---------------------------------------------------------------------------------------------
}
