package com.my.Algorithm.BinarySearch;


import java.util.ArrayList;
/**线性插值查找*/
public class InterpolationSearch {
    /**
     * 此算法为二分法的进阶，他将每次二分之一分割转化为
     * mid = left + (right-left)*(data-arr[left])/(arr[right]-arr[left])
     * 此方法是计算data在整个数组的可能位置
     * 可以吧数组看成线段进行理解
     * 应用于分布均匀的数组
     * 分布不均匀的数组使用此算法效率可能不如二分法
     */
    public static void main(String[] args) {

        int[] ints = {100, 1000, 1000, 1000, 1000, 1000, 1000};
//            int[] ints = {1, 8, 10, 89, 1000, 1000, 1000};
        System.out.println(InterpolationSearch(ints, 0, ints.length - 1, 1000));

    }

    public static ArrayList<Integer> InterpolationSearch(int[] arr, int left, int right, int data) {
        System.out.println("调用次数");
        ArrayList<Integer> integers = new ArrayList<>();
        if (arr[right] == arr[left] && arr[right] == data) {
            while (left <= right) {
                if (left==right){
                    integers.add(left);
                    return integers;
                }
                integers.add(left);
                integers.add(right);
                right--;
                left++;
            }
            return integers;
        }
        if (left > right || data < arr[0] || data > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int Median = left + (right - left) * (data - arr[left]) / (arr[right] - arr[left]);
        if (arr[Median] < data) {
            return InterpolationSearch(arr, Median + 1, right, data);
        }
        if (arr[Median] > data) {
            return InterpolationSearch(arr, left, Median - 1, data);
        } else {
            integers.add(Median);
            int temp = Median - 1;
            while (temp >= 0 && arr[temp] == data) {
                integers.add(temp);
                temp -= 1;

            }
            temp = Median + 1;
            while (temp <= arr.length - 1 && arr[temp] == data) {
                integers.add(temp);
                temp += 1;
            }
        }
        return integers;
    }
}
