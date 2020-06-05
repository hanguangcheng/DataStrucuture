package com.my.Algorithm.Recursive;

/**简单迷宫回溯算法*/
public class Maze {
    public static void main(String[] args) {
        //用二维数组模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //先把障碍物的位置设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("地图的结构");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯 找路
        setWay(map,1,1);
        //输出新的地图
        System.out.println("新的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用回溯寻找路
     *
     * @param map 表示地图
     * @param i   从哪个位置开始寻找
     * @param j   同上
     * @return 如果寻找成功返回true 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //如果当前的节点没有走过
            if (map[i][j] == 0) {
                //策略 下-右-上-左
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }

}
