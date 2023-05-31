package assignment;

import java.util.concurrent.Semaphore;

public class Driver {
    public static void main(String args[]) throws InterruptedException {
       
        Semaphore sem = new Semaphore(1);
          
        Thread threadA = new MyThread(sem, "A");
        Thread threadB = new MyThread(sem, "B");
          
        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
          
        
        System.out.println("CumSum Value: " + Shared.CumSum);
    }
}