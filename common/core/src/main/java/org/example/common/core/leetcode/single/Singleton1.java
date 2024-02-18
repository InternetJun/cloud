package org.example.common.core.leetcode.single;

/**
 * @Author: lejun
 * @project: cloud
 * @description:单例的实现类:懒汉式
 * @time: 2023/8/24 14:58
 */
public class Singleton1 {
    private static Singleton1 instance = null;
    public static Singleton1 getInstance(){
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
