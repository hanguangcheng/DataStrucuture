package com.my;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static int lengthOfLongestSubstring(String s) {
    if (s.length() <= 1) {
      return s.length();
    }
    int result = 0;
    // 通过哈希表来快速定位重复字符的最新位置
    Map<Character, Integer> charAndIndexMap = new HashMap<>();

    // 快慢指针：快指针指向最新的下标，慢指针指向不重复的最小下标
    int fast = 0;
    int slow = 0;
    while (fast < s.length()) {
      if (charAndIndexMap.containsKey(s.charAt(fast))) {
        slow = Math.max(slow, charAndIndexMap.get(s.charAt(fast)) + 1);
      }
      charAndIndexMap.put(s.charAt(fast), fast);
      result = Math.max(result, fast - slow + 1);
      fast++;
    }

    return result;
  }

  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> hashmap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int num = target - nums[i];
      if (hashmap.containsKey(num) && hashmap.get(num) != i) {
        return new int[] {i, hashmap.get(num)};
      }
      hashmap.put(nums[i], i);
    }
    throw new IllegalArgumentException();
  }

  public static void rotate(int[] nums, int k) {
    if (nums.length <= 1) {
      return;
    }
    k = k % nums.length;
    reverse(nums, 0, nums.length);
    reverse(nums, 0, k);
    reverse(nums, k, nums.length);
    System.out.println(Arrays.toString(nums));
  }

  public static void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end - 1];
      nums[end - 1] = temp;
      start++;
      end--;
    }
  }

  public static int maxSubArray(int[] nums) {
    int max = nums[0];
    int[] numMax = new int[nums.length];
    numMax[0] = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      numMax[i] = Math.max(nums[i], nums[i] + numMax[i - 1]);
      max = Math.max(numMax[i], max);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] ints = {-10,-3,0,5,9};

    TreeNode treeNode = sortedArrayToBST(ints);
    System.out.println(1);
  }

  public static TreeNode sortedArrayToBST(int[] nums) {
    return help(0, nums, nums.length - 1);
  }

  public static TreeNode help(int left, int[] nums, int right) {
    if (left > right) {
      return null;
    }
    int mid = (left + right) / 2;
    TreeNode treeNode = new TreeNode(nums[mid]);
    treeNode.left = help(left, nums, mid - 1);
    treeNode.right = help(mid + 1, nums, right);
    return treeNode;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
      val = x;
    }
  }
  public void reverseString(char[] s) {
    for(int i =0 ;i<(s.length-1)/2;i++){
      char temp;
      temp = s[i];
      s[i]= s[s.length-1-i];
      s[s.length-1-i] = temp;
    }
  }
}
