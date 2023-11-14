package org.example.common.core.leetcode.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/4 14:49
 */
@Slf4j
public class PrintABC {
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    volatile int value = 0;

    private int count;

    public PrintABC(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        PrintABC printABC = new PrintABC(10);
        printABC.printABC();
    }

    public void printABC() {
        // 多个线程打印abc；
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new ThreadC()).start();
    }

    /**
     * //                conditionB.await();
     * //                conditionA.notify();
     * 这时是我的理解。这时错误的。要的是如下的
     * conditionA.await();
     *
     */
    class ThreadA implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 0) {
                        conditionA.await();
                    }
                    log.info("{}", Thread.currentThread().getName());
                    System.out.println("A");
                    conditionB.signal();
                    value++;
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    class ThreadB implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 1) {
                        conditionB.await();
                    }
                    log.info("{}", Thread.currentThread().getName());
                    System.out.println("B");
                    conditionC.signal();
                    value++;
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    class ThreadC implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 2) {
                        conditionC.await();
                    }
                    log.info("{}", Thread.currentThread().getName());
                    System.out.println("C");
                    conditionA.signal();
                    value++;
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }
}


class TestMultiThread {

}
