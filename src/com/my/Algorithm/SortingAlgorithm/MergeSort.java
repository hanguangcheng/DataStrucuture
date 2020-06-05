package com.my.Algorithm.SortingAlgorithm;

/** 归并排序算法 */
public class MergeSort {
  public static void main(String[] args) {
    // 运行时间134ms
    long l = System.currentTimeMillis();
    int[] arr = new int[800000];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * 8000000);
    }
    int[] temp = new int[arr.length];
    MergeSort(arr, 0, arr.length - 1, temp);
    System.out.println(System.currentTimeMillis() - l);
  }

  public static void MergeSort(int[] arr, int left, int right, int[] temp) {
    if (left < right) {
      // 中间的索引值
      int mid = (left + right) / 2;
      // 先分解左边的数组
      MergeSort(arr, left, mid, temp);
      // 分解右边的数组
      MergeSort(arr, mid + 1, right, temp);
      // 合并
      SyntheticArray(arr, left, mid, right, temp);
    }
  }

  /**
   * @param arr 原数组
   * @param left 左边有序序列的初始索引
   * @param right 右边的索引
   * @param mid 中间索引
   * @param temp 中转数组
   */
  public static void SyntheticArray(int[] arr, int left, int mid, int right, int[] temp) {
    int i = left; // 左边有序数组的下标
    int j = mid + 1; // 右边有序数组的下标
    int t = 0; // temp的当前索引值
    /*
       (一)
       先把左右两边(有序)的数据按照规则填充到temp数组
       直到左右两边的有序序列，有一边处理完毕为止
    */
    while (i <= mid && j <= right) {
      /*
           如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
          即将左边的当前元素，填充到 temp数组
          然后 t++, i++
      */
      if (arr[i] <= arr[j]) {
        temp[t] = arr[i];
        i++;
      } else {
        temp[t] = arr[j];
        j++;
      }
      t++;
    }
    //  2.将左右的剩余数据拷贝到中转数组中
    // 左边的有序序列还有剩余的元素，就全部填充到temp
    while (i <= mid) {
      temp[t] = arr[i];
      i++;
      t++;
    }
    // 右边的有序序列还有剩余的元素，就全部填充到temp
    while (j <= right) {
      temp[t] = arr[j];
      j++;
      t++;
    }
    //  3.将中转数组拷贝到原数组中
    t = 0;
    int tempLeft = left;
    while (tempLeft <= right) {
      arr[tempLeft] = temp[t];
      tempLeft++;
      t++;
    }
  }
}
