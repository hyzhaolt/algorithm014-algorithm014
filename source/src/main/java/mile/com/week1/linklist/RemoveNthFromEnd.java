package mile.com.week1.linklist;

/**
 * 删除链表中指定条件的节点
 * Created by zhaofengying on 2020/9/15.
 */
public class RemoveNthFromEnd {
    /**
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * 假定n一定是有效的
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(null == head || n <= 0){
            return null;
        }

        //空的头节点(一定要有 方便删除的是链表中的第一个节点)
        ListNode tmpHead = new ListNode();
        tmpHead.next = head;

        ListNode pre = tmpHead;
        ListNode fast = tmpHead;
        //快指针先于pre节点先走n步
        for(int i=0; i<n; i++){
            fast = fast.next;
        }

        //fast和pre指针一起走 当fast.next == null的时候 pre节点即是要删除节点的前驱节点
        while(null != fast.next){
            pre = pre.next;
            fast = fast.next;
        }

        pre.next = pre.next.next;

        return tmpHead.next;
    }

    public static void main(String[] args){
        ListNode head = ListNode.createLinkedList(1,false);
        ListNode p = head;
        System.out.println("原始链表");
        while(null != p){
            System.out.print(p.val + (null != p.next ? "-->" : ""));
            p = p.next;
        }
        System.out.println();

        RemoveNthFromEnd delElementInLinkedList = new RemoveNthFromEnd();
        int k = 1;
        ListNode delHead = delElementInLinkedList.removeNthFromEnd(head, k);
        System.out.println("删除倒数第" + k + "个节点之后的链表");
        ListNode q = delHead;
        while(null != q){
            System.out.print(q.val + (null != q.next ? "-->" : ""));
            q = q.next;
        }
    }
}
