package com.my.DataSructure.HashTable;

import java.util.Scanner;

public class HashTabDemo {
  public static void main(String[] args) {

    // 创建哈希表
    HashTab hashTab = new HashTab(7);

    // 写一个简单的菜单
    String key;
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("add:  添加雇员");
      System.out.println("list: 显示雇员");
      System.out.println("find: 查找雇员");
      System.out.println("remove: 删除雇员");
      System.out.println("exit: 退出系统");

      key = scanner.next();
      switch (key) {
        case "add":
          System.out.println("输入id");
          int id = scanner.nextInt();
          System.out.println("输入名字");
          String name = scanner.next();
          // 创建 雇员
          Emp emp = new Emp(id, name);
          hashTab.add(emp);
          break;
        case "list":
          hashTab.list();
          break;
        case "find":
          System.out.println("请输入要查找的id");
          id = scanner.nextInt();
          hashTab.findEmpById(id);
          break;
        case "exit":
          scanner.close();
          System.exit(0);
        case "remove":
          System.out.println("请输入需要删除的id");
          id = scanner.nextInt();
          hashTab.EmpRemove(id);
        default:
          break;
      }
    }
  }
  // 创建 hash表 ,用于管理多条链表
  static class HashTab {
    private final EmpLinkedList[] empLinkedListArray;
    private final int size;

    public HashTab(int size) {
      this.size = size;
      empLinkedListArray = new EmpLinkedList[size];
      // 初始化每条链表
      for (int i = 0; i < size; i++) {
        empLinkedListArray[i] = new EmpLinkedList();
      }
    }

    public void add(Emp emp) {
      // 根据id得到应该添加到哪条链表
      int empLinkedListArrayNo = hashFun(emp.id);
      // 加入对应链表
      empLinkedListArray[empLinkedListArrayNo].add(emp);
    }

    // 遍历hash表
    public void list() {
      for (int i = 0; i < size; i++) {
        empLinkedListArray[i].list(i);
      }
    }

    public void findEmpById(int id) {
      int i = hashFun(id);
      Emp emp = empLinkedListArray[i].findById(id);
      if (emp != null) { // 找到
        System.out.printf("在第%d条链表中找到  id = %d\n", (i + 1), id);
      } else {
        System.out.println("在哈希表中，没有找到此信息~");
      }
    }

    public void EmpRemove(int id) {
      int i = hashFun(id);
      empLinkedListArray[i].remove(id);
    }

    // 取模 hash散列运算
    public int hashFun(int i) {
      return i % size;
    }
  }

  static class Emp {
    private final int id;
    private final String name;
    private Emp next;

    public Emp(int id, String name) {
      this.id = id;
      this.name = name;
    }

    @Override
    public String toString() {
      return "Emp{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
  }

  static class EmpLinkedList {
    // 头指针指向第一个链表
    private Emp head;
    // 添加信息到方法 假设id是自增的 ，id总是从小到大
    public void add(Emp emp) {
      // 如果是第一条数据，直接加入链表
      if (head == null) {
        head = emp;
        return;
      }
      // 如果不是第一个就加入链表的最后
      Emp curEmp = head;
      while (curEmp.next != null) {
        curEmp = curEmp.next;
      }
      curEmp.next = emp;
    }

    public void list(int no) {
      if (head == null) {
        System.out.println("第" + (no + 1) + "链表为空");
        return;
      }
      System.out.println("第" + (no + 1) + "链表为");
      Emp curEmp = head;
      while (true) {
        System.out.println(curEmp.toString());
        if (curEmp.next == null) {
          break;
        }
        curEmp = curEmp.next;
      }
    }
    // 根据id值进行查找
    public Emp findById(int id) {
      if (head == null) {
        System.out.println("链表为空");
        return null;
      }
      Emp cruemp = head;
      while (true) {
        if (cruemp.id == id) {
          return cruemp;
        }
        if (cruemp.next == null) {
          return null;
        }
        cruemp = cruemp.next;
      }
    }
    // 根据id删除信息
    public void remove(int id) {
      if (head == null) {
        System.out.println("删除失败,没有此信息");
        return;
      }
      Emp cruemp = head;
      if (head.id == id) {
        head = head.next;
        System.out.println("删除成功");
        return;
      }
      while (cruemp.next != null) {
        if (cruemp.next.id == id) {
          cruemp.next = cruemp.next.next;
          head = cruemp;
          System.out.println("删除成功");
          return;
        }
        cruemp = cruemp.next;
      }
      System.out.println("删除失败,没有此信息");
    }
  }
}
