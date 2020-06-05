package com.my.DataSructure.Tree;

import java.util.Collections;

import java.util.ArrayList;
/**哈弗曼树*/
public class HuffmanTree {
  public static void main(String[] args) {
    //
    int[] arr = {13, 7, 8, 3, 29, 6, 1};
    System.out.println(HuffmanTree(arr));
  }

  public static Node HuffmanTree(int[] arr) {
    // 1.遍历arr数组
    // 2 . 将元素构建成node
    // 3. 将node放入到arraylist
    ArrayList<Node> nodes = new ArrayList<>();
    for (int value : arr) {
      nodes.add(new Node(value));
    }

    while (nodes.size() > 1) {
      Collections.sort(nodes);
      //最小值左节点
      Node LeftNode = nodes.get(0);
      //第二小右节点
      Node RightNode = nodes.get(1);
      //创建父节点
      Node parent = new Node((LeftNode.value + RightNode.value));
      //加入左孩子
      parent.left = LeftNode;
      //加入又孩子
      parent.right = RightNode;
      nodes.remove(LeftNode);
      nodes.remove(RightNode);
      //新的二叉树加入list
      nodes.add(parent);
    }
    return nodes.get(0);
  }

  // 为了使Node可排序
  static class Node implements Comparable<Node> {
    // 节点权值
    int value;
    Node left;
    Node right;

    public Node(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "Node{" + "value=" + value + '}';
    }

    @Override
    public int compareTo(Node o) {
      // 表示从小到大排列
      return this.value - o.value;
    }
  }
}
