package examples.distributed_programming.socket;

import java.io.Serializable;

// SDS = Stammdatensatz
public class SDS implements Serializable {
    // ---------------------------------------------------------------------------------------------

    public String name;
    public transient String vorname;
    public int personalnummer;
    public String wohnort;

    // ---------------------------------------------------------------------------------------------

    public SDS(String name, String vorname, int personalnummer, String wohnort) {
        this.name = name;
        this.vorname = vorname;
        this.personalnummer = personalnummer;
        this.wohnort = wohnort;
    }

    // ---------------------------------------------------------------------------------------------
}
