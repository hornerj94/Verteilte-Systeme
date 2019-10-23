package examples.distributed_programming.rmi.sdsApplication;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SDS_ServerImpl extends UnicastRemoteObject implements SDS_Server {
    // ---------------------------------------------------------------------------------------------

    private static final long serialVersionUID = -6133447167228653238L;

    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        try {
            SDS_ServerImpl server = new SDS_ServerImpl();
            Naming.rebind("rmi://localhost/SDS_Server", server);
            
            System.out.println("SDS_Server erwartet Anfragen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =============================================================================================

    @Override
    public Reply investigate(final String suchname) {
        SDS sds = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Reply reply = null;

        System.out.println("Methode investigate aufgerufen, Parameter: " + suchname);
        
        try {
            String filename = "kundendatei.txt";
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            
            while (true) {
                sds = (SDS) ois.readObject();
                System.out.println("Ausgelesener Name: " + sds.name);
                
                if ((sds.name).equals(suchname)) {
                    ois.close();
                    fis.close();
                    System.out.println("Name " + suchname + " gefunden");
                    reply = new Reply(Reply.FOUND, sds.name, sds.vorname, 
                                      sds.personalnummer, sds.wohnort);
                    
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Suchname " + suchname + " nicht gefunden");
            reply = new Reply();
        }
        System.out.println();
        
        return reply;
    }

    // ---------------------------------------------------------------------------------------------
    
    public SDS_ServerImpl() throws RemoteException { }

    // ---------------------------------------------------------------------------------------------
}
