package com.my.Algorithm.DynamicProgramming.NumberSelection;

/**
 * 给定数组选择此数组不相邻的数值加起来的最大值
 *
 * @author hanguangcheng
 * @date 2020/6/4 15:30
 */
public class NumberSelection {
  public static void main(String[] args) {
    //
    int[] arr = {1, 2, 4, 1, 7, 8, 3};
    int i = dp_opt(arr);
    System.out.println(i);
    int[] arr2 = {3, 34, 4, 12, 5, 2};
    System.out.println(dp_sunset(arr2, 13));
  }

  /**
   * 递归求解
   *
   * @param arr 数组
   * @param i 第i个元素
   * @return 此方案的最大值
   */
  public static int rec_opt(int[] arr, int i) {
    if (i == 0) {
      return arr[0];
    }
    if (i == 1) {
      return Math.max(arr[0], arr[1]);
    }
    return Math.max(arr[i] + rec_opt(arr, i - 2), rec_opt(arr, i - 1));
  }
  /** 非递归 动态规划 */
  public static int dp_opt(int[] arr) {
    int[] opt = new int[arr.length];
    opt[0] = arr[0];
    opt[1] = Math.max(arr[0], arr[1]);
    for (int i = 2; i < opt.length; i++) {
      opt[i] = Math.max(arr[i] + opt[i - 2], opt[i - 1]);
    }
    return opt[arr.length - 1];
  }

  /**
   * subset 从给定数组中的数字判断能否组成 数字s
   *
   * @param arr 给定数组
   * @param S 需要组成的数字
   * @return 是否能组成
   */
  public static boolean dp_sunset(int[] arr, int S) {
    boolean[][] subset = new boolean[arr.length][S + 1];
    for (int i = 0; i < arr.length; i++) {
      subset[i][0] = true;
      subset[0][i] = false;
    }
    subset[0][arr[0]] = true;
    for (int i = 1; i < arr.length; i++) {
      for (int s = 1; s < S + 1; s++) {
        if (arr[i] > s) {
          subset[i][s] = subset[i - 1][s];
        } else {
          boolean A = subset[i - 1][s - arr[i]];
          boolean B = subset[i - 1][s];
          subset[i][s] = A || B;
        }
      }
    }
    return subset[arr.length - 1][S];
  }
}
