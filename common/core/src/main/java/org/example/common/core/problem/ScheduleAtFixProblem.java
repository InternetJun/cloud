package org.example.common.core.problem;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/5/20 10:01
 */
public class ScheduleAtFixProblem {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        // variable used to thrown an error at the 3rd task invocation
        AtomicInteger countBeforeError = new AtomicInteger(100);

        // boolean allowing to leave the client to halt the scheduling task or not after a failure
        boolean mustHalt = true;
        do {
            Future<?> futureA = executor
                    .scheduleWithFixedDelay(new MyRunnable(countBeforeError), 1, 2, TimeUnit.SECONDS);
            try {
                futureA.get(); // will return only if canceled
            } catch (InterruptedException e) {
                // handle that : halt or not halt
                if (e.getCause() instanceof Error) {
                    System.out.println("I halt in case of Error");
                    mustHalt = true;
                } else {
                    System.out.println("I reschedule in case of Exception");
                    mustHalt = false;
                }
            } catch (ExecutionException e) {
                if (e.getCause() instanceof Error) {
                    System.out.println("I halt in case of Error");
                    mustHalt = true;
                } else {
                    System.out.println("I reschedule in case of Exception");
                    mustHalt = false;
                }
            }
        }
        while (!mustHalt);
        // shutdown the executorservice
        executor.shutdown();
    }

    private static class MyRunnable implements Runnable {

        private final AtomicInteger invocationDone;

        public MyRunnable(AtomicInteger invocationDone) {
            this.invocationDone = invocationDone;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", execution");

            if (invocationDone.decrementAndGet() == 0) {
                throw new Error("ohhh an Error in MyRunnable");
            } else {
//                throw new IllegalArgumentException("ohhh an Exception in MyRunnable");
                doubleException();
            }
        }
    }

    public static void doubleException() {
        try {
            int a = 2, b = 42/a;
            try {
                if (a == 1) {
                    a = a/(a - a);
                }
                if (a == 2) {
                    int[] c = {1};
                    c[43] = 9;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ArrayIndexOutOfBounds:"+e);
            }
        } catch (ArithmeticException e) {
            System.out.println("Divide by:"+e);
        }
    }

    @Test
    public void testException() {
        try {
            int a = 2, b = 42/a;
            try {
                if (a == 1) {
                  a = a/(a - a);
                }
                if (a == 2) {
                    int[] c = {1};
                    c[43] = 9;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ArrayIndexOutOfBounds:"+e);
            }
        } catch (ArithmeticException e) {
            System.out.println("Divide by:"+e);
        }
    }
}
