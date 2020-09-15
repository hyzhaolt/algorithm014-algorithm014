package mile.com.week1.linklist;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by zhaofengying on 2020/9/11.
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        //临时头节点 永远不变 tempHead.next才指向链表的第一个数据节点
        ListNode tempHead = new ListNode();
        tempHead.next = head;

        //已反转链表的前驱节点
        ListNode pre = tempHead;
        ListNode end = tempHead;
        while(null != end.next){
            //start,end分别指向待反转链表的第一个节点和最后一个节点
            for(int i=0; i<k && null != end ; i++){
                end = end.next;
            }
            if(null == end){
                break;
            }

            ListNode start = pre.next;
            //暂存未反转链表的第一个节点
            ListNode next = end.next;
            end.next = null;
            //已反转链表+新反转成功链表建立连接
            pre.next = this.reverse(start,end);
            //已反转链表+未反转链表建立连接
            start.next = next;

            //重置变量 为下一次链表反转的前驱节点
            pre = start;
            end = pre;
        }

        return tempHead.next;
    }

    /**
     * 指定一个链表中的第一个节点和最后一个节点 将此之间的节点全部反转
     * @param head
     * @param tail
     */
    private ListNode reverse(ListNode head,ListNode tail){
        if(null == head || null == tail){
            return null;
        }

        ListNode tmpHead = new ListNode();
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
        ReverseNodesInKGroup reverseLinkedList = new ReverseNodesInKGroup();
        ListNode head = ListNode.createLinkedList(10,false);

        System.out.println("未反转前链表:");
        String origin = "";
        ListNode p = head;
        while(null != p){
            origin = origin + p.val + ",";
            p = p.next;
        }
        System.out.println(origin);
        System.out.println("反转之后的链表:");
        ListNode reverseHead = reverseLinkedList.reverseKGroup(head,5);
        String reverse = "";
        p = reverseHead;
        while(null != p){
            reverse = reverse + p.val + ",";
            p = p.next;
        }
        System.out.println(reverse);

    }
}
