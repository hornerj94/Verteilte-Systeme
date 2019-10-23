package examples.synchronisation.shared_problem_demo;

/* Datei Shared.java                      E. Ammann    */
/* Klasse fuer eine Counter-Variable (, auf die        */
/* mehrere Threads unsynchronisiert zugreifen)        */
 
public class Shared {
    // ---------------------------------------------------------------------------------------------

    private int counter;

    // ---------------------------------------------------------------------------------------------

    // Konstruktormethode dieser Klasse
    Shared(int anfangswert) {
       counter = anfangswert;
    }

    // ---------------------------------------------------------------------------------------------

    // Counter - Wert veraendern
    public void change_counter(int delta) {
        counter = counter + delta;
    }

    // Counter - Wert abfragen
    public int get_counter() {
        return counter;
    }

    // ---------------------------------------------------------------------------------------------
}
