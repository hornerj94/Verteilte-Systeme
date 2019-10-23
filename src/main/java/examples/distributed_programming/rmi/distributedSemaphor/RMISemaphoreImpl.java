package examples.distributed_programming.rmi.distributedSemaphor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISemaphoreImpl extends UnicastRemoteObject implements RMISemaphore {
    // ---------------------------------------------------------------------------------------------

    private static final long serialVersionUID = 4580868834222376720L;
    private int value;

    // ---------------------------------------------------------------------------------------------

    public RMISemaphoreImpl(int init) throws RemoteException {
        if (init < 0)
            init = 0;
        value = init;
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public synchronized void p() throws RemoteException {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        value--;
    }

    @Override
    public synchronized void v() throws RemoteException {
        value++;
        notify();
    }
    
    // ---------------------------------------------------------------------------------------------
}