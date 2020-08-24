package mile.com.week1;

/**
 * 注:所有的方法 都是非线程安全的
 * Created by zhaofengying on 2020/8/15.
 */
class MyCircularDeque {
    /**
     * 容量大小限制
     */
    private int capacity;
    /**
     * 队列中的元素个数
     */
    private int size;
    /**
     * 空的头指针
     */
    private Node head;

    /**
     * 空的尾指针
     */
    private Node tail;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.capacity = k;
        Node head = new Node();
        head.setValue(-1);
        this.head = head;

        Node tail = new Node();
        tail.setValue(-1);
        this.tail = tail;

        head.next = tail;
        tail.pre = head;

        head.pre = tail;
        tail.next = head;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(this.isFull()){
            return false;
        }
        //当前待插入节点
        Node insertNode = new Node();
        insertNode.value = value;

        //未插入前的第一个数据节点
        Node nextNode = head.next;

        //待插入节点的直接后继节点
        insertNode.next = nextNode;
        nextNode.pre = insertNode;

        //当前待插入节点的直接前驱节点
        insertNode.pre = head;
        head.next = insertNode;

        size ++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(this.isFull()){
            return false;
        }

        //生成待插入节点
        Node insertNode = new Node();
        insertNode.value = value;

        //当前尾元素的前驱节点
        Node preNode = tail.pre;

        //待插入节点 设置直接前驱节点
        preNode.next = insertNode;
        insertNode.pre = preNode;

        //待插入节点 设置直接后继节点
        insertNode.next = tail;
        tail.pre = insertNode;

        size ++;

        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(this.isEmpty()){
            return false;
        }

        //要删除的节点
        Node deletedNode = head.next;
        //要删除节点的直接后继节点
        Node deletedNodeNextNode = deletedNode.next;

        deletedNodeNextNode.pre = head;
        head.next = deletedNodeNextNode;

        deletedNode.next = null;
        deletedNode.pre = null;
        deletedNode = null;

        size --;

        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(this.isEmpty()){
            return false;
        }

        //deleteNode:要删除的节点元素
        Node deleteNode = tail.pre;
        //要删除节点的前驱指针
        Node deletePreNode = deleteNode.pre;
        //类开deleteNode前驱指针与deleteNode之间的链接
        deletePreNode.next = tail;
        //队尾的前驱指针指向原来要删除元素的前驱
        tail.pre = deletePreNode;

        deleteNode.next = null;
        deleteNode.pre = null;
        deleteNode = null;

        size --;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(this.isEmpty()){
            return -1;
        }

        return this.head.next.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(this.isEmpty()){
            return -1;
        }

        return this.tail.pre.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        MyCircularDeque obj = new MyCircularDeque(3);
        System.out.println("obj.isEmpty():" + obj.isEmpty());
        System.out.println("obj.insertLast(1):" + obj.insertLast(1));
        System.out.println("obj.insertLast(2):" + obj.insertLast(2));
        System.out.println("obj.insertFront(3):" + obj.insertFront(3));
        System.out.println("obj.isFull():" + obj.isFull());
        System.out.println("obj.insertFront(4):" + obj.insertFront(4));

        System.out.println("obj.getRear():" + obj.getRear());
        System.out.println("obj.getFront():" + obj.getFront());

        System.out.println("obj.deleteFront():" + obj.deleteFront());
        System.out.println("obj.deleteLast():" + obj.deleteLast());
        System.out.println("obj.isEmpty():" + obj.isEmpty());
        System.out.println("obj.isFull():" + obj.isFull());

        System.out.println("obj.deleteFront():" + obj.deleteFront());
        System.out.println("obj.isEmpty():" + obj.isEmpty());

        System.out.println("obj.deleteFront():" + obj.deleteFront());
        System.out.println("obj.deleteLast():" + obj.deleteLast());


    }

    /**
     * 队列中的每一个节点定义 节点之间以链表的前后指针相连接
     */
    private class Node{
        /**
         * 当前元素值
         */
        private int value;
        /**
         * 前指指针
         */
        private Node pre;
        /**
         * 后指指针
         */
        private Node next;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
