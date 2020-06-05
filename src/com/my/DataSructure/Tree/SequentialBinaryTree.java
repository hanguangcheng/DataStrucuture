package com.my.DataSructure.Tree;
/** 顺序存储二叉树 */
public class SequentialBinaryTree {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
    arrBinaryTree.preOrder();
  }
  // 实现顺序存储二叉树
  static class ArrBinaryTree {
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
      this.arr = arr;
    }

    public void preOrder() {
      this.preOrder(0);
    }
    // 中序 后序只需要更换输出位置即可
    /** @param index 数组下标 */
    public void preOrder(int index) {
      if (arr == null || arr.length == 0) {
        System.out.println("数组为空,不能进行前序遍历");
        return;
      }
      System.out.println(arr[index]);

      if (index * 2 + 1 < arr.length) {
        preOrder(2 * index + 1);
      }
      if (index * 2 + 2 < arr.length) {
        preOrder(2 * index + 2);
      }
    }
  }
}
