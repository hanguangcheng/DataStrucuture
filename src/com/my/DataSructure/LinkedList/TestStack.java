package com.my.DataSructure.LinkedList;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        while (stack.size()>0){
            System.out.println(stack.pop());
        }

    }
}
