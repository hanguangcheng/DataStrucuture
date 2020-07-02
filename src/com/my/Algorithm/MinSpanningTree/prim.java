package com.my.Algorithm.MinSpanningTree;

import java.util.Arrays;

/**
 * 普利姆算法 在图结构中求最小生成树 应该是贪心算法的一种应用
 *
 * @author hanguangcheng
 * @date 2020/6/15 13:46
 */
public class prim {
  public static void main(String[] args) {
    char[] data = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    int vertex = data.length;
    // 邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
    int[][] weight =
        new int[][] {
          {10000, 5, 7, 10000, 10000, 10000, 2},
          {5, 10000, 10000, 9, 10000, 10000, 3},
          {7, 10000, 10000, 10000, 8, 10000, 10000},
          {10000, 9, 10000, 10000, 10000, 4, 10000},
          {10000, 10000, 8, 10000, 10000, 5, 4},
          {10000, 10000, 10000, 4, 5, 10000, 6},
          {2, 3, 10000, 10000, 4, 6, 10000},
        };
    // 创建 MGraph
    MGraph mGraph = new MGraph(vertex);
    MinTree minTree = new MinTree();
    minTree.createGraph(mGraph, vertex, data, weight);
    minTree.showGraph(mGraph);
    minTree.prim(mGraph, 1); //
  }
}
// 创建最小生成树
class MinTree {
  /**
   * @param graph 图对象
   * @param vertex 图的顶点个数
   * @param data 图的顶点值
   * @param weight 图的邻接矩阵
   */
  public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
    int i, j;
    for (i = 0; i < vertex; i++) {
      graph.data[i] = data[i];
      for (j = 0; j < vertex; j++) {
        graph.weight[i][j] = weight[i][j];
      }
    }
  }

  /**
   * 输出图
   *
   * @param graph 图对象
   */
  public void showGraph(MGraph graph) {
    for (int[] ints : graph.weight) {
      System.out.println(Arrays.toString(ints));
    }
  }

  /**
   * 构建最小生成树
   *
   * @param graph 图
   * @param v 顶点 如‘A’->0
   */
  public void prim(MGraph graph, int v) {
    // 表示顶点是否被访问过
    int[] visited = new int[graph.vertex];
    // 将当前节点标记为已经被访问
    visited[v] = 1;
    // h1 和 h2 记录两个顶点的下标
    int h1 = -1;
    int h2 = -1;
    int minWeight = 10000; // 将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
    for (int k = 1; k < graph.vertex; k++) { // 因为有 graph.vertex// 顶点，普利姆算法结束后，有 graph.vertex-1边

      // 这个是确定每一次生成的子图 ，和哪个结点的距离最近
      for (int i = 0; i < graph.vertex; i++) {// i结点表示被访问过的结点
        for (int j = 0; j < graph.vertex; j++) { // j结点表示还没有访问过的结点
          if (visited[i] != 1) {
            break;
          }
          if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
            // 替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
            minWeight = graph.weight[i][j];
            h1 = i;
            h2 = j;
          }
        }
      }
      // 找到一条边是最小
      System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
      // 将当前这个结点标记为已经访问
      visited[h2] = 1;
      // minWeight 重新设置为最大值 10000
      minWeight = 10000;
    }
  }
}

class MGraph {
  // 在图中的节点个数
  int vertex;
  // 存放节点数据
  char[] data;
  // 存放边，既邻接矩阵
  int[][] weight;

  public MGraph(int vertex) {
    this.vertex = vertex;
    data = new char[vertex];
    weight = new int[vertex][vertex];
  }
}
