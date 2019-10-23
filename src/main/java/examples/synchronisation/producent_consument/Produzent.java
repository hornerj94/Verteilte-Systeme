package examples.synchronisation.producent_consument;

//* Datei Produzent.java                    E.Ammann  */
/* Klasse fuer die Produzenten-Threads               */

public class Produzent extends Thread {
    // ---------------------------------------------------------------------------------------------

    private FIFOPuffer fifo;
    private int zaehler;
    private int num_kons;

    // ---------------------------------------------------------------------------------------------

    public Produzent(int zaehler, FIFOPuffer fifo, int num_kons) {
        this.zaehler = zaehler;
        this.fifo = fifo;
        this.num_kons = num_kons;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        for (int i = 0; i < (4 * num_kons); i++) {
            fifo.einfuegen(100 * zaehler + i);
            System.out.println("Produzent " + zaehler + " hat eingefuegt: " + (100 * zaehler + i));
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}
