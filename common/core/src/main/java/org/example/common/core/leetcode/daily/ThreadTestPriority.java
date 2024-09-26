package org.example.common.core.leetcode.daily;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/28 15:49
 */
public class ThreadTestPriority {
    public static void main(String[] args) {
        // 创建10个线程
        Thread[] threads = new Thread[10];

        // 创建3个高优先级线程
        for (int i = 0; i < 3; i++) {
            threads[i] = new HighPriorityThread(i+1);
//            public final static int MIN_PRIORITY = 1;
//
//            /**
//             * The default priority that is assigned to a thread.
//             */
//            public final static int NORM_PRIORITY = 5;
//
//            /**
//             * The maximum priority that a thread can have.
//             */
//            public final static int MAX_PRIORITY = 10;
            int val = Thread.MAX_PRIORITY;
            threads[i].setPriority(val--);
        }

        // 创建7个普通优先级线程
        for (int i = 3; i < 10; i++) {
            threads[i] = new NormalPriorityThread();
        }

        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }
    }

    // 高优先级线程
    static class HighPriorityThread extends Thread {
        int i;

        public HighPriorityThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.printf("Thread %d:High priority thread is running.\n", i);
        }
    }

    // 普通优先级线程
    static class NormalPriorityThread extends Thread {
        @Override
        public void run() {
            System.out.println("Normal priority thread is running.");
        }
    }

}
