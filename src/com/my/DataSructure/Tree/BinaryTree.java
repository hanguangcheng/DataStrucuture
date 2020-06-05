package com.my.DataSructure.Tree;
/**二叉树*/
public class BinaryTree {
  // 定义一个二叉树
  private HeroNode root;

  public void setRoot(HeroNode root) {
    this.root = root;
  }

  // 前序遍历
  public void preOrder() {
    if (this.root != null) {
      this.root.preOrder();
    } else {
      System.out.println("二叉树为空，无法遍历");
    }
  }

  // 中序遍历
  public void infixOrder() {
    if (this.root != null) {
      this.root.infixOrder();
    } else {
      System.out.println("二叉树为空，无法遍历");
    }
  }
  // 后序遍历
  public void postOrder() {
    if (this.root != null) {
      this.root.postOrder();
    } else {
      System.out.println("二叉树为空，无法遍历");
    }
  }
  // 前序遍历查找
  public HeroNode preOrderSearch(int no) {
    if (root != null) {
      return root.preOrderSearch(no);
    } else {
      return null;
    }
  }
  // 中序遍历查找
  public HeroNode infixOrderSearch(int no) {
    if (root != null) {
      return root.infixOrderSearch(no);
    } else {
      return null;
    }
  }
  // 后序遍历查找
  public HeroNode postOrderSearch(int no) {
    if (root != null) {
      return this.root.postOrderSearch(no);
    } else {
      return null;
    }
  }

  public void delNode(int no) {
    if (root != null) {
      if (root.no == no) {
        root = null;
      } else {
        this.root.delNode(no);
      }
    } else {
      System.out.println("空树，不能删除~");
    }
  }

  public static void main(String[] args) {
    // 先需要创建一颗二叉树
    BinaryTree binaryTree = new BinaryTree();
    // 创建需要的结点
    HeroNode root = new HeroNode(1, "宋江");
    HeroNode node2 = new HeroNode(2, "吴用");
    HeroNode node3 = new HeroNode(3, "卢俊义");
    HeroNode node4 = new HeroNode(4, "林冲");
    HeroNode node5 = new HeroNode(5, "关胜");
    // 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
    root.setLeft(node2);
    root.setRight(node3);
    node3.setRight(node4);
    node3.setLeft(node5);
    binaryTree.setRoot(root);
    //    //1,2,3,5,4
    //    System.out.println("前序遍历");
    //    binaryTree.preOrder();
    //    // 2,1,5,3,4
    //    System.out.println("中序遍历");
    //    binaryTree.infixOrder();
    //    // 2,5,4,3,1
    //    System.out.println("后序遍历");
    //    binaryTree.postOrder();
    //    前序遍历
    //    前序遍历的次数 ：4
    System.out.println("前序遍历方式~~~");
    HeroNode resNode = binaryTree.preOrderSearch(5);
    if (resNode != null) {
      System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
    } else {
      System.out.printf("没有找到 no = %d 的英雄", 5);
    }

    //    中序遍历查找
    //    中序遍历3次
    //		System.out.println("中序遍历方式~~~");
    //		HeroNode resNode = binaryTree.infixOrderSearch(5);
    //		if (resNode != null) {
    //			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
    //		} else {
    //			System.out.printf("没有找到 no = %d 的英雄", 5);
    //		}
    ////
    //    后序遍历查找
    //    后序遍历查找的次数  2次
    //		System.out.println("后序遍历方式~~~");
    //		HeroNode resNode = binaryTree.postOrderSearch(5);
    //		if (resNode != null) {
    //			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
    //		} else {
    //			System.out.printf("没有找到 no = %d 的英雄", 5);
    //		}
    System.out.println("删除前,前序遍历");
    binaryTree.preOrder(); //  1,2,3,5,4
    //    binaryTree.delNode(5);
    binaryTree.delNode(3);
    System.out.println("删除后，前序遍历");
    binaryTree.preOrder(); // 1,2,3,4
  }
  // 节点信息
  static class HeroNode {
    private int no;
    private String name;
    // 左子树
    private HeroNode left;
    // 又子树
    private HeroNode right;

    public HeroNode(int no, String name) {
      this.no = no;
      this.name = name;
    }

    public int getNo() {
      return no;
    }

    public void setNo(int no) {
      this.no = no;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public HeroNode getLeft() {
      return left;
    }

    public void setLeft(HeroNode left) {
      this.left = left;
    }

    public HeroNode getRight() {
      return right;
    }

    public void setRight(HeroNode right) {
      this.right = right;
    }

    @Override
    public String toString() {
      return "heroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }
    // 删除节点信息
    public void delNode(int no) {
      if (this.left != null && this.left.no == no) {
        this.left = null;
        return;
      }
      if (this.right != null && this.right.no == no) {
        this.right = null;
        return;
      }
      if (this.left != null) {
        this.left.delNode(no);
      }
      if (this.right != null) {
        this.right.delNode(no);
      }
    }
    // 前序遍历
    public void preOrder() {
      System.out.println(this);
      if (this.left != null) {
        this.left.preOrder();
      }
      if (this.right != null) {
        this.right.preOrder();
      }
    }
    // 中序遍历
    public void infixOrder() {
      if (this.left != null) {
        this.left.infixOrder();
      }
      // 输出父节点
      System.out.println(this);
      if (this.right != null) {
        this.right.infixOrder();
      }
    }
    // 后序遍历
    public void postOrder() {
      if (this.left != null) {
        this.left.postOrder();
      }
      if (this.right != null) {
        this.right.postOrder();
      }
      System.out.println(this);
    }
    // 前序遍历查找

    /**
     * @param no 需要查找的no
     * @return 比较当前节点
     */
    public HeroNode preOrderSearch(int no) {
      System.out.println("进入前序遍历");
      // 比较当前节点
      if (this.no == no) {
        return this;
      }
      HeroNode heroNode = null;
      if (this.left != null) {
        heroNode = this.left.preOrderSearch(no);
      }
      if (heroNode != null) {
        return heroNode;
      }
      if (this.right != null) {
        heroNode = this.right.preOrderSearch(no);
      }
      return heroNode;
    }

    public HeroNode infixOrderSearch(int no) {
      HeroNode heroNode = null;
      if (this.left != null) {
        heroNode = this.left.infixOrderSearch(no);
      }
      if (heroNode != null) {
        return heroNode;
      }
      System.out.println("进入中序查找");
      if (this.no == no) {
        return this;
      }
      if (this.right != null) {
        heroNode = this.right.infixOrderSearch(no);
      }
      return heroNode;
    }

    public HeroNode postOrderSearch(int no) {
      HeroNode heroNode = null;
      if (this.left != null) {
        heroNode = this.left.postOrderSearch(no);
      }
      if (heroNode != null) {
        return heroNode;
      }
      if (this.right != null) {
        heroNode = this.right.postOrderSearch(no);
      }
      if (heroNode != null) {
        return heroNode;
      }
      System.out.println("进入后序查找");
      if (this.no == no) {
        return this;
      }
      return heroNode;
    }
  }
}
