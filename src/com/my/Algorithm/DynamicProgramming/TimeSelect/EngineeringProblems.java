package com.my.Algorithm.DynamicProgramming.TimeSelect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 使用动态规划寻找最优解
 * 参考视频：https://www.bilibili.com/video/BV18x411V7fm?from=search&seid=11625479885489424315
 */
public class EngineeringProblems {
  // 思路分析  首先进行填表 为
  public static void main(String[] args) {
    ArrayList<Integer[]> ints = new ArrayList<>();
    //    // 数组中第一个数据代表价值，第二个和第三个代表时间为双开区间
    Integer[] a = {5, 1, 4};
    Integer[] b = {1, 3, 5};
    Integer[] c = {8, 0, 6};
    Integer[] d = {4, 4, 7};
    Integer[] e = {6, 3, 8};
    Integer[] f = {3, 5, 9};
    Integer[] g = {2, 6, 10};
    Integer[] h = {4, 8, 11};
    Collections.addAll(ints, a, b, c, d, e, f, g, h);
    Job[] jb = new Job[8];
    for (int i = 0; i < jb.length; i++) {
      jb[i] = new Job();
      jb[i].payment = ints.get(i)[0];
      jb[i].start = ints.get(i)[1];
      jb[i].end = ints.get(i)[2];
    }
    int[][] arr = new int[ints.size()][3];
    System.out.println(prev(arr, jb));
    for (int[] ints1 : arr) {
      System.out.println(Arrays.toString(ints1));
    }
  }

  public static int prev(int[][] arr, Job[] job) {
    // 初始化第一个方案的数据
    arr[0][1] = 0;
    arr[0][2] = job[0].payment;
    for (int i = 0; i < arr.length; ++i) {
      // 将第一个job填入表中 为第二列调用
      arr[i][0] = i + 1;
      if (i > 0) {
        // 从第二个job开始寻找最接近job[i]中从后往前中满足情况的job[j]
        for (int j = i - 1; j >= 0; j--) {
          if (job[j].end <= job[i].start || job[j].start >= job[i].end) {
            arr[i][1] = j + 1;
            break;
          }
        }
        // 寻找最大价值
        if (arr[i][1] == 0) {
          // 比较当前价值和上一个方案的价值大小
          arr[i][2] = Math.max(job[i].payment, arr[i - 1][2]);

        } else {
          // 比较当前方案和上一个方案的价值
          // arr[arr[i][1]-1][2]
          // arr[i][1]选择当前能和哪一个进行组合，因为数组是从0开始方案是从1开始所以需要减去1
          int a = job[i].payment + arr[arr[i][1] - 1][2];
          int b = arr[i - 1][2];
          arr[i][2] = Math.max(a, b);
        }
      }
    }
    return arr[arr.length - 1][arr[0].length - 1];
  }

  static class Job {
    int payment;
    int start;
    int end;

    @Override
    public String toString() {
      return "job{" + "payment=" + payment + ", start=" + start + ", end=" + end + '}';
    }
  }
}
