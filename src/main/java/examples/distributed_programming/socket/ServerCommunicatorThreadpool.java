package examples.distributed_programming.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunicatorThreadpool {
    // ---------------------------------------------------------------------------------------------

    private final static int PORT = 7825;
    private static ServerSocket serverSocket;
    private static Server server;
    private static Socket incoming;
    private static ExecutorService pool;

    // ---------------------------------------------------------------------------------------------

    public static void main(String args[]) {
        try {
            serverSocket = new ServerSocket(PORT);
            pool = Executors.newFixedThreadPool(10);

            System.out.println("Threadpool generated, Server waiting for clients...");

            server = new Server();

            while (true) {
                incoming = serverSocket.accept();
                Runnable task = new RunTask(incoming);
                pool.execute(task);
            }

        } catch (IOException e) {
            pool.shutdown();
        }
    }

    // =============================================================================================

    static class RunTask implements Runnable {
        // -----------------------------------------------------------------------------------------

        private Socket myconnection;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        // -----------------------------------------------------------------------------------------

        public RunTask(Socket connection) {
            myconnection = connection;
            try {
                out = new ObjectOutputStream(myconnection.getOutputStream());
                in = new ObjectInputStream(myconnection.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // -----------------------------------------------------------------------------------------

        @Override
        public void run() {
            try {
                String suchname = (String) in.readObject();
                Reply reply = server.investigate(suchname);
                out.writeObject(reply);

                out.flush();
                myconnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // -----------------------------------------------------------------------------------------
    }

    // =============================================================================================
}