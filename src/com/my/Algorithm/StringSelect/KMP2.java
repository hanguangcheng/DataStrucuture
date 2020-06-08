package com.my.Algorithm.StringSelect;

import java.util.Arrays;

/**
 * KMP 修改版
 *
 * @author hanguangcheng
 * @date 2020/6/5 16:30
 */
public class KMP2 {

  public static void main(String[] args) {
    String str1 = "ABABABABCABAAB";
    String str2 = "ABABCABAA";
    // String str2 = "BBC";

    int[] next = kmpNext(str2);
    System.out.println("next=" + Arrays.toString(next));

    int index = kmpSearch(str1, str2, next);
    System.out.println("index=" + index); // 15了
  }

  // 写出我们的kmp搜索算法
  /**
   * @param str1 源字符串
   * @param str2 子串
   * @param next 部分匹配表, 是子串对应的部分匹配表
   * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
   */
  public static int kmpSearch(String str1, String str2, int[] next) {

    // 遍历
    for (int i = 0, j = 0; i < str1.length(); i++) {
      while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
        //将j移动到j位置对应的部分匹配表数值的位置
        j = next[j - 1];
      }

      if (str1.charAt(i) == str2.charAt(j)) {
        j++;
      }
      if (j == str2.length()) { // 找到了 // j = 3 i
        return i - j + 1;
      }
    }
    return -1;
  }

  // 获取到一个字符串(子串) 的部分匹配值表
  public static int[] kmpNext(String dest) {
    int[] next = new int[dest.length()];
    next[0] = 0;
    // i为需要填入部分匹配表中的位置
    // j表示最长前后缀
    for (int i = 1, j = 0; i < dest.length(); i++) {
      if (j > 0 && dest.charAt(i) != dest.charAt(j)) {
        j = next[j - 1];
      }
      if (dest.charAt(i) == dest.charAt(j)) {
        j++;
      }
      next[i] = j;
    }
    return next;
  }
}
