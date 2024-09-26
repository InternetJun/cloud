package org.example.common.core.leetcode.abTest;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/18 15:21
 */
public class TestImpl extends Test{
    @Override
    void method() {
        System.out.println("I am a subClass method");
    }

    private static int j = 0;

    private static Boolean methodB(int k) {
        j += k;
        return true;
    }

    public static void methodA(int i) {
        boolean b;
        b = i < 10 | methodB(4);
        b = i < 10 || methodB(8);

    }

    public static void main(String[] args) {
        methodA(0);
        System.out.println(j);
    }

}
