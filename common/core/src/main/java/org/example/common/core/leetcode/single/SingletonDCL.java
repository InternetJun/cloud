package org.example.common.core.leetcode.single;

/**
 * @Author: lejun
 * @project: cloud
 * @description:单例的实现类:懒汉式
 * @time: 2023/8/24 14:58
 */
public class SingletonDCL {
    private volatile static SingletonDCL instance;
    private SingletonDCL(){
        System.out.println(Thread.currentThread().getName()+"创建了对象");
    }

    public static SingletonDCL getInstance(){
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (null == instance) {
                     instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
