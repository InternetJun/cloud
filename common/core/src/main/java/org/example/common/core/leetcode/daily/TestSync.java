package org.example.common.core.leetcode.daily;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/23 14:40
 */
public class TestSync {
    private synchronized void a() {

    }

    private void b() {
        synchronized (this) {

        }
     }
}
