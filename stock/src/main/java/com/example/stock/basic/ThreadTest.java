package com.example.stock.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:lejun
 * @project:cloud
 * @time: 2023/1/1 9:40
 */
public class ThreadTest {

    private Condition conditionA;
    private Condition conditionB;
    private ReentrantLock lock;
    private static volatile Integer count;
    private int allCount = 10000;
    private int batch = 100;
    /**
     * 来测试的是多个线程下载数据
     */
    public static void main(String[] args) {

    }

    public void doAnalysis() {

    }
}
