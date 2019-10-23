package exercises.task4.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The communicating part of the server.
 * 
 * @author julian
 *
 */
public class ServerCommunicator extends Thread {
    // ---------------------------------------------------------------------------------------------

    /** The port of the server to wait for the client requests. */
    private int port;
    
    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an server communicator object with the given parameter.
     * 
     * @param port The port of the server
     */
    public ServerCommunicator(final int port) {
        this.port = port;
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!isInterrupted()) {
                Socket newSock = serverSocket.accept();
                new RequestWorkerThread(newSock).start();

            }
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ---------------------------------------------------------------------------------------------
}
