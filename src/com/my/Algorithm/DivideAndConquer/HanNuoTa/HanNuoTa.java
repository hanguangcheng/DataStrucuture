package com.my.Algorithm.DivideAndConquer.HanNuoTa;
/**求解汉诺塔问题*/
public class HanNuoTa {
  public static void main(String[] args) {
    HanNuoTa(10,'A','B','C');
    System.out.println(d);
  }
  // 汉诺塔的移动方式
  // 分治法解决

  /**
   * @param num 有几个盘子
   * @param a A柱子
   * @param b B柱子
   * @param c C柱子
   *          移动思路
   *          当num大于2的时
   *          1.将上部分的塔从A ->B
   *          2.将最下部的塔从 A->C
   *          3.将B上边的塔移动到C  B->C
   */
  private static int d = 0;
  public static void HanNuoTa(int num, char a, char b, char c) {
    if (num == 1) {
      d++;
      System.out.println("第1个盘从 " + a + "->" + c);
    } else {
      // 如果num >2 将整体看做两个盘子 即最下面的盘子和上面的所有盘子
      // 移动过程会使用到C  其他是吧其移动到B
      HanNuoTa(num - 1, a, c, b);
      // 将最下面的从A移动到C
      System.out.println("第" + num + "个盘从" + a + "->" + c);
      d++;
      HanNuoTa(num-1,b,a,c);
    }
  }
}
