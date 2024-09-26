package org.example.common.core.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 用2个栈实现队列
 * a b c|| inStack
 * c b a|| outStack
 *
 * @time: 2023/9/25 14:13
 */
public class StackToList {
    Deque<Integer> stackIn = new LinkedList();
    Deque<Integer> stackOut = new LinkedList();

    public void push(int num) {
        stackIn.push(num);
        if (stackIn.size() > 1) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }

    public Integer pop() {
        //要把所有的inStack进入outStack
        if(!stackOut.isEmpty()) {
            return stackOut.pop();
        }
        return null;
    }

    public Integer peek() {
        return stackOut.peek();
    }

    public boolean empty() {
        return stackOut.isEmpty();
    }

    public static void main(String[] args) {
        final MyQueue stackToList = new MyQueue();        stackToList.push(1);
        stackToList.push(2);
        System.out.println(stackToList.peek());
        System.out.println(stackToList.pop());
        System.out.println(stackToList.empty());
    }
}

class MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
