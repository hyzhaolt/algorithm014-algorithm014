package mile.com.week1.linklist;

/**
 * Created by zhaofengying on 2020/9/15.
 */
public class DeleteDuplicates {
    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //如果是空链表或只有一个节点的链表则直接返回当前链表不做任何处理
        if(null == head || null == head.next){
            return head;
        }

        ListNode curr = head;
        ListNode next = head;
        while(null != next){
            while(null != next && curr.val == next.val){
                next = next.next;
            }

            curr.next = next;
            curr = next;
            if(null == next){
                break;
            }
            next = next.next;
        }

        return head;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(5);
        ListNode node9 = new ListNode(5);
        ListNode node10 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        System.out.println("原始链表:");
        ListNode.printListNode(node1);

        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode head = deleteDuplicates.deleteDuplicates(node1);
        System.out.println("删除重复元素之后的链表:");
        ListNode.printListNode(head);
    }
}
