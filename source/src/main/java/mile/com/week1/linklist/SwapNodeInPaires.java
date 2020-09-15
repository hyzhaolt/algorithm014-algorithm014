package mile.com.week1.linklist;

import java.awt.*;

/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * Created by zhaofengying on 2020/9/11.
 */
public class SwapNodeInPaires {
    public ListNode swapPairs(ListNode head) {
        //如果链表为空 或 只有一个节点的链表则直接返回
        if(null == head
                || null == head.next){
            return head;
        }

        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode pre = newHead;

        while(null != head && null != head.next){
            //要交换的两个节点
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            //swap交换两个节点
            pre.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            //reverse
            //前驱节点
            pre = firstNode;
            //下一组的当前节点
            head = firstNode.next;
        }

        return newHead.next;
    }

    public static void main(String[] args){
        ListNode listNode = ListNode.createLinkedList(1,false);
        System.out.println("====原始链表====");
        ListNode p = listNode;
        while(null != p){
            System.out.print(p.val + ",");
            p = p.next;
        }
        System.out.println();

        SwapNodeInPaires swapNodeInPaires = new SwapNodeInPaires();
        listNode = swapNodeInPaires.swapPairs(listNode);
        p = listNode;

        System.out.println("====两两成对交换两个节点之后的链表====");
        while(null != p){
            System.out.print(p.val + ",");
            p = p.next;
        }

    }
}
