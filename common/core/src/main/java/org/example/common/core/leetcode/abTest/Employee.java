package org.example.common.core.leetcode.abTest;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/19 15:49
 */
class Person {
    String name = "No name";
    public Person(String nm) {
        name = nm;
    }
}
class Employee extends Person {
    String empID = "0000";
    public Employee(String id) {
        super("999");
        empID = id;
    }
}
class Test2 {
    public static void main(String args[]) {

        String s1 = "Coder";
        String s2 = "Coder";
        String s3 = "Coder" + s2;
        String s4 = "Coder" + "Coder";
        String s5 = s1+s2;
        System.out.println(s4 == s3);
        System.out.println(s5 == s3);
        System.out.println(s4 == "CoderCoder");
//        System.out.println(s4 == s3);
    }

    private float f = 1.0f;
    static class InnerClass{
        protected static float func() {
            // 静态要调用静态变量。
            return 0.1f;
        }
    }

}


class A {
    public A foo() {
        return this;
    }
}

class B extends A{
    @Override
    public A foo(){
        return this;
    }
}

class C extends B {

    public A foo(B b) {
        return b;
    }
}

class Base {
    public void methodOne() {
        System.out.println("a");
        methodTwo();
    }

    public void methodTwo() {
        System.out.println("B");
    }
}

class Derive extends Base{
    @Override
    public void methodOne() {
        super.methodOne();
        System.out.println("C");
    }

    @Override
    public void methodTwo()  {
        super.methodTwo();
        System.out.println("D");
    }
}

/**
 * 执行了
 * Base a = new Derive();
 * a.methodOne(); 输出的是ABDC；
 *
 */


