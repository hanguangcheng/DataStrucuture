package com.my.DataSructure.Tree;

import java.util.*;
/**哈弗曼编码 解码未完成*/
public class HuffmanCoding {
  static Map<Byte, String> huffmanCodes = new HashMap<>();
  static StringBuilder stringbuilder = new StringBuilder();

  public static void main(String[] args) {
    //
    String content = "i like like like java do you like a java";
    byte[] bytes1 = huffmanZip(content);
  }

  /**
   * @param huffmanCodes 哈夫曼编码表
   * @param huffmanBytes 哈夫曼编码得到的字节数组
   * @return 原来字符串对应的数组
   */
  private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
    return null;
  }

  /**
   * 将byte转换为二进制的字符串
   *
   * @param b byte数组
   * @return 二进制的字符串
   */
  private static String bytesToBitString(byte b, boolean flag) {
    // 使用变量
    int temp = b;
    if (flag) {
      temp |= 256;
    }
    String s = Integer.toBinaryString(temp);
    if (flag) {
      return s.substring(s.length() - 8);
    } else {
      return s;
    }
  }

  /**
   * 哈夫曼编码压缩
   *
   * @param content 需要压缩的字符串
   * @return 压缩之后的数组
   */
  private static byte[] huffmanZip(String content) {
    // 将字符串转换成字节数组
    byte[] bytes = content.getBytes();
    // 创建字节次数的数组
    List<Node> nodes = getNodes(bytes);
    // 创建哈夫曼树
    Node huffmanTree = createHuffmanTree(nodes);
    // 生成哈夫曼编码表
    getCodes(huffmanTree);
    // 返回压缩之后的字节数组
    return zip(bytes);
  }

  /**
   * @param data 传入的字节数组
   * @return 返回list Node{data='32', Weight=9}, Node{data='97', Weight=5}
   */
  private static List<Node> getNodes(byte[] data) {
    ArrayList<Node> nodes = new ArrayList<>();
    HashMap<Byte, Integer> counts = new HashMap<>();
    // 将字节数组存入map
    for (byte b : data) {
      counts.merge(b, 1, Integer::sum);
    }
    // 将map中的数据加入集合
    for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
      nodes.add(new Node(entry.getKey(), entry.getValue()));
    }
    return nodes;
  }
  // 构建哈夫曼树
  private static Node createHuffmanTree(List<Node> nodes) {
    while (nodes.size() > 1) {
      Collections.sort(nodes);
      Node LeftNode = nodes.get(0);
      Node RightNode = nodes.get(1);
      Node node = new Node(null, LeftNode.Weight + RightNode.Weight);
      node.left = LeftNode;
      node.right = RightNode;
      nodes.remove(LeftNode);
      nodes.remove(RightNode);
      nodes.add(node);
    }
    return nodes.get(0);
  }
  // 生成哈夫曼编码表
  private static void getCodes(Node node) {
    getCodes(node, "", stringbuilder);
  }
  /**
   * 将哈夫曼编码表存入map中， 在遍历的过程中，需要去拼接路径，定义一个stringBuilder 存储叶子结点的路径 将 node中所有叶子结点的哈夫曼编码存放到map
   *
   * @param node 节点
   * @param code 路径 左子节点为0 右子节点为1
   * @param stringBuilder1 拼接路径
   */
  private static void getCodes(Node node, String code, StringBuilder stringBuilder1) {
    StringBuilder stringBuilder2 = new StringBuilder(stringBuilder1);
    stringBuilder2.append(code);
    if (node != null) {
      // 判断node是叶子结点还是非叶子结点
      if (node.data == null) {
        getCodes(node.left, "0", stringBuilder2);
        getCodes(node.right, "1", stringBuilder2);
      } else {
        // 找到了某个叶子结点的最后
        huffmanCodes.put(node.data, stringBuilder2.toString());
      }
    }
  }
  // 将对应的byte通过哈夫曼编码表，返回哈夫曼压缩成的byte[]
  private static byte[] zip(byte[] bytes) {
    StringBuilder stringBuilder1 = new StringBuilder();
    for (byte b : bytes) {
      stringBuilder1.append(huffmanCodes.get(b));
    }
    // 将此字符串转换成byte数组
    // 统计返回的 byte huffmanCodeBytes 长度
    int len;
    if (stringBuilder1.length() % 8 == 0) {
      len = stringBuilder1.length() / 8;
    } else {
      len = stringBuilder1.length() / 8 + 1;
    }
    // 创建一个存储压缩之后的byte数组
    byte[] by = new byte[len];
    // 记录是第几个byte
    int index = 0;
    for (int i = 0; i < stringBuilder1.length(); i += 8) {
      String strByte;
      if (i + 8 > stringBuilder1.length()) {
        strByte = stringBuilder1.substring(i);
      } else {
        strByte = stringBuilder1.substring(i, i + 8);
      }
      // 将stringbyte转换成byte数组 放入by
      by[index] = (byte) Integer.parseInt(strByte, 2);
      index++;
    }
    return by;
  }

  static class Node implements Comparable<Node> {
    Byte data;
    Integer Weight;
    Node left;
    Node right;

    public Node(Byte data, Integer weight) {
      this.data = data;
      Weight = weight;
    }

    @Override
    public String toString() {
      return "Node{" + "data='" + data + '\'' + ", Weight=" + Weight + '}';
    }

    @Override
    public int compareTo(Node o) {
      return this.Weight - o.Weight;
    }
  }
}
