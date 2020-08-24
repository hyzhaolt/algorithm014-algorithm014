package mile.com.week1;

/**
 * Created by zhaofengying on 2020/8/16.
 */
public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //用于记录合并之后的链表的头节点 是一个空节点
        ListNode header = new ListNode();
        //合并之后链表的最后一个节点 指向l1和l2中的较小者
        ListNode current = header;
        while (null != l1 && null != l2) {
            if (l1.val < l2.val) {
                current.next = l1;
                current = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                current = l2;
                l2 = l2.next;
            }
        }

        current.next = null == l1 ? l2 : l1;

        return header.next;
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
