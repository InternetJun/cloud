package org.example.common.core.leetcode.twentyFour.march;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lejun
 * @project: cloud -leetcode
 * @description:
 * @time: 2024/3/18 10:38
 */
@Slf4j
public class Huawei0318 {

    public static String extractLongestExpression(String s) {
        // 分割字符串为数字和运算符的子串
        String[] substrings = s.split("[^0-9+\\-*/]");

        String longestExpression = "";
        int maxLength = 0;

        for (String s1 : substrings) {
            // 初始化双指针
            int l = 0;
            int r = 0;
            int length = s1.length();

            while (r < length) {
                // 找到合法表达式的开始位置
                while (r < length && !Character.isDigit(s1.charAt(r))) {
                    r++;
                }
                l = r;

                // 找到合法表达式的结束位置
                while (r < length && (Character.isDigit(s1.charAt(r)) || "+-*/".indexOf(s1.charAt(r)) != -1)) {
                    r++;
                }

                // 更新最长的合法表达式
                if (r - l > maxLength) {
                    //
                    longestExpression = s1.substring(l, r);
                    maxLength = r - l;
                }
            }
        }

        return longestExpression;
    }

    private static final String v = "0123456789+-*";
    private static List<String> solution(String line) {
        char[] chars = line.toCharArray();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (Character.isDigit(cur)) {
                int start = i;
                while (i + 1 < chars.length &&
                        v.contains(chars[i + 1] + "")) {
                    if (!Character.isDigit(cur) &&
                            !Character.isDigit(chars[i + 1])) {
                        break;
                    }
                    i++;
                }
                list.add(line.substring(start, i + 1));
            }
        }
        return list;
    }

    @Test
    public void test() {
        System.out.println(solution("2-5**6/3ddd"));
    }

    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            queue.add(c);
        }
        return helper(queue);
    }

    /**
     * 简易版本的计算器实现。
     *
     */
    private int helper(Queue<Character> s) {
        final Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        while (!s.isEmpty()) {
            char c = s.poll();
            if (Character.isDigit(c)) {
                num = num*10 + Character.getNumericValue(c);
            }
            if (c == '(') {
                num = helper(s);
            }
            if ((!Character.isDigit(c) && c != ' ')|| s.isEmpty()) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        int res = 0;
        for (Integer i : stack) {
            res += i;
        }
        return res;
    }
}

class Calculator {

    static String[] operators = new String[]{"-", "+", "*", "/", "(", ")"};
    static Stack digitStack;
    static Stack operatorStack;

    public static void main(String[] args) throws Exception {
        digitStack = new Stack();
        operatorStack = new Stack();
        String s = "1+(3+1)*(3-2)";
        transExpression(s);
        System.out.println(calculate());
    }

    private static String calculate() throws Exception {
        Stack stack = reverse(digitStack);
        Stack stack1 = new Stack();
        while (!stack.isEmpty()) {
            String value = (String) stack.pop();
            if (!isOperator(value)) {
                stack1.push(value);
            } else {
                String value1 = (String) stack1.pop();
                String value2 = (String) stack1.pop();
                Double result = cal(value1, value2, value);
                stack1.push(String.valueOf(result));
            }
        }
        return (String) stack1.pop();
    }

    private static Stack reverse(Stack s) throws Exception {
        Stack result = new Stack();
        while (!s.isEmpty()) {
            result.push(s.pop());
        }
        return result;
    }

    private static int getPriority(String operatorA) {
        if (operatorA.equals("+") || operatorA.equals("-")) {
            return 1;
        } else if (operatorA.equals("*") || operatorA.equals("/")) {
            return 2;
        } else if (operatorA.equals("(") || operatorA.equals(")")) {
            return 0;
        }
        return 0;
    }

    private static boolean isOperator(String a) {
        for (String c : operators) {
            if (c.equals(a)) {
                return true;
            }
        }
        return false;
    }

    private static boolean comparePriority(String operatorA, String operatorB) {
        return getPriority(operatorA) >= getPriority(operatorB);
    }

    private static Double cal(String a, String b, String operator) {
        Double tempValue1 = Double.parseDouble(a.toString());
        Double tempValue2 = Double.parseDouble(b.toString());
        if ("+".equals(operator)) {
            return tempValue1 + tempValue2;
        }
        if ("-".equals(operator)) {
            return tempValue2 - tempValue1;
        }
        if ("*".equals(operator)) {
            return tempValue1 * tempValue2;
        }
        if ("/".equals(operator)) {
            return tempValue2 / tempValue1;
        }
        return 0D;
    }

    private static String transExpression(String expression) throws Exception {
        for (int i = 0; i < expression.length(); i++) {
            String c = String.valueOf(expression.charAt(i));
            if (isOperator(c)) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(c);
                } else {
                    //遇到一个右括号，则弹出操作栈到后缀栈，直到弹出的符号是左括号
                    if (c.equals(")")) {
                        String operator = (String) operatorStack.pop();
                        while (operator != null && !operator.equals("(")) {
                            digitStack.push(operator);
                            if (operatorStack.isEmpty()) {
                                break;
                            }
                            operator = (String) operatorStack.pop();
                        }
                    } else if (c.equals("(")) {
                        operatorStack.push(c);
                    } else {
                        String operator = (String) operatorStack.peek();
                        while (operator != null) {
                            //比如当前操作符优先级是否大于操作栈中栈顶的符号，如果大于，则压入操作栈，如果小于，则弹出操作栈的符号，
                            // 一直到弹出的操作符号小于当前符号为止
                            if (comparePriority(c, operator)) {
                                operatorStack.push(c);
                                break;
                            } else {
                                operator = (String) operatorStack.pop();
                                digitStack.push(operator);
                                operator = (String) operatorStack.peek();
                                if (operator == null) {
                                    operatorStack.push(c);
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                digitStack.push(c);
            }
        }
        //将操作栈的符号压入到数字栈
        while (!operatorStack.isEmpty()) {
            digitStack.push(operatorStack.pop());
        }
//        digitStack.();

        return "";
    }
}
