package a2.masterworker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import a2.conf.ViolatedMatrixMultiplicationRules;
import a2.utils.Matrix;

public class Main {
    // ---------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        int [][] rowsA = {{  1, -2,  3,  4, -1, },
                          { -2,  3,  0,  1,  2, },
                          {  4, -1,  2,  1, -2, },
                          { -2,  1,  3, -1,  3, },
                          {  0,  2, -1,  2,  4, }};
        
        int [][] rowsB = {{  2, -4, -1,  1, -2 },
                          { -1,  1, -2,  2,  1 },
                          {  5,  0,  3, -2, -4 },
                          {  1, -2,  1,  0,  2 },
                          {  2,  3, -3,  0,  0 }};

        Matrix matrixA = new Matrix();
        matrixA.setRows(rowsA);
        Matrix matrixB = new Matrix();
        matrixB.setRows(rowsB);
        Matrix matrixC = null;
        
        Master masterThread = new Master();
        masterThread.setMatrixA(matrixA);
        masterThread.setMatrixB(matrixB);
        List<Worker> workers = new ArrayList<>();
        Semaphore listSemaphore = new Semaphore(1);
        
        int threadAmount = 0;
        try {
            threadAmount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Der übergebene Parameter war keine Zahl oder hatte das "
                    + "falsche Format");
        }
        
        for (int i = 0; i < threadAmount; i++) {
            workers.add(new Worker(listSemaphore, masterThread));
        }
        masterThread.setWorkers(workers);
        
        masterThread.start();     
        try {
            masterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        matrixC = masterThread.getMatrixC();
        if (matrixC != null) {
            matrixC.printMatrix();
        }
        
    }
    
    // ---------------------------------------------------------------------------------------------
}
