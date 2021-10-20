package org.example.algorithm;

import org.junit.Test;

/**
 * LeetCode 两数相加
 */
public class TwoNumPlus {



    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;


        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        ListNode listNode = addTwoNumbers(l1, l4);

        while (null != listNode.next) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }



    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carrry = 0;
        while (null != l1 || null != l2) {
            int num1 = null == l1 ? 0 : l1.val;
            int num2 = null == l2 ? 0 : l2.val;
            int sum = num1 + num2 + carrry;
            if (null == head) {

            } else {

            }




        }



        return head;

    }





    public class ListNode {
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
}
