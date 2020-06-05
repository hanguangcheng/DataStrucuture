package com.my.Algorithm.DynamicProgramming.Backpack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author hanguangcheng
 * @date 2020/6/4 17:28 01背包问题 给定背包容量和每个商品的价值和重量 规定每个商品只能被装一次 求最佳的装入方案
 */
public class Backpack01 {
  public static void main(String[] args) {
    ArrayList<commodity> commodities = new ArrayList<>();
    commodity commodity = new commodity(1, 1500);
    commodity commodity2 = new commodity(3, 2000);
    commodity commodity3 = new commodity(4, 3000);
    Collections.addAll(commodities, commodity, commodity2, commodity3);
    System.out.println(Backpack(commodities, 4));
  }

  public static int Backpack(ArrayList<commodity> arr, int maxWeight) {
    int[][] ints = new int[arr.size() + 1][maxWeight + 1];
    for (int i = 1; i < arr.size() + 1; i++) {
      for (int j = 1; j < maxWeight + 1; j++) {
        ints[i][0] = 0;
        ints[0][j] = 0;
        // 如果物体的重量大于背包的重量
        if (arr.get(i - 1).getWeight() > j) {
          // 将上一个背包的方案存储到此背包中
          ints[i][j] = ints[i - 1][j];
        } else {
          // 当物体的重量能放入背包时
          // 判断当选择此无 既 当前背包价值+（总重量-当前物体重量）的方案价值  和
          // 此重量上一个物体的价值既（横轴相同纵轴-1的价值）
          // 的大小  取大值
          ints[i][j] =
              Math.max(
                  ints[i - 1][j],
                  ints[i - 1][j - arr.get(i - 1).getWeight()] + arr.get(i - 1).getValue());
        }
      }
    }
    return ints[arr.size()][maxWeight];
  }
}

class commodity {

  private int weight;
  private int value;

  public commodity(int weight, int value) {
    this.value = value;
    this.weight = weight;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
