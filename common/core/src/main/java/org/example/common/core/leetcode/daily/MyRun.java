package org.example.common.core.leetcode.daily;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/26 13:46
 */
public class MyRun implements Runnable{

    @Override
    public void run() {
        // todo
    }

}

class TestRun {
    public static void main(String[] args) {
//        new MyRun().start();
        new Thread(new MyRun()).start();
//        ThreadLocal 没有集成thread类和runnable接口。

    }
}
