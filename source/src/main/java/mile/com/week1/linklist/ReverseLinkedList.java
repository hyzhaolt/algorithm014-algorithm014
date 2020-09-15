package mile.com.week1.linklist;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 算法思想:
 * 1.使用一个带有头节点的空节点作为新链表的表头
 * 2.设置两个指针:currP:当前等待插入到头节点后面的节点 nextP:当前操作节点未操作前的下一个节点
 * 3.每次都将当前要插入的节点插入到头节点的后面即可.
 * Created by zhaofengying on 2020/9/10.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode tmpHead = new ListNode();
        if(null == head){
            return null;
        }

        ListNode currP = head;
        while(null != currP){
            ListNode nextP = currP.next;
            currP.next = tmpHead.next;
            tmpHead.next = currP;
            currP = nextP;
        }

        return tmpHead.next;
    }

    public static void main(String[] args){
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        /**
         * 创建一个带头节点的链表
         */
        ListNode head = new ListNode();
        ListNode pre = head;
        for(int i=0; i<5; i++){
            ListNode currNode = new ListNode();
            currNode.val = i + 1;
            pre.next = currNode;
            pre = currNode;
        }

        ListNode curNode = head.next;
        System.out.println("原有链表:");
        while(null != curNode){
            System.out.print(curNode.val + ",");
            curNode = curNode.next;
        }
        System.out.println();
        System.out.println("反转之后的链表:");
        ListNode reverseNode = reverseLinkedList.reverseList(head.next);
        while(null != reverseNode){
            System.out.print(reverseNode.val + ",");
            reverseNode = reverseNode.next;
        }
        System.out.println();
    }
}
