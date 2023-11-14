package org.example.common.core.leetcode.lock;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/19 15:18
 */
public class DataProcessor {
    private static final int CORE_POOL_SIZE = 10;
    // 核心线程数
    private static final int MAX_POOL_SIZE = 20;
    // 最大线程数
    private static final long KEEP_ALIVE_TIME = 60L;
    // 空闲线程的存活时间（// 秒）
    private static final int QUEUE_CAPACITY = 100;
    // 任务队列容量
    private static final String THREAD_NAME_PREFIX = "MyThreadPool-";
    // 线程名称前缀

    public static void main(String[] args) {
        // 创建自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY),
                new MyThreadFactory(THREAD_NAME_PREFIX),
                new MyRejectedExecutionHandler()
        );

        // 提交任务给线程池
        for (int i = 1; i <= 20; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Task " + taskNumber + " is being processed by " + Thread.currentThread().getName());
                // 在这里执行任务逻辑
            });
        }

        // 关闭线程池
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 自定义线程工厂
    static class MyThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    // 自定义拒绝策略
    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 在这里处理任务被拒绝的情况
            System.out.println("Task Rejected: " + r.toString());
        }
    }
}
