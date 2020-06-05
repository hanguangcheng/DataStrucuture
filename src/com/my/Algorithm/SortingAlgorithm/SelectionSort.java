package com.my.Algorithm.SortingAlgorithm;

/** 选择排序算法 */
public class SelectionSort {
  //  时间190779ms
  public static void main(String[] args) {
    long l = System.currentTimeMillis();
    int[] a = new int[800000];
    for (int i = 0; i < a.length; i++) {
      a[i] = (int) (Math.random() * 8000000);
    }
    SelectSort(a);
    System.out.println(System.currentTimeMillis() - l);
  }

  public static void SelectSort(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      int min = a[i];
      int index = i;
      for (int j = i + 1; j < a.length; j++) {
        // 说明假定的最小值，并不是最小
        if (a[j] < min) {
          // 重置min
          min = a[j];
          // 重置minIndex
          index = j;
        }
      }
      // 将最小值，放在arr[0], 即交换
      if (index != i) {
        a[index] = a[i];
        a[i] = min;
      }
    }
  }
}
