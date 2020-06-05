package com.my.DataSructure.Queue;


import java.util.Arrays;

public class ArrayQueue {
    //队列最大长度
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //用于模拟队列，存放数据的数组
    private final int[] arr;
    //构造器

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        //指向队列头的前一个位置
        front = -1;
        //指向队列尾
        rear = -1;
    }
    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    //判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }
    //入队
    public void addQ(int n){
        if (isFull()){
            System.out.println("队列已经满了");
            return;
        }
        rear++;
        arr[rear] =n;
    }
    //出队
    public int getQ(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }
    public void showQueue() {
        if (isEmpty()){
            throw new RuntimeException("队列是空的");
        }
        System.out.println(Arrays.toString(arr));
    }
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }
}
