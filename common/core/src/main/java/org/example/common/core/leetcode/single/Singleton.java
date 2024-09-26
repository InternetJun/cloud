package org.example.common.core.leetcode.single;

/**
 * @Author: lejun
 * @project: cloud
 * @description:单例的实现类。有什么的缺点呢？
 * @time: 2023/8/24 14:58
 */
public class Singleton {
    private Singleton() {

    }
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }
}
