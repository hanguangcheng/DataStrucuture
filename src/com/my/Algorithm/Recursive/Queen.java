package com.my.Algorithm.Recursive;
/** 八皇后算法 */
public class Queen {
  /** array[n] = m n表示第几个皇后 m表示 放在什么位置 从零开始 */
  int max = 8;

  int[] array = new int[max];
  static int count = 0;
  static int judgeCount = 0;

  public static void main(String[] args) {
    // 测试一把 ， 8皇后是否正确
    long l = System.currentTimeMillis();
    Queen queue8 = new Queen();
    queue8.check(0);
    System.out.printf("一共有%d解法", count);
    System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    System.out.println(System.currentTimeMillis() - l);
  }

  /** @param n 为第几个皇后 */
  private void check(int n) {
    if (n == max) {
      print();
      return;
    }
    // 依次放入皇后
    for (int i = 1; i <= max; i++) {
      array[n] = i;
      if (judge(n)) {
        check(n + 1);
      }
    }
  }

  private boolean judge(int n) {
    judgeCount++;
    for (int i = 0; i < n; i++) {
      /*
       夹角45度 |k|=tanα=（y2-y1）/（x2-x1）=1
       Math.abs(n-i)=（x2-x1）
       Math.abs(array[n]-array[i]) =（y2-y1）
      */
      if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
        return false;
      }
    }
    return true;
  }
  // 写一个方法，可以将皇后摆放的位置输出
  private void print() {
    count++;
    for (int value : array) {
      System.out.print(value + " ");
    }
    System.out.println();
  }
}
