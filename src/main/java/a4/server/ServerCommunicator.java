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

    /** Whether the server is to be terminated or not. */
    private boolean isTerminated = false;

    // ---------------------------------------------------------------------------------------------

    /**
     * Sets the server to listen to the given port.
     * 
     * @param port The port to listen to
     */
    public void listen(final int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!isTerminated) {
                Socket newSock = serverSocket.accept();
                new WorkerThread(newSock).start();

            }
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the server.
     */
    public void stop() {
        isTerminated = true;
    }

    // ---------------------------------------------------------------------------------------------
}
