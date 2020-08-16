package mile.com.week1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zhaofengying on 2020/8/15.
 */
public class MinStack {
    private Deque<Integer> stack = null;
    private Deque<Integer> minStack = null;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int x) {
        //等同于addFirst()
        stack.push(x);
        //首个栈元素 两个栈同时入栈
        if(minStack.isEmpty()){
            minStack.push(x);
            return;
        }
        //非首个栈元素
        //检查一下最小栈栈顶元素是否比当前元素大 若是当前元素入栈最小栈栈顶 保持栈顶元素为当前栈内元素的最小值
        //否则继续使用最小栈原有栈顶元素当作新元素插入到栈顶
        int currMin = minStack.peekFirst();
        if(currMin > x){
            minStack.push(x);
            return;
        }

        minStack.push(currMin);
    }

    public void pop() {
        if(this.stack.isEmpty()){
            return;
        }
        //等同于removeFirst()
        stack.pop();
        minStack.pop();
    }

    /**
     * 因为入栈默认使用的是addFirst() 所以查看栈顶元素要用peekFirest()
     * @return
     */
    public int top() {
        return stack.peekFirst();
    }

    /**
     * 取栈顶元素 因为入栈使用的是addFirst()
     * 此处查看也要使用first 否则就不是栈而是队列的特性了
     * @return
     */
    public int getMin() {
        return minStack.peekFirst();
    }

    public static void main(String[] args){
        MinStack minStack = new MinStack();
        minStack.push(2);
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());
        minStack.push(0);
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());
        minStack.push(3);
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());
        minStack.push(0);
        System.out.println(minStack.stack);
        System.out.println(minStack.minStack);
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());

        System.out.println("------start to pop------");
        minStack.pop();
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());
        minStack.pop();
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());
        minStack.pop();
        System.out.println("current top and  min:" + minStack.top() + "," + minStack.getMin());

    }
}
