package org.example.common.core.leetcode.single;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/8/24 15:22
 */
public class SingletonStatic {
    /*
    * 私有构造方法
    * */
    private SingletonStatic() {

    }

    public static SingletonStatic getInstance() {
        return InnerClass.single;
    }

    // 静态的内部类
    public static class InnerClass{
        private static final SingletonStatic
        single = new SingletonStatic();
    }
}
