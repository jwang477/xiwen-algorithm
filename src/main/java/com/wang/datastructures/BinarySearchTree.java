package com.wang.datastructures;

import java.util.*;

/**
 * 二叉查找树
 */
public class BinarySearchTree<T> {

    private TreeNode<T> root;

    private int size;

    private Comparator<? super T> cpt;

    public BinarySearchTree() {
        this.root = null;
        this.cpt = null;
    }

    public BinarySearchTree(Comparator<? super T> cpt) {
        this.cpt = cpt;
        this.root = null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(T data) {
        return Objects.nonNull(getTreeNode(data));
    }

    protected TreeNode<T> getTreeNode(T data) {
        if (Objects.isNull(data)) {
            throw new NullPointerException("data is null");
        }
        TreeNode<T> tempNode = root;
        if (Objects.nonNull(this.cpt)) {
            while (Objects.nonNull(tempNode)) {
                int compare = cpt.compare(data, tempNode.data);
                if (compare > 0) {
                    tempNode = tempNode.right;
                } else if (compare < 0) {
                    tempNode = tempNode.left;
                } else {
                    return tempNode;
                }
            }
        } else {
            while (Objects.nonNull(tempNode)) {
                Comparable<? super T> k = (Comparable<? super T>) data;
                int compare = k.compareTo(tempNode.data);
                if (compare > 0) {
                    tempNode = tempNode.right;
                } else if (compare < 0) {
                    tempNode = tempNode.left;
                } else {
                    return tempNode;
                }
            }
        }
        return null;
    }

    protected TreeNode<T> findMax() {
        return findMax(root);
    }

    protected TreeNode<T> findMax(TreeNode<T> temp) {
        while (temp != null && temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    protected TreeNode<T> findMin() {
        return this.findMin(root);
    }

    protected TreeNode<T> findMin(TreeNode<T> temp) {
        while (temp != null && temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public boolean put(T data) {
        TreeNode<T> node = new TreeNode<>(data, null, null);
        if (isEmpty()) {
            root = node;
            size++;
            return true;
        }
        TreeNode<T> temp = root;
        TreeNode<T> parent;
        int compare;
        do {
            parent = temp;
            if (Objects.isNull(this.cpt)) {
                Comparable<? super T> comparable = (Comparable<? super T>) data;
                compare = comparable.compareTo(temp.data);
            } else {
                compare = cpt.compare(data, temp.data);
            }
            if (compare > 0) {
                temp = temp.right;
            } else if (compare < 0) {
                temp = temp.left;
            } else {
                return false;
            }
        } while (Objects.nonNull(temp));
        node.parent = parent;
        if (compare > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        size++;
        return true;

    }

    public boolean remove(T data) {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode<T> temp = root;
        TreeNode<T> parent;
        int compare;
        do {
            if (Objects.isNull(this.cpt)) {
                Comparable<? super T> comparable = (Comparable<? super T>) data;
                compare = comparable.compareTo(temp.data);
            } else {
                compare = cpt.compare(data, temp.data);
            }
            if (compare > 0) {
                temp = temp.right;
            } else if (compare < 0) {
                temp = temp.left;
            } else {
                break;
            }
        } while (Objects.nonNull(temp));
        if (temp == null) {
            return false;
        }
        parent = temp.parent;

        if (temp.left == null) {
            if (parent.left == temp) {
                parent.left = temp.right;

            } else {
                parent.right = temp.right;
            }
            temp.right = null;
            temp.parent = null;
        } else if (temp.right == null) {
            if (parent.left == temp) {
                parent.left = temp.left;
            } else {
                parent.right = temp.left;
            }
            temp.left = null;
            temp.parent = null;
        } else {
            TreeNode<T> min = findMin(temp.right);
            temp.data = min.data;
            parent = min.parent;
            if (parent.left == min) {
                parent.left = min.right;
            } else {
                parent.right = min.right;
            }
            min.right = null;
            min.parent = null;
        }
        size--;
        return true;
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("tree is empty");
        } else {
            System.out.print("{");
            printTree(root);
            System.out.println("}");
        }
    }

    /**
     * 前序遍历  根 > 左 >右
     */
    public void preOrderPrint() {
        TreeNode<T> temp = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        System.out.println("非递归前序遍历：");
        System.out.print("{");
        while (temp != null || !stack.isEmpty()) {
            if (temp != null) {
                System.out.print(temp.data + "  ");
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                temp = temp.right;
            }
        }
        System.out.println("}");
    }

    /**
     * 中序遍历  左 > 根 > 右
     */
    public void inOrderPrint() {
        TreeNode<T> temp = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        System.out.println("非递归中序遍历：");
        System.out.print("{");
        while (temp != null || !stack.isEmpty()) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                System.out.print(temp.data + "  ");
                temp = temp.right;
            }
        }
        System.out.println("}");
    }

    /**
     * 后续遍历  左 > 右 > 根
     */
    public void postOrderPrint() {
        TreeNode<T> temp = root;
        TreeNode<T> prev = null;
        TreeNode<T> top = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        System.out.println("非递归后序遍历：");
        System.out.print("{");
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            top = stack.peek();
            if (top.right == null || top.right == prev) {
                stack.pop();
                System.out.print(top.data + "  ");
                prev = top;
            } else {
                temp = top.right;
            }
        }
        System.out.println("}");
    }

    /**
     * 奇怪的思想 根 > 右 > 左 反转==> 左 > 右 > 根
     */
    public void postOrderPrint2() {
        TreeNode<T> temp = root;
        TreeNode<T> prev = null;
        TreeNode<T> top = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        ArrayList<TreeNode> list = new ArrayList<>();
        System.out.println("非递归后序遍历：");
        System.out.print("{");
        while (temp != null || !stack.isEmpty()) {
            if (temp != null) {
                list.add(temp);
                stack.push(temp);
                temp = temp.right;
            } else {
                temp = stack.pop();
                temp = temp.left;
            }
        }
        Collections.reverse(list);
        for (TreeNode treeNode : list) {
            System.out.print(treeNode.data + "  ");
        }
        System.out.println("}");
    }

    protected void printTree(TreeNode<T> temp) {

        if (temp != null) {

            printTree(temp.left);
            printTree(temp.right);
            System.out.print(temp.data + "  ");
        }
    }

    private class TreeNode<T> {
        public T data;
        public TreeNode<T> left;

        public TreeNode<T> right;
        public TreeNode<T> parent;

        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

}
