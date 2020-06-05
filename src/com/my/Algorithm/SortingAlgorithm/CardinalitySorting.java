package com.my.Algorithm.SortingAlgorithm;
/** 基数排序算法 */
public class CardinalitySorting {
  // 基数排序需要大额内存空间
  public static void main(String[] args) {
    // 时间103ms
    //        int arr[] = {53, 3, 542, 748, 14, 214,0};
    long l = System.currentTimeMillis();
    int[] arr = new int[800000];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * 8000000);
    }
    CardinalitySorting(arr);
    System.out.println(System.currentTimeMillis() - l);
    //        System.out.println(Arrays.toString(arr));
  }

  public static void CardinalitySorting(int[] arr) {
    int max = arr[0];
    // 1. 得到数组中最大的数的位数
    for (int i : arr) {
      if (i > max) {
        max = i;
      }
    }
    // 得到最大数是几位数
    int maxLength = (max + "").length();
    /*
        定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
       说明
       1. 二维数组包含10个一维数组
       2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
       3. 名明确，基数排序是使用空间换时间的经典算法
    */
    // 创建10个桶还存放数据
    int[][] bucket = new int[10][arr.length];
    /*
       为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
       可以这里理解
       比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
    */
    int[] bucketElementCount = new int[10];
    for (int k = 0, n = 1; k < maxLength; k++, n *= 10) {
      {
        // 进行桶放入
        for (int value : arr) {
          // 取出每个元素的对应位的值
          int Digitdata = (value / n) % 10;
          bucket[Digitdata][bucketElementCount[Digitdata]] = value;
          bucketElementCount[Digitdata]++;
        }
        // 按照桶的顺序取出并复制到源数组
        int index = 0;
        // 遍历每一桶，并将桶中是数据，放入到原数组
        for (int i = 0; i < bucket.length; ++i) {
          // 如果桶中，有数据，我们才放入到原数组
          if (bucketElementCount[i] != 0) {
            // 循环该桶即第k个桶(即第k个一维数组), 放入
            for (int j = 0; j < bucketElementCount[i]; j++) {
              // 取出元素放入到arr
              arr[index++] = bucket[i][j];
            }
          }
          // 第i+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
          bucketElementCount[i] = 0;
        }
      }
    }
  }
}
