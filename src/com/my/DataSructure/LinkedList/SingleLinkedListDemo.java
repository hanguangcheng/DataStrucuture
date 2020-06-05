package com.my.DataSructure.LinkedList;


import java.util.Stack;
/**单链表*/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode hero7 = new HeroNode(7, "林冲", "豹子头");
        HeroNode hero8 = new HeroNode(8, "林冲", "豹子头");
        singleLinkedList singleLinkedList = new singleLinkedList();
        singleLinkedList singleLinkedList1 = new singleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        //测试修改节点的代码

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero7);
        singleLinkedList.addByOrder(hero6);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero8);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList singleLinkedList2 = MergeLinkedList(singleLinkedList, singleLinkedList1);
        singleLinkedList2.list();
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println(findLastIndexNode(singleLinkedList.gethead(), 1));
//        reverSetList(singleLinkedList.gethead());
//        singleLinkedList.list();
////        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        System.out.println("删除后的链表情况~~");

//        singleLinkedList.list();
//        reverstPrint(singleLinkedList.gethead());
//        System.out.println(getLength(singleLinkedList.gethead()));


    }

    /**
     * @param heroNode 需要判断的节点
     * @param index    倒数第几个节点
     * @return 倒数第几个节点
     */
    public static HeroNode findLastIndexNode(HeroNode heroNode, int index) {
        if (heroNode.next == null) {
            return null;
        }
        int size = getLength(heroNode);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = heroNode.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * @param heroNode 需要判断的链表
     * @return 有效节点个数
     */
    public static int getLength(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;
        }
        int length = 0;
        while (heroNode.next != null) {
            heroNode = heroNode.next;
            length++;
        }
        return length;
    }

    /** 两个单链表和成一个链表并且有序排列
     * @param singleLinkedList1 第一个单链表
     * @param singleLinkedList2 第二个单链表
     */
    public static singleLinkedList MergeLinkedList(singleLinkedList singleLinkedList1, singleLinkedList singleLinkedList2) {
        singleLinkedList singleLinkedList = new singleLinkedList();
        if (singleLinkedList1 == null || singleLinkedList2 == null) {
            throw new RuntimeException("其中一个链表为空为空");
        }
        HeroNode cru1 = singleLinkedList1.head.next;
        HeroNode cru2 = singleLinkedList2.head.next;
        HeroNode cru;
        while (cru1 != null || cru2 != null) {
            if (cru1 == null) {
                singleLinkedList.add(cru2);
                break;
            }
            if (cru2 == null) {
                singleLinkedList.add(cru2);
                break;
            }
            if (cru1.no < cru2.no) {
                if (cru1.next == null) {
                    singleLinkedList.add(cru1);
                    cru1 = cru1.next;
                } else {
                    cru = cru1.next;
                    cru1.next = null;
                    singleLinkedList.add(cru1);
                    cru1 = cru;
                }
            } else if (cru1.no > cru2.no) {
                if (cru2.next == null) {
                    singleLinkedList.add(cru2);
                    cru2 = cru2.next;
                } else {
                    cru = cru2.next;
                    cru2.next = null;
                    singleLinkedList.add(cru2);
                    cru2 = cru;
                }
            }
        }
        return singleLinkedList;
    }

    /**
     * 逆序打印单链表 使用栈的方式
     *
     * @param heroNode 单链表
     */
    public static void reverstPrint(HeroNode heroNode) {
        Stack<HeroNode> stack = new Stack<>();
        if (heroNode.next == null || heroNode.next.next == null) {
            System.out.println(heroNode.toString());
            return;
        }
        HeroNode cur = heroNode.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //单链表翻转
    public static void reverSetList(HeroNode heroNode) {
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        //定义一个辅助节点帮助遍历
        HeroNode cur = heroNode.next;
        //指向当前节点的下一个节点
        HeroNode next;
        HeroNode reverSetHead = new HeroNode(0, "", "");
        //遍历原来链表每次遍历将其取出添加到reverSetHead的头部
        while (cur != null) {
            next = cur.next;
            cur.next = reverSetHead.next;
            reverSetHead.next = cur;
            cur = next;
        }
        heroNode.next = reverSetHead.next;
    }

    //定义一个单链表
    static class singleLinkedList {
        public HeroNode gethead() {
            return head;
        }

        //先初始化一个头结点
        private final HeroNode head = new HeroNode(0, "", "");

        //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
        //(如果有这个排名，则添加失败，并给出提示)
        public void addByOrder(HeroNode heroNode) {
            HeroNode temp = head;
            //标识添加的编号是否存在
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no > heroNode.no) {//位置找到向后添加
                    break;
                } else if (temp.next.no == temp.no) {
                    //说明编号已经存在
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
            } else {
                //插入链表temp后边
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        //修改节点信息 根据no信息修改
        public void update(HeroNode heroNode) {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            boolean flag = false;
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.no == heroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.name = heroNode.name;
                temp.nickName = heroNode.nickName;
            } else {
                System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
            }
        }

        //删除节点信息
        public void del(int no) {
            if (head.next == null) {
                System.out.println("链表为空");
            }
            HeroNode temp = head;
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no == no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next = temp.next.next;
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
            }
        }

        //添加节点信息
        //当不考虑编号顺序时，找到当前列表的最后节点，将最后节点的next域指向新的节点
        public void add(HeroNode heroNode) {
            //因为头结点不能动，所以需要一个辅助节点
            HeroNode temp = head;
            while (temp.next != null) {
                //找到链表最后
                //如果没有找到，temp后移
                temp = temp.next;
            }
            temp.next = heroNode;
        }

        public void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //因为头结点不能动需要辅助变量进行遍历
            HeroNode temp = head.next;
            while (temp != null) {
                //输出节点信息
                System.out.println(temp);
                //将next后移
                temp = temp.next;
            }
        }
    }

    //此对象表示一个节点
    static class HeroNode {
        //头结点
        public int no;

        public String name;

        public String nickName;
        //指向下一个节点
        public HeroNode next;

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        //重写toString
        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }

    }
}
