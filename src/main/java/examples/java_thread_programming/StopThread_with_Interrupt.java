package examples.java_thread_programming;

public class StopThread_with_Interrupt extends Thread {
    // ---------------------------------------------------------------------------------------------

    public StopThread_with_Interrupt() {
        start();
    }

    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        StopThread_with_Interrupt st = new StopThread_with_Interrupt();

        try {
            sleep(7000);
        } catch (InterruptedException e) {
        }

        st.interrupt();
    }

    // =============================================================================================


    @Override
    public void run() {
        int i = 0;

        try {
            while (!isInterrupted()) {
                System.out.println("Output: " + i);
                i++;
                sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Thread terminiert ...");
    }

    // ---------------------------------------------------------------------------------------------
}