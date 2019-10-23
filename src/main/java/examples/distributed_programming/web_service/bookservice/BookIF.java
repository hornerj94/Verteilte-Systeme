package examples.distributed_programming.web_service.bookservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookIF extends Remote {
    // ---------------------------------------------------------------------------------------------

    /**
     * Gets the book title of the given isbn number.
     * 
     * @param isbn The isbn to get the title
     * @return The title of the book with the given isbn
     * @throws RemoteException Throws RemoteException if an communication error occurs
     */
    String getBookTitle(final String isbn) throws RemoteException;

    // ---------------------------------------------------------------------------------------------
}
