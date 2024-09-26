package org.example.common.core.leetcode.daily;

import java.net.URL;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/18 12:15
 */
class X{
    Y y=new Y();
    public X(){
        System.out.print("X");
    }
}
class Y{
    public Y(){
        System.out.print("Y");
    }
}
public class Z extends X{
    Y y=new Y();
    public Z(){
        System.out.print("Z");
    }
    public static void main(String[] args) {
        new Z();
        final Integer a = new Integer(17);
        final Integer b = new Integer(17);
        System.out.println(a == b);

//        URL u =new URL("http://www.123.com");
        System.out.println("is "+ 100 + 5);
        System.out.println(100 + 5 +" is");
        System.out.println("is "+ (100 + 5));
    }
    
}
