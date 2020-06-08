package com.my.Algorithm.StringSelect;

import java.util.Arrays;

/**
 * 字符串匹配算法 KMP算法
 * 基于概念写的垃圾KMP
 * @author hanguangcheng
 * @date 2020/6/5 14:02
 * 可参考资料
 * https://www.cnblogs.com/zzuuoo666/p/9028287.html
 * 思路: 首先做出部分匹配表
 * 使用前缀和后缀数组 比较共同的部分
 * 主串 A = BBC ABCDAB ABCDABCDABDE
 * 串 B = ABCDABD
 * 如：ABCDAB
 *  前缀 ：A,AB,ABC,ABCD,ABCDA
 *  后缀:  B,AB,DAB,CDAB,BCDAB
 *
 *  相同部分为AB 其长度为2
 *  在串中 ABCDAB的部分匹配值为2
 *  根据此构建串的部分匹配表
 *  将B和A进行对比当发现B首位置相同和A的某个位置，继续让B和A的后边比较，当发现B不同的时候，根据已经相同的长度n
 *  和这个n对应的串查询部分匹配表得到m
 *  B串移动的距离为 n-m
 */
public class KMP {
  public static void main(String[] args) {
    String str1 = "BBC ABCDAB ABCDABCDABDE";
    String str2 = "ABCDAB";
    String str3 = "ABCDABD";
    String str4 = "ABCDABDEF";
    // 构建部分匹配表
    int[] next = PartialMatchTable(str2);
    int[] next1 = PartialMatchTable(str3);
    int[] next2 = PartialMatchTable(str4);
    print(str1, str2, next);
    print(str1, str3, next1);
    print(str1, str4, next2);
  }

  /**
   * 构建部分匹配表
   *
   * @param str 需要进行构建的串
   * @return 部分匹配表
   */
  public static int[] PartialMatchTable(String str) {
    // 构建部分匹配表
    // 创建一个部分匹配表的数组 0位不适用 从1位开始 数组下表 = str2部分匹配的串长度
    // 如 next[2] = AB的部分匹配值(AB长度为2)
    int[] next = new int[str.length() + 1];
    // 从长度为2开始构建 因为长度为1的没有前缀和后缀
    int k = 2;
    while (k <= str.length()) {
      // 将需要进行部分匹配的串截取出来
      String substring = str.substring(0, k);
      int subL = substring.length();
      // 创建前缀和后缀数组为了方便 我们从下标为1开始使用
      String[] Prefix = new String[subL];
      String[] suffix = new String[subL];

      for (int i = 1, j = subL - 1; i < subL; i++, j--) {
        // 为了方便比较所以讲位数相同的下标值也相同
        // 截取第一个前缀
        Prefix[i] = substring.substring(0, i);
        // 截取第一个后缀
        suffix[j] = substring.substring(i);
      }
      // 进行比较如果相同则在对应的部分匹配表中存入串的长度
      for (int i = 1; i < subL; i++) {
        if (Prefix[i].equals(suffix[i])) {
          next[subL] = suffix[i].length();
        }
      }
      k++;
    }
    return next;
  }

  /**
   * @param str1 父串
   * @param str2 子串
   * @param next 部分匹配表
   * @return 第一次出现的小标
   */
  public static int KMPCompare(String str1, String str2, int[] next) {
    if (str2.length() > str1.length()) {
      throw new IllegalArgumentException("子串长度大于父串");
    }
    int i = 0;
    int j = 0;
    while (true) {
      while (j < str2.length()) {
        // 超过父串长度则并且返回没有找到
        if (i >= str1.length()) {
          return -1;
        }
        // 如果子串的当前字符和父串相同
        if (str1.charAt(i) == str2.charAt(j)) {
          // 比较下一个
          i++;
          j++;
        } else {
          // 当不同的时候且部分匹配为0 则向子串向后
          if (next[j] == 0) {
            i++;
          } else {
            // 否则根据部分匹配进行移动
            i += j - next[j];
          }
          // 重置子串继续从第一个字符开始比较
          j = 0;
          break;
        }
      }
      // 如果子串的全部长度都比较完成并且全部相等 返回小标
      if (j == str2.length()) {
        return i - str2.length();
      }
    }
  }

  public static void print(String str1, String str2, int[] next) {
    System.out.println(Arrays.toString(next));
    int a = KMPCompare(str1, str2, next);
    System.out.println(a);
    if (a != -1) {
      System.out.println(str1.substring(a, a + str2.length()));
    }
  }
}
