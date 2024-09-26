package org.example.web.socket.nettyTest;

import io.netty.util.HashedWheelTimer;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/28 10:08
 */
@Slf4j
public class WheelTimeTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws IOException, InterruptedException {

//定义一个HashedWheelTimer，有16个格的轮子，每一秒走一个一个格子
        HashedWheelTimer timer = new HashedWheelTimer(1, TimeUnit.SECONDS, 16);
        //把任务加到HashedWheelTimer里，到了延迟的时间就会自动执行
        timer.newTimeout((timeout) -> {
            log.info("task1 execute");
            countDownLatch.countDown();
        }, 500, TimeUnit.MILLISECONDS);
        timer.newTimeout((timeout) -> {
            log.info("task2 execute");
            countDownLatch.countDown();
        }, 2, TimeUnit.SECONDS);
        countDownLatch.await();
        timer.stop();
    }

}