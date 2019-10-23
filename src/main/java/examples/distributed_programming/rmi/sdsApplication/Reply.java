package examples.distributed_programming.rmi.sdsApplication;

import java.io.Serializable;

public class Reply implements Serializable {
    // ---------------------------------------------------------------------------------------------

    private static final long serialVersionUID = -2550427480754028444L;
    public static final int NOT_FOUND = 0;
    public static final int FOUND = 1;

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
