package mile.com.week1.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

 说明：不允许修改给定的链表。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by zhaofengying on 2020/9/13.
 */
public class LinkedListCircle {

    /**
     * 哈希法
     * 每访问一个节点就将其放到一个set中 后续如果还能访问到此节点 说明一定有环
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        //空链表或只有一个节点的链表
        if(null == head
                || null == head.next){
            return null;
        }

        //注:最初使用的Set<Integer> 使用每一个节点的value为唯一值 如果两个节点拥有相同的val时将会异常
        Set<ListNode> visited = new HashSet<>();
        ListNode currNode = head;
        while(null != currNode){
            if(visited.contains(currNode)){
                return currNode;
            }

            visited.add(currNode);
            currNode = currNode.next;
        }

        return null;
    }

    /**
     * 使用快慢指针法判断一个链表中是否有环
     * @param head
     * @return
     */
    public ListNode detectCycleSlowAndFastPointer(ListNode head) {
        //空链表或只有一个节点的链表
        if(null == head
                || null == head.next){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        //step1:先找到入口点
        while(null != fast && null != fast.next){
            slow = slow.next;
            fast = fast.next.next;

            //第一次找到相遇节点则跳出 slow即为相遇点
            if(slow == fast){
                break;
            }
        }

        //step2:一个指针从head遍历 一个指针从step1的相遇节点开始遍历 下次两指针相遇的节点即是环的入口节点
        //数学证明:从head到环入口点的距离记作:F,从入口点到第一次相遇点距离:a,从相遇点到环入口点记作:b
        //因为快指针每次都走2步 所以在第一次相遇时 快指针走过的距离是慢指针的2倍
        //2(F+a)=F+a+(a+b)-->2F + 2a = F + 2a + b-->F=b
        ListNode p1 = head;
        while(p1 != slow){
            p1 = p1.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args){
        LinkedListCircle linkedListCircle = new LinkedListCircle();
        ListNode head = ListNode.createLinkedListCircle(4, false, 1);
        ListNode entryCircleNode = linkedListCircle.detectCycle(head);
        System.out.println(null == entryCircleNode ? "链表中没有环" : "链表中有环,其尾部连接到第" + entryCircleNode.val + "节点");

        ListNode slowFastCircleNode = linkedListCircle.detectCycleSlowAndFastPointer(head);
        System.out.println(null == slowFastCircleNode ? "链表中没有环" : "链表中有环,其尾部连接到第" + slowFastCircleNode.val + "节点");
    }
}
