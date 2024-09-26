package org.example.common.core.leetcode.twentyFour.march;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/20 10:56
 */
public class Calculator {
    public int cal(String s) {
        Deque<Character> queue = new LinkedList<Character>();
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 加入queue
            queue.add(chars[i]);
        }
        final int calculate = calculate(queue);
        return calculate;
    }
    public int calculate(Deque<Character> queue) {

        Deque<Integer> stack = new LinkedList<Integer>();
        int sum = 0;
        char sign = '+';
        int num = 0;
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                num += num*10 + (int)(c - '0');
            }
            // 这里没有写好递归的出口。
            if (c == '(') {
//                int calculate = calculate(s);
//                stack.push(calculate);
                num = calculate(queue);
            }
            // 普通的符号可以计算。
//            if (Character.isLetter(c)) {
            /**
             * 当处理表达式中的最后一个字符时，就会使用到 queue.isEmpty() 这个判断条件。让我们用一个简单的例子来说明：
             *
             * 假设我们有一个表达式："3 + 5 * (6 - 2)"。
             *
             * 在处理这个表达式时，当我们处理完最后一个字符 '2' 之后，我们已经处理完了整个表达式。
             * 此时，队列中不再有剩余的字符，因此 queue.isEmpty() 返回 true。在这种情况下，我们需要结束当前的计算过程，并返回结果。
             */
            if ((!Character.isDigit(c) && c != ' ') || queue.isEmpty()) {
                if (sign == '+') {
                    stack.push(num);
                } else if(sign == '-') {
                    stack.push(-num);
                }else if (sign == '*') {
                    final Integer pop = stack.pop();
                    stack.push(pop*num);
                } else if (sign == '/') {
                    final Integer pop = stack.pop();
                    stack.push(pop/num);
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        while (!stack.isEmpty()) {
            sum += stack.poll();
        }
        return sum;
    }

    @Test
    public void testCalculate() {
        String s = "2-3*5  ";
        System.out.println(cal(s));
    }
}
