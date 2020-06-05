package com.my.DataSructure.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
/**图*/
public class Graph {
  public static void main(String[] args) {
    // 顶点个数
    int n = 5;
    String[] VertexValue = {"A", "B", "C", "D", "E"};
    Graph graph = new Graph(n);
    // 添加顶点
    for (String s : VertexValue) {
      graph.insertVertex(s);
    }
    // 添加边
    // A-B A-C B-C B-D B-E
    graph.insertEdge(0, 1, 1);
    graph.insertEdge(0, 2, 1);
    graph.insertEdge(1, 2, 1);
    graph.insertEdge(1, 3, 1);
    graph.insertEdge(1, 4, 1);
    graph.showGraph();
    //    graph.dfs();
    graph.bfs();
  }
  // 存储顶点的集合
  private final ArrayList<String> vertexList;
  // 存储图对应的矩阵
  private final int[][] edges;
  // 存储变得数量
  private int numOfEdges;
  // 某个节点是否被访问过
  private final boolean[] isVisited;

  public Graph(int n) {
    edges = new int[n][n];
    numOfEdges = n;
    vertexList = new ArrayList<>(n);
    isVisited = new boolean[n];
  }
  // 深度优先遍历 重载目的为了防范没有连接的图
  public void dfs() {
    for (int i = 0; i < getNumOfVertex(); i++) {
      if (!isVisited[i]) {
        dfs(isVisited, i);
      }
    }
  }
  // 广度优先遍历
  public void bfs() {
    for (int i = 0; i < getNumOfVertex(); i++) {
      if (!isVisited[i]) {
        bfs(isVisited, i);
      }
    }
  }

  private void bfs(boolean[] isVisited, int i) {
    // 表示队列的头结点对应下标
    int u;
    // 邻接结点w
    int w;
    // 记录访问顺序
    LinkedList<Integer> queue = new LinkedList<>();
    // 输出遍历到的节点
    System.out.print(getValueByIndex(i) + "->");
    // 将当前节点设置为已经被访问
    isVisited[i] = true;
    // 将此节点入队
    queue.add(i);
    while (!queue.isEmpty()) {
      // 取出队列头结点的下表
      u = queue.removeFirst();
      // 找到u的下一个节点
      w = getFirstNeighbor(u);
      if (!isVisited[w]) {
        System.out.print(getValueByIndex(w) + "->");
        isVisited[w] = true;
        queue.addLast(w);
      }
      // 以u为前驱点，找w后面的下一个邻结点
      getNextNeighbor(u, w);
    }
  }
  /**
   * @param isVisited 表示是否被遍历过得数组
   * @param i 当前遍历的数据
   */
  public void dfs(boolean[] isVisited, int i) {
    // 输出遍历到的节点
    System.out.print(getValueByIndex(i) + "->");
    // 将当前节点设置为已经被访问
    isVisited[i] = true;
    // 查找结点i的第一个邻接结点w
    int w = getFirstNeighbor(i);
    while (w != -1) {
      // 如果没有被访问过
      if (!isVisited[w]) {
        // 访问w
        dfs(isVisited, w);
      }
      // 如果w已经被访问过，i节点的中不包括w的第一个节点
      w = getNextNeighbor(i, w);
    }
  }

  /**
   * 根据前一个邻接结点的下标来获取下一个邻接结点
   *
   * @param v1 当前节点
   * @param v2 当前节点被访问过得邻近节点
   * @return 下一个邻近节点
   */
  private int getNextNeighbor(int v1, int v2) {
    // 从V2的下一个节点开始寻找
    for (int i = v2 + 1; i < getNumOfVertex(); i++) {
      if (edges[v1][i] == 1) {
        return i;
      }
    }
    return -1;
  }

  // 查找当前节点的第一个相邻节点
  private int getFirstNeighbor(int index) {
    for (int i = 0; i < getNumOfVertex(); i++) {
      if (edges[index][i] == 1) {
        return i;
      }
    }
    return -1;
  }

  // 插入节点
  public void insertVertex(String vertex) {
    vertexList.add(vertex);
  }
  // 返回结点的个数
  public int getNumOfVertex() {
    return vertexList.size();
  }
  // 得到边的数量
  public int getNumOfEdges() {
    return numOfEdges;
  }
  // 返回节点i对应的数据
  public String getValueByIndex(int i) {
    return vertexList.get(i);
  }
  // 显示矩阵
  public void showGraph() {
    for (int[] link : edges) {
      System.out.println(Arrays.toString(link));
    }
  }
  /**
   * 添加边
   *
   * @param x 矩阵图的x轴 第一个顶点 0
   * @param y 矩阵图的y轴 第二个顶点 1
   * @param weight 权值
   *                  Y   0    1
   *                _____________
   *              X |    A   B  |
   *              0 | A  0   1  |
   *              1 | B  1   0  |
   *                |___________|
   */
  public void insertEdge(int x, int y, int weight) {
    edges[x][y] = weight;
    edges[y][x] = weight;
    numOfEdges++;
  }
}
