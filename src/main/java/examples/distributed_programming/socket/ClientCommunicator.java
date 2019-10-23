package examples.distributed_programming.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCommunicator {
    // ---------------------------------------------------------------------------------------------

    private final static int PORT = 7825;

    // =============================================================================================

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    // ---------------------------------------------------------------------------------------------

    public ClientCommunicator(String server) {
        try {
            socket = new Socket(server, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------------------------------

    public Reply communicate(String suchname) {
        Reply reply = null;

        try {
            out.writeObject(suchname);
            reply = (Reply) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reply;
    }

    public void stop() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
        }

        System.exit(0);
    }

    // ---------------------------------------------------------------------------------------------
}