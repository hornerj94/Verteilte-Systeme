package examples.java_thread_programming;

public class HelloWorldThread extends Thread {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("\nAnwendungs-Thread vor Threaderzeugung");

        HelloWorldThread t = new HelloWorldThread();
        t.start();

        System.out.println("Anwendungs-Thread nach Threaderzeugung");
    }

    // =============================================================================================

    @Override
    public void run() {
        System.out.println("Neuer Thread: Hello World");
    }

    // ---------------------------------------------------------------------------------------------
}