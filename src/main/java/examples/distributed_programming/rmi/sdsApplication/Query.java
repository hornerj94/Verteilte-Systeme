package examples.distributed_programming.rmi.sdsApplication;

import java.io.Serializable;

public class Query implements Serializable {
    // ---------------------------------------------------------------------------------------------

    private static final long serialVersionUID = -833216699541101129L;
    private String name;

    // ---------------------------------------------------------------------------------------------

    public Query(String name) {
        this.name = name;
    }

    // ---------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    // ---------------------------------------------------------------------------------------------
}
