package mile.com.week1.linklist;

/**
 * 合并两个有序链表
 * Created by zhaofengying on 2020/9/15.
 */
public class MergeTwoSortedLinkedList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tmpHead = new ListNode();
        ListNode preNode = tmpHead;

        ListNode curr1 = l1;
        ListNode curr2 = l2;

        while(null != curr1 && null != curr2){
            if(curr1.val <= curr2.val){
                preNode.next = curr1;
                preNode = curr1;
                curr1 = curr1.next;
            }
            else {
                preNode.next = curr2;
                preNode = curr2;
                curr2 = curr2.next;
            }
        }

        preNode.next = null == curr1 ? curr2 : curr1;

        return tmpHead.next;
    }

    public static void main(String[] args){
        ListNode l1 = ListNode.createLinkedList(5,false);
        ListNode l2 = ListNode.createLinkedList(3,false);

        MergeTwoSortedLinkedList mergeTwoSortedLinkedList = new MergeTwoSortedLinkedList();
        ListNode m = mergeTwoSortedLinkedList.mergeTwoLists(l1,l2);
        ListNode node = m;
        String out = "";
        while(null != node){
            out = out + node.val + ",";
            node = node.next;
        }
        System.out.println("合并之后的链表:" + out);
    }
}
