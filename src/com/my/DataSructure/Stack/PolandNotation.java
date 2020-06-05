package com.my.DataSructure.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/** 栈 */
public class PolandNotation {
  public static void main(String[] args) {
    // 定义逆波兰表达式
    // (3+4)*5-6 => 3 4 + 5 * 6 -
    String suffixExpression = "3 4 + 5 * 6 -";
    List<String> listString = getListString(suffixExpression);
    System.out.println(listString);
    int calculate = calculate(listString);
    System.out.println(calculate);
  }

  // 将中缀表达式转化为后缀表达式 (未完成)
  public static List<String> InfixToSuffixExpressions(String infixExpression) {
    // 定义一个list存放中缀表达式
    ArrayList<String> ls = new ArrayList<>();
    // 指针用于遍历中缀表达式
    int i = 0;
    // 对多位数拼接
    String str;
    char c;
    do {
      // 如果是一个非数字，加入ls

      if ((c = infixExpression.charAt(i)) < 48 || (c = infixExpression.charAt(i)) > 48) {
        ls.add("" + c);
        i++;
      }
    } while (i < infixExpression.length());
    {
    }
    return ls;
  }

  // 将逆波兰表达式 ，依次将数据和运算法放入arraylist
  public static List<String> getListString(String suffixExpression) {
    String[] split = suffixExpression.split(" ");
    ArrayList<String> list = new ArrayList<>();
    Collections.addAll(list, split);
    return list;
  }

  // 完成逆波兰表达式的运算
  public static int calculate(List<String> ls) {
    Stack<String> stack = new Stack<>();
    for (String item : ls) {
      if (item.matches("\\d+")) {
        stack.push(item);
      } else {
        // pop出两个数，并且运算 在入栈
        int num2 = Integer.parseInt(stack.pop());
        int num1 = Integer.parseInt(stack.pop());
        int res;
        switch (item) {
          case "+":
            res = num1 + num2;
            break;
          case "-":
            res = num1 - num2;
            break;
          case "*":
            res = num1 * num2;
            break;
          case "/":
            res = num1 / num2;
            break;
          default:
            throw new RuntimeException("运算符有误");
        }
        // 将res入栈
        stack.push("" + res);
      }
    }
    return Integer.parseInt(stack.pop());
  }
}
