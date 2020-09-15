package mile.com.week1.linklist;

/**
 * Created by zhaofengying on 2020/9/10.
 */
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


    /**
     * 创建一个指定长度的链表 并且可指明是否需要返回空的头节点
     * @param n
     * @param hasHeadNode
     * @return
     */
    public static ListNode createLinkedList(int n,boolean hasHeadNode){
        /**
         * 创建一个带头节点的链表
         */
        ListNode head = new ListNode();
        ListNode pre = head;
        for(int i=0; i<n; i++){
            ListNode currNode = new ListNode();
            currNode.val = i + 1;
            pre.next = currNode;
            pre = currNode;
        }

        return hasHeadNode ? head : head.next;
    }

    /**
     * 创建一个环形链表 可指定链表最后一个元素链接到第几个位置上的节点 即:用pos指定形成环的入口点
     * @param n
     * @param hasHeadNode
     * @param pos:来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * @return
     */
    public static ListNode createLinkedListCircle(int n,boolean hasHeadNode,int pos){
        //先创建一个正常无环的链表
        ListNode head = createLinkedList(n,hasHeadNode);
        if(pos < 0){
            return head;
        }

        ListNode currNode = null;
        if(hasHeadNode){
            currNode = head.next;
        }
        else{
            currNode = head;
        }

        ListNode entryCircleNode = null;
        ListNode tailNode = null;
        int i = 0;
        while(null != currNode.next){
            if(i == pos){
                entryCircleNode = currNode;
            }

            currNode = currNode.next;
            i++;
        }

        tailNode = currNode;
        tailNode.next = entryCircleNode;

        return head;
    }

    /**
     * 直接打印链表
     * @param head
     */
    public static void printListNode(ListNode head){
        if(null == head){
            System.out.println("当前链表为空");
            return;
        }

        ListNode p = head;
        while(null != p){
            System.out.print(p.val + (null == p.next ? "" : "-->"));
            p = p.next;
        }

        System.out.println();
    }
}
