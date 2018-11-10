package a4.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The communicating part of the server.
 * 
 * @author julian
 *
 */
public class ServerCommunicator {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to send the incoming messages. */
    private final static int PORT = 7825;

    /** The socket of the server that waits for new incoming messages. */
    private static ServerSocket serverSocket;

    /** The functional part of the server. */
    private static Server server;
    
    // =============================================================================================

    public ServerCommunicator() {
        try {
            serverSocket = new ServerSocket(PORT);
            server = new Server();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ---------------------------------------------------------------------------------------------

    public void listen() {
        try {
            while (true) {
                Socket newSock = serverSocket.accept();
                new WorkerThread(newSock).start();
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ---------------------------------------------------------------------------------------------

    
    // ---------------------------------------------------------------------------------------------
}
