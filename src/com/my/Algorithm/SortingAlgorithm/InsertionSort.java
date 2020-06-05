package com.my.Algorithm.SortingAlgorithm;
/** 插入排序算法 */
public class InsertionSort {
  public static void main(String[] args) {
    // 69517ms
    long l = System.currentTimeMillis();
    int[] a = new int[800000];
    for (int i = 0; i < a.length; i++) {
      a[i] = (int) (Math.random() * 8000000);
    }
    insertSort(a);
    System.out.println(System.currentTimeMillis() - l);
  }

  public static void insertSort(int[] a) {
    int insertVal;
    int insertIndex;
    // 使用for循环来把代码简化
    for (int i = 1; i < a.length; i++) {
      // 定义待插入的数
      insertVal = a[i];
      // 即arr[1]的前面这个数的下标
      insertIndex = i - 1;
      /*
         给insertVal 找到插入的位置
         说明
         1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
         2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
         3. 就需要将 arr[insertIndex] 后移
      */
      while (insertIndex >= 0 && insertVal < a[insertIndex]) {
        a[insertIndex + 1] = a[insertIndex];
        insertIndex--;
      }
      /*
         当退出while循环时，说明插入的位置找到, insertIndex + 1
         举例：理解不了，我们一会 debug
         这里我们判断是否需要赋值
      */
      a[insertIndex + 1] = insertVal;
    }
  }
}
