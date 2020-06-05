package com.my.Algorithm.SortingAlgorithm;

//希尔排序算法
public class ShellSort {
    //位移法运行时间261ms
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int[] a = new int[800000];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 8000000);
        }
        shellSort2(a);
        System.out.println(System.currentTimeMillis() - l);
//        int[] b = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort2(b);
//        System.out.println(Arrays.toString(b));

    }

    //交换法希尔排序
    public static void shellSort(int[] a) {
        int temp;
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < a.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (a[j] > a[j + gap]) {
                        temp = a[j];
                        a[j] = a[j + gap];
                        a[j + gap] = temp;
                    } else {
                        break;
                    }
                }
            }

        }
    }

    //移位法希尔排序
    public static void shellSort2(int[] a) {
        int temp;
        int index;
        //增量gap，并且逐步缩小增量
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            //从gap个元素，逐个对组内元素进行插入排序
            for (int i = gap; i < a.length; i++) {
                index = i;
                temp = a[index];
                if (temp < a[index - gap]) {
                    while (index - gap >= 0 && temp < a[index - gap]) {
                        //移动
                        a[index] = a[index - gap];
                        index -= gap;
                    }
                }
                //当退出while后，就给temp找到插入的位置
                a[index] = temp;

            }
        }
    }
}
