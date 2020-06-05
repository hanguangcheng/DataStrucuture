package com.my.DataSructure.LinkedList;
/**双向链表*/
public class DubboLinkedList {
    public static void main(String[] args) {

    }
    class DoubleLinkedList{
        private final HeroNode head = new HeroNode(0, "", "");
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
        //添加一个节点到双向链表
        public void add(HeroNode heroNode) {
            //因为头结点不能动，所以需要一个辅助节点
            HeroNode temp = head;
            while (temp.next != null) {
                //找到链表最后
                //如果没有找到，temp后移
                temp = temp.next;
            }
            temp.next = heroNode;
            heroNode.pre = temp;
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
            HeroNode temp = head.next;
            boolean flag = false;
            while (true) {
                if (temp== null) {
                    break;
                }
                if (temp.no == no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.pre.next = temp.next;
                if (temp.next!=null){
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
            }
        }
    }
    static class HeroNode {
        //头结点
        public int no;

        public String name;

        public String nickName;
        //指向下一个节点
        public HeroNode next;

        public HeroNode pre;

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

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
