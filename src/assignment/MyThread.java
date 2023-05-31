package assignment;

import java.util.concurrent.Semaphore;

class MyThread extends Thread {
    Semaphore sem;
    String threadName;

    public MyThread(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        if (this.getName().equals("A")) {
            System.out.println("Starting " + threadName);
            try {
                System.out.println(threadName + " is waiting for a permit.");

                // Acquire the lock
                sem.acquire();

                System.out.println(threadName + " gets a permit.");

                for (int i = 0; i < 5; i++) {
                    Shared.CumSum = (Shared.X + Shared.Y) + Shared.CumSum;
                    System.out.println(threadName + " -> CumSum: " + Shared.CumSum);
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            System.out.println(threadName + " releases the permit.");
            sem.release();
        }
        else {
            System.out.println("Starting " + threadName);
            try {
                System.out.println(threadName + " is waiting for a permit.");
                
                sem.acquire();

                System.out.println(threadName + " gets a permit.");

                for (int i = 0; i < 5; i++) {
                    Shared.CumSum = Shared.CumSum - (Shared.X + Shared.Y);
                    System.out.println(threadName + " -> CumSum: " + Shared.CumSum);
                    Thread.sleep(10);
                }
                
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            System.out.println(threadName + " releases the permit.");
            sem.release();
        }
    }
}
