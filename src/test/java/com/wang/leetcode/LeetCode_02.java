package com.wang.leetcode;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_02 {
    //[9]
//        [1,9,9,9,9,9,8,9,9,9]
    public static void main(String[] args) {
        int[] l1Arr = {9};
        int[] l2Arr = {1, 9, 9, 9, 9, 9, 8, 9, 9, 9};
        ListNode listNode1 = builder(l1Arr);
        ListNode listNode4 = builder(l2Arr);

        ListNode node = addTwoNumbers(listNode1, listNode4);
        ListNode node2 = addTwoNumbersV2(listNode1, listNode4);
        printNode(node);
        System.out.println("-------------------------------");
        printNode(node2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        int l1Size = size(l1);
        int l2Size = size(l2);
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        int sum = 0;
        int l2Val = 0;
        int l1Val = 0;
        boolean flag = false;
        if (l1Size > l2Size) {
            while (l2Temp != null) {
                l2Val = l2Temp.val;
                l1Val = l1Temp.val;

                if (flag) {
                    sum = 1 + l1Val + l2Val;
                } else {
                    sum = l1Val + l2Val;
                }
                if (sum >= 10) {
                    flag = true;
                    l1Temp.val = sum % 10;
                } else {
                    flag = false;
                    l1Temp.val = sum;
                }
                if (l2Temp.next == null) {
                    break;
                }
                l1Temp = l1Temp.next;
                l2Temp = l2Temp.next;
            }
            if (flag) {
                if (l1Temp.next == null) {
                    ListNode node = new ListNode();
                    node.val = 1;
                    l1Temp.next = node;
                } else {
                    l1Temp = l1Temp.next;
                    while (l1Temp.next != null) {
                        if (flag) {
                            sum = l1Temp.val + 1;
                            if (sum == 10) {
                                l1Temp.val = 0;
                                flag = true;
                            } else {
                                l1Temp.val = sum;
                                flag = false;
                                break;
                            }
                        } else {
                            break;
                        }
                        l1Temp = l1Temp.next;
                    }
                    if (flag) {
                        if (l1Temp.val == 9) {
                            l1Temp.val = 0;
                            ListNode node = new ListNode();
                            node.val = 1;
                            l1Temp.next = node;
                        } else {
                            l1Temp.val++;
                        }
                    }
                }
            }
            return l1;
        } else {
            while (l1Temp != null) {
                l2Val = l2Temp.val;
                l1Val = l1Temp.val;

                if (flag) {
                    sum = 1 + l1Val + l2Val;
                } else {
                    sum = l1Val + l2Val;
                }
                if (sum >= 10) {
                    flag = true;
                    l2Temp.val = sum % 10;
                } else {
                    flag = false;
                    l2Temp.val = sum;
                }
                if (l1Temp.next == null) {
                    break;
                }
                l1Temp = l1Temp.next;
                l2Temp = l2Temp.next;
            }

            if (flag) {
                if (l2Temp.next == null) {
                    ListNode node = new ListNode();
                    node.val = 1;
                    l2Temp.next = node;
                } else {
                    l2Temp = l2Temp.next;
                    while (l2Temp.next != null) {
                        if (flag) {
                            sum = l2Temp.val + 1;
                            if (sum == 10) {
                                l2Temp.val = 0;
                                flag = true;
                            } else {
                                l2Temp.val = sum;
                                flag = false;
                                break;
                            }
                        } else {
                            break;
                        }
                        l2Temp = l2Temp.next;
                    }
                    if (flag) {
                        if (l2Temp.val == 9) {
                            l2Temp.val = 0;
                            ListNode node = new ListNode();
                            node.val = 1;
                            l2Temp.next = node;
                        } else {
                            l2Temp.val++;
                        }

                    }
                }
            }
            return l2;
        }


    }


    public static ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + carry;
            carry = sum / 10;
            int result = sum % 10;
            cur.next = new ListNode(result);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return pre.next;

    }

    public static int size(ListNode l) {
        ListNode temp = l;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public static void printNode(ListNode l) {
        ListNode temp = l;
        while (temp != null) {
            System.out.println("temp = " + temp.val);
            temp = temp.next;
        }
    }

    public static ListNode builder(int[] nums) {
        ListNode l = new ListNode();
        l.val = nums[0];
        ListNode temp = l;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode();
            node.val = nums[i];
            temp.next = node;
            temp = node;
        }
        return l;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}