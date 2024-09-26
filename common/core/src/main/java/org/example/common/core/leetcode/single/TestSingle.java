package org.example.common.core.leetcode.single;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/24 15:05
 */
@Slf4j
public class TestSingle {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonDCL.getInstance();
                }
            }).start();
        }
    }
}
