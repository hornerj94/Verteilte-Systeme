package examples.distributed_programming.corba;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class QueryInit {

    public static void main(String args[]) {

        SDS sds1 = new SDS("Ammann", "Eckhard", 17, "Reutlingen"), sds2 = new SDS("Laux", "Fritz", 13, "Tuebingen"),
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

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}