package com.example.stock.basic;

import java.util.*;

/**
 * @author lejun
 */
public class Caculation {

    public int caculate(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new LinkedList();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (isDigit(c)) {
                num += num * 10 + (c - '0');
            }

            // 是
            if (!isDigit(c) && i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                }

                // update
                sign = c;
                num = 0;
            }

        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.poll();
        }
        return res;
    }

    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}

class Main {
    public List<String> toInfixExpression(String infixExpression) {
        //存储中序表达式
        List<String> ls = new ArrayList<String>();
        int i = 0;
        String str;
        char c;
        do {
            //如果c 在 < 48 或者 > 57 说明是符号, 这里没有判断是 + , - , * , / 等等
            if ((c = infixExpression.charAt(i)) < 48 || (c = infixExpression.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { // 说明是数字，要进行拼接处理
                str = "";
                while (i < infixExpression.length() && (c = infixExpression.charAt(i)) >= 48
                        && (c = infixExpression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }

        } while (i < infixExpression.length());
        return ls;
    }

    /**
     * 将一个中缀表达式对应的List 转成 转换成逆波兰表达式, 放入到List中
     *
     * @param ls
     * @return
     */
    public List<String> parseSuffixExpression(List<String> ls) {
        Stack<String> s1 = new Stack<String>();
        Stack<String> s2 = new Stack<String>();
        List<String> lss = new ArrayList<String>();
        for (String ss : ls) {
            if (ss.matches("\\d+")) {
                lss.add(ss);
            } else if (ss.equals("(")) {
                s1.push(ss);
            } else if (ss.equals(")")) {

                while (!s1.peek().equals("(")) {
                    lss.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(ss)) {
                    lss.add(s1.pop());
                }
                s1.push(ss);
            }
        }
        //他的情况是怎么样的呢？为什么会出现呢？例如有1+((2+3)*4)-5最后的"-"符号的时候，他最后是压入栈里面的。所以会有S1.isEmpty() == false;的！！
        while (s1.size() != 0) {
            lss.add(s1.pop());
        }
        return lss;
    }

    /**
     * 计算出要的结果了。
     */
    public int calculate(List<String> ls) {
        Stack<String> s = new Stack<String>();
        for (String str : ls) {
            if (str.matches("\\d+")) {
                s.push(str);
            } else {
                int b = Integer.parseInt(s.pop());
                int a = Integer.parseInt(s.pop());
                int result = 0;
                if (str.equals("+")) {
                    result = a + b;
                } else if (str.equals("-")) {
                    result = a - b;
                } else if (str.equals("*")) {
                    result = a * b;
                } else if (str.equals("/")) {
                    result = a / b;
                } else {
                    throw new RuntimeException("符号错误");
                }
                s.push("" + result);
            }
        }
        return Integer.parseInt(s.pop());
    }

}


class Operation {
    private static int ADDITION = 1;
    private static int SUBTRACTION = 1;
    private static int MULTIPLICATION = 2;
    private static int DIVISION = 2;

    public static int getValue(String operation) {
        int result;
        switch (operation) {
            case "+":
                result = ADDITION;
                break;
            case "-":
                result = SUBTRACTION;
                break;
            case "*":
                result = MULTIPLICATION;
                break;
            case "/":
                result = DIVISION;
                break;
            default:
                result = 0;
        }
        return result;
    }
}

class xiecheng {
    // 他要计算的是什么呢？有一个操作数和很多的数。
/*
我的问题出现了1，switch case的写法。2，对数据的处理不当了。
*/

    public static void main(String[] args) {
        xiecheng xiecheng = new xiecheng();
        xiecheng.count("1+(2*(3-1))");
    }
    public void count(String s) {

        Deque stack = new LinkedList();
        int i = 0;
        String tempC = "";
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ')') {
                Stack<String> tempList = new Stack();
                String temp = (String) stack.pop();
                while (!"(".equals(temp)) {
                    tempList.add(temp);
                    temp = (String) stack.pop();
                }
                int ans = 0;
                switch (tempList.pop()) {
                    case "+":
                        while (!tempList.empty()) {
                            ans += Integer.parseInt(tempList.pop());
                        }
                        break;
                    case "-":
                        ans = Integer.parseInt(tempList.pop());
                        while (!tempList.empty()) {
                            ans -= Integer.parseInt(tempList.pop());
                        }
                        break;
                    case "*":
                        ans = Integer.parseInt(tempList.pop());
                        while (!tempList.empty()) {
                            ans *= Integer.parseInt(tempList.pop());
                        }
                        break;
                    default:
                        break;
                }
                stack.push(String.valueOf(ans));
                i++;
                continue;
            }

            if (c == '(' || c == '+' || c == '-' || c == '*') {
                stack.push(String.valueOf(c));
                i++;
                continue;
            }

            if (c == ' ') {
                i++;
                continue;
            }
            String temp = "";
            while (i + 1 < s.length() && c != ' ' && c != ')') {
                temp += c;
                c = s.charAt(++i);
            }
            stack.addFirst(temp);
            //这里的temp就是什么的情况呢？吧（和符号+数字全部加入了。
        }
    }
}