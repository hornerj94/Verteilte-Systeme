package examples.distributed_programming.rmi.callback;

import java.rmi.Naming;

public class ChatServerMain {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServerImpl();
            Naming.rebind("rmi://localhost/ChatServer", server);
            Naming.list("Test");
        } catch (Exception e) {
        }
    }

    // ---------------------------------------------------------------------------------------------
}