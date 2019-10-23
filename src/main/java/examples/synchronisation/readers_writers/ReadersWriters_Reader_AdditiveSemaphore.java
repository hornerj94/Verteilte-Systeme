package examples.synchronisation.readers_writers;
//package synchronisation.readers_writers;
//
//import synchronisation.semaphoren.AdditiveSemaphore;
//
////=================================================================================================
//
//class ReadersWriters_Reader_AdditiveSemaphore {
//    // ---------------------------------------------------------------------------------------------
//
//    public static void main(String[] args) {
//        Data_and_AccessControl_AddSem data = new Data_and_AccessControl_AddSem();
//
//        new Writer(data);
//        new Reader(data, "Reader_1");
//        new Reader(data, "Reader_2");
//    }
//
//    // ---------------------------------------------------------------------------------------------
//}
//
//// =================================================================================================
//
//class Data_and_AccessControl_AddSem {
//    // ---------------------------------------------------------------------------------------------
//
//    // Maximum number of concurrent readers
//    private static final int MAX = 1000;
//
//    // =============================================================================================
//
//    // Generate additive semaphore
//    private AdditiveSemaphore sem = new AdditiveSemaphore(MAX);
//    private int value = 0;
//
//    // ---------------------------------------------------------------------------------------------
//
//    public int read() {
//        entry_Read();
//        int i = value;
//        exit_Read();
//        return i;
//    }
//
//    public void write(int i) {
//        entry_Write();
//        value = i;
//        exit_Write();
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    private void entry_Read() {
//        sem.p(1);
//    }
//
//    private void exit_Read() {
//        sem.v(1);
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    private void entry_Write() {
//        sem.p(MAX);
//    }
//
//    private void exit_Write() {
//        sem.v(MAX);
//    }
//
//    // ---------------------------------------------------------------------------------------------
//}
//
//// =================================================================================================
//
//class Reader extends Thread {
//    // ---------------------------------------------------------------------------------------------
//
//    private Data_and_AccessControl_AddSem data;
//
//    // ---------------------------------------------------------------------------------------------
//
//    public Reader(Data_and_AccessControl_AddSem data, String string) {
//        super(string);
//        this.data = data;
//        start();
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 50; i++) {
//            Integer integer = data.read();
//            System.out.println(this.getName() + ": " + integer);
//            try {
//                sleep(10);
//            } catch (InterruptedException e) {
//            }
//        }
//    }
//
//    // ---------------------------------------------------------------------------------------------
//}
//
//// =================================================================================================
//
//class Writer extends Thread {
//    // ---------------------------------------------------------------------------------------------
//
//    private Data_and_AccessControl_AddSem data;
//
//    // ---------------------------------------------------------------------------------------------
//
//    public Writer(Data_and_AccessControl_AddSem data) {
//        this.data = data;
//        start();
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    @Override
//    public void run() {
//        for (int i = 1; i < 5; i++) {
//            data.write(i);
//            try {
//                sleep(50);
//            } catch (InterruptedException e) {
//            }
//        }
//    }
//
//    // ---------------------------------------------------------------------------------------------
//}
//
//// =================================================================================================
