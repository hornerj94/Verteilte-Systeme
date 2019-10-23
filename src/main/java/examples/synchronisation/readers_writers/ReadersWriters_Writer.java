package examples.synchronisation.readers_writers;

// =================================================================================================

public class ReadersWriters_Writer {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Data_and_AccessControl data = new Data_and_AccessControl();

        new Writer(data);
        new Reader(data, "Reader_1");
        new Reader(data, "Reader_2");

    }

    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================

class Data_and_AccessControl {
    // ---------------------------------------------------------------------------------------------

    private int value = 0;
    private int activeReaders = 0;
    private int activeWriters = 0;
    private int waitingReaders = 0;
    private int waitingWriters = 0;

    // ---------------------------------------------------------------------------------------------

    public int read() {
        entry_Read();
        int i = value;
        exit_Read();
        return i;
    }

    public void write(int i) {
        entry_Write();
        value = i;
        exit_Write();
    }

    // ---------------------------------------------------------------------------------------------

    private synchronized void entry_Read() {
        waitingReaders++;
        while (waitingWriters != 0 || activeWriters != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        waitingReaders--;
        activeReaders++;
    }

    private synchronized void exit_Read() {
        activeReaders--;
        notifyAll();
    }

    // ---------------------------------------------------------------------------------------------

    private synchronized void entry_Write() {
        waitingWriters++;
        while (activeReaders != 0 || activeWriters != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        waitingWriters--;
        activeWriters++;
    }

    private synchronized void exit_Write() {
        activeWriters--;
        notifyAll();
    }
    
    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================

class Reader extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Data_and_AccessControl data;

    // ---------------------------------------------------------------------------------------------

    public Reader(Data_and_AccessControl data, String string) {
        super(string);
        this.data = data;
        start();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            Integer integer = data.read();
            System.out.println(this.getName() + ": " + integer);
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================

class Writer extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Data_and_AccessControl data;

    // ---------------------------------------------------------------------------------------------

    public Writer(Data_and_AccessControl data) {
        this.data = data;
        start();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            data.write(i);
            System.out.println("Test");
            try {
                sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================
