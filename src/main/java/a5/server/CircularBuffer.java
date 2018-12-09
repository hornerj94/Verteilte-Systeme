package a5.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import a5.RemoteCircularBuffer;

/**
 * Circular buffer class.
 * 
 * @author julian
 *
 */
public class CircularBuffer extends UnicastRemoteObject implements RemoteCircularBuffer {
    // ---------------------------------------------------------------------------------------------

    /** The serial version uid. */
    private static final long serialVersionUID = 1915216272775492556L;

    // =============================================================================================
    
    /** The pointer that points on the index to insert the next element. */
    private int inPointer;

    /** The pointer that points on the index to get the next element. */
    private int outPointer;

    /** The size of the buffer. */
    private int size;

    /** The amount of elements that the buffer holds currently. */
    private int counter;

    /** The elements of the buffer. */
    private int[] elements;

    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an circular buffer object with the given size.
     * 
     * @param size The size of the buffer.
     * @throws RemoteException Throws an communcation error
     */
    public CircularBuffer(final int size) throws RemoteException {
        super();
        setSize(size);
    }

    // ---------------------------------------------------------------------------------------------
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getElementAmount() {
        return counter;
    }
    
    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int readElement() throws RemoteException {
        int element;
        while (inPointer == outPointer) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        element = elements[outPointer];
        
        counter--;
        outPointer = (outPointer + 1) % size;
        notifyAll();
       
        return element;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void insertElement(final int newValue) throws RemoteException {
        while ((inPointer + 1) % size == outPointer) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        elements[inPointer] = newValue;
        
        counter++;
        inPointer = (inPointer + 1) % size;
        notifyAll();
        
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Sets the size of the buffer.
     * 
     * @param size The size to set the buffer
     */
    private void setSize(final int size) {
        if (size < 2) {
            this.size = 2;
            elements = new int[this.size];

        } else {
            this.size = size;
            elements = new int[this.size];

        }
    }

    // ---------------------------------------------------------------------------------------------
}
