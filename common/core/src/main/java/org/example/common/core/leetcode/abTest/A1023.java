package org.example.common.core.leetcode.abTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/23 15:05
 */
public class A1023 {
    public static void main(String[] args) {
        List<? extends A1023> list = new ArrayList<B1023>();
        int i = 0;
        int a = i++;
        int b = ++a;
        int c = a + b;
        int d = (a == 1) ? b : c;
        System.out.printf("a:%d;d:%d",a,d);
    }
}

class B1023 extends A1023 {

}

class C1023 extends B1023 {

}

class D extends B1023 {

}



