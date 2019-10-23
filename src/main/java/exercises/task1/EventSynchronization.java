package exercises.task1;

import java.util.concurrent.Semaphore;

class Thread_1 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_1(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
    
    }

    @Override
    public void run() {
        System.out.println("Activity_1 running");
        sems[0].release();
        sems[1].release();
   
    }
    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================

class Thread_2 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_2(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
   
    }

    @Override
    public void run() {
        try {
            sems[0].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Activity_2 running");
        sems[2].release();
        sems[3].release();
    
    }
    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================

class Thread_3 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_3(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
    
    }

    @Override
    public void run() {
        try {
            sems[1].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Activity_3 running");
        sems[4].release();
        sems[5].release();
    
    }
    // ---------------------------------------------------------------------------------------------
}

// =================================================================================================

class Thread_4 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_4(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
    
    }

    @Override
    public void run() {
        try {
            sems[2].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Activity_4 running");

        sems[6].release();
            
    }
    // ---------------------------------------------------------------------------------------------   
}

// =================================================================================================

class Thread_5 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_5(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
    
    }

    @Override
    public void run() {
        try {
            sems[3].acquire();
            sems[4].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Activity_5 running");

        sems[7].release();
            
    }
    // ---------------------------------------------------------------------------------------------   
}

// =================================================================================================

class Thread_6 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_6(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
    
    }

    @Override
    public void run() {
        try {
            sems[5].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Activity_6 running");

        sems[8].release();
    
    }
    // ---------------------------------------------------------------------------------------------   
}

// =================================================================================================

class Thread_7 extends Thread {
    // ---------------------------------------------------------------------------------------------

    private Semaphore[] sems;

    // ---------------------------------------------------------------------------------------------

    public Thread_7(Semaphore[] sems, String name) {
        super(name);
        this.sems = sems;
        start();
    
    }

    @Override
    public void run() {
        try {
            sems[6].acquire();
            sems[7].acquire();
            sems[8].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Activity_7 running");

    }
    // ---------------------------------------------------------------------------------------------   
}

// =================================================================================================

public class EventSynchronization {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        Semaphore[] sems = new Semaphore[9];
        for (int i = 0; i < sems.length; i++) {
            sems[i] = new Semaphore(0);
        }
        new Thread_5(sems, "Thread_5");
        new Thread_6(sems, "Thread_6");
        new Thread_3(sems, "Thread_3");

        new Thread_7(sems, "Thread_7");

        new Thread_1(sems, "Thread_1");
        new Thread_2(sems, "Thread_2");
        new Thread_4(sems, "Thread_4");
    }

    // ---------------------------------------------------------------------------------------------
}
