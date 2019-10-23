package examples.distributed_programming.socket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class QueryInit {
    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {

        SDS sds1 = new SDS("Ammann", "Eckhard", 17, "Reutlingen"), 
            sds2 = new SDS("Laux", "Fritz", 13, "Tuebingen"),
            sds3 = new SDS("Seichter", "Helmut", 15, "Pfullingen");

        System.out.println("Initialisierungsarbeit starten");
        String filename = "kundendatei.txt";
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(sds1);
            oos.writeObject(sds2);
            oos.writeObject(sds3);
            oos.flush();
            oos.close();
            fos.close();
            System.out.println("Kundendatei vorbereitet");

            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(((SDS) ois.readObject()).name);
            System.out.println(((SDS) ois.readObject()).name);
            System.out.println(((SDS) ois.readObject()).name);
            ois.close();
            fis.close();

        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------
}