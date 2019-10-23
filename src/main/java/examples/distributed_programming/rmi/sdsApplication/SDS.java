package examples.distributed_programming.rmi.sdsApplication;

import java.io.Serializable;

// SDS = Stammdatensatz
public class SDS implements Serializable {
    // ---------------------------------------------------------------------------------------------

    public String name;
    public String vorname;
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
