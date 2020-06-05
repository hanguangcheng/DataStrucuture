package com.my;

public class Main {

    public static void main(String[] args) {
        String str = "a";
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            str += "c";
        }
        System.out.println("加号所花费的时间：");
        System.out.println(System.currentTimeMillis()-time);
        String str2 = "a";
        time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            str2.concat("c");
        }
        System.out.println("cancat方法所花费的时间：");
        System.out.println(System.currentTimeMillis()-time);
        time = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder("a");
        for (int i = 0; i < 100; i++) {
            stringBuilder.append("c");
        }
        String str3 = stringBuilder.toString();
        System.out.println("StringBuilder的append方法：");
        System.out.println(System.currentTimeMillis()-time);
    }
}
