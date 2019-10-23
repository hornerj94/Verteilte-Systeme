package examples.synchronisation.producent_consument;

//* Datei Konsument.java                    E.Ammann    */
/* Klasse fuer die Konsumenten-Threads                 */

public class Konsument extends Thread {
    // ---------------------------------------------------------------------------------------------

    private FIFOPuffer fifo;
    private int zaehler;
    private int num_prod;

    // ---------------------------------------------------------------------------------------------

    public Konsument(int zaehler, FIFOPuffer fifo, int num_prod) {
        this.zaehler = zaehler;
        this.fifo = fifo;
        this.num_prod = num_prod;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        int temp;

        for (int i = 0; i < (4 * num_prod); i++) {
            temp = fifo.auslesen();
            System.out.println("Konsument " + zaehler + " hat ausgelesen: " + temp);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}
