package com.my.Algorithm.BinarySearch;

import java.util.ArrayList;
/** 二分查找 */
public class BinarySearch {
  public static void main(String[] args) {
    //        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] ints = {1, 8, 10, 89, 1000, 1000, 1000};
    System.out.println(BinarySearch2(ints, 0, ints.length - 1, 1000));
    System.out.println(dichotomy(ints, 1000));
  }

  /**
   * @param arr 原数组
   * @param left 左索引
   * @param right 又索引
   * @param data 需要查找的数据
   * @return 下标
   */
  public static int BinarySearch(int[] arr, int left, int right, int data) {
    int Median = (left + right) / 2;
    if (left > right) {
      return -1;
    }
    if (arr[Median] < data) {
      return BinarySearch(arr, Median + 1, right, data);
    }
    if (arr[Median] > data) {
      return BinarySearch(arr, left, Median - 1, data);
    } else {
      return Median;
    }
  }

  /*
   * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
   * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
   *
   * 思路分析
   * 1. 在找到mid 索引值，不要马上返回
   * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
   * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
   * 4. 将Arraylist返回
   */
  public static ArrayList<Integer> BinarySearch2(int[] arr, int left, int right, int data) {
    System.out.println("调用次数");
    int Median = (left + right) / 2;
    ArrayList<Integer> integers = new ArrayList<>();
    if (left > right) {
      return new ArrayList<>();
    }
    if (arr[Median] < data) {
      return BinarySearch2(arr, Median + 1, right, data);
    }
    if (arr[Median] > data) {
      return BinarySearch2(arr, left, Median - 1, data);
    } else {
      integers.add(Median);
      // 向左扫描
      int temp = Median - 1;
      while (temp >= 0 && arr[temp] == data) {
        integers.add(temp);
        temp -= 1;
      }
      temp = Median + 1;
      while (temp <= arr.length - 1 && arr[temp] == data) {
        integers.add(temp);
        temp += 1;
      }
    }

    return integers;
  }
  // 二分法查找非递归方式
  public static int dichotomy(int[] arr, int a) {
    int left = 0;
    int right = arr.length - 1;
    while (left <= right) {
      int Median = (left + right) / 2;
      if (arr[Median] < a) {
        left = Median + 1;

      } else if (arr[Median] > a) {
        right = Median - 1;

      } else {
        return Median;
      }
    }
    return -1;
  }
}
