package mile.com.week2.queue;

import java.util.PriorityQueue;

/**
 * Created by zhaofengying on 2020/8/13.
 */
public class PriorityQueuePractice {
    /**
     * 基本数据类型的优先队列
     */
    public static void testIntegerPriorityQueue(){
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(100);
        queue.add(50);
        queue.add(2);
        queue.add(31);
        queue.add(5);

        System.out.println("初始队列: " + queue);

        System.out.println("查看堆顶元素:" + queue.peek());
        System.out.println("出队: " + queue.poll());
        System.out.println("当前队列: " + queue);

        System.out.println("查看堆顶元素:" + queue.peek());
        System.out.println("出队: " + queue.poll());
        System.out.println("当前队列: " + queue);

        System.out.println("查看堆顶元素:" + queue.peek());
        System.out.println("当前队列:" + queue);
    }

    /**
     * 测试自定义的优先队列
     */
    public static void testSelfDefPriority(){
        PriorityQueue<OKR> queue = new PriorityQueue<>();
        OKR okr1 = new OKR();
        okr1.setUserName("hyzhaolt");
        okr1.setInnerOrder(6);
        okr1.setType(2);
        okr1.setYear("2020");
        queue.add(okr1);

        System.out.println(queue);
        OKR okr2 = new OKR();
        okr2.setUserName("hyzhaolt");
        okr2.setInnerOrder(2);
        okr2.setType(2);
        okr2.setYear("2020");
        queue.add(okr2);
        System.out.println(queue);


        OKR okr5 = new OKR();
        okr5.setUserName("hyzhaolt");
        okr5.setInnerOrder(5);
        okr5.setType(2);
        okr5.setYear("2020");
        queue.add(okr5);
        System.out.println(queue);

        queue.poll();
        System.out.println("出队之后:" + queue);

        queue.poll();
        System.out.println("出队之后:" + queue);


    }

    public static void main(String[] args){
//        testIntegerPriorityQueue();
        testSelfDefPriority();
    }

}
