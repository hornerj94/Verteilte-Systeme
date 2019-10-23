package examples.distributed_programming.socket;

import java.io.Serializable;

public class Reply implements Serializable {
    // ---------------------------------------------------------------------------------------------

    public final static int NOT_FOUND = 0;
    public final static int FOUND = 1;

    // =============================================================================================

    public int status = NOT_FOUND;
    public String name = null;
    public String vorname = null;
    public int personalnummer = 0;
    public String wohnort = null;

    // ---------------------------------------------------------------------------------------------

    public Reply(int status, String name, String vorname, int personalnummer, String wohnort) {
        this.status = status;
        this.name = name;
        this.vorname = vorname;
        this.personalnummer = personalnummer;
        this.wohnort = wohnort;
    }

    public Reply() { }

    // ---------------------------------------------------------------------------------------------
}
