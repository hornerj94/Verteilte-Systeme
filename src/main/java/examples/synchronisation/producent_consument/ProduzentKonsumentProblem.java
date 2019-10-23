package examples.synchronisation.producent_consument;

//* Datei ProduzentKonsumentProblem.java           E. Ammann   */
/* Demo-Anwendung zum Synchronisationsproblem                 */
/* von Produzenten und Konsumenten.                           */
/* Paramenter:  - Groesse des Puffers                         */
/*              - Zahl der Produzenten                        */
/*              - Zahl der Konsumenten.                       */

public class ProduzentKonsumentProblem {
    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        int i, num_prod, num_kons;
        FIFOPuffer puffer;

        if (args.length != 3) {
            System.out.println("Syntaktisch falscher Aufruf des Programms");
            System.exit(1);
        }

        puffer = new FIFOPuffer(Integer.parseInt(args[0]));
        num_prod = Integer.parseInt(args[1]);
        num_kons = Integer.parseInt(args[2]);

        for (i = 0; i < num_prod; i++)
            (new Produzent(i, puffer, num_kons)).start();
        for (i = 0; i < num_kons; i++)
            (new Konsument(i, puffer, num_prod)).start();
    }

    // ---------------------------------------------------------------------------------------------
}
