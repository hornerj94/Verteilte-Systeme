package examples.distributed_programming.corba;

import java.io.Serializable;

public class SDS implements Serializable { // SDS = Stammdatensatz
    public String name;
    public String vorname;
    public int personalnummer;
    public String wohnort;

    public SDS(String name, String vorname, int personalnummer, String wohnort) {
        this.name = name;
        this.vorname = vorname;
        this.personalnummer = personalnummer;
        this.wohnort = wohnort;
    }
}
