package a4.server;

public class Main {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to receive the incoming messages. */
    private final static int PORT = 7825;

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        new ServerCommunicator(PORT).start();
                
    }

    // ---------------------------------------------------------------------------------------------
}
