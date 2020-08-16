package mile.com.week1;

import java.util.HashMap;

/**
 * Created by zhaofengying on 2020/8/16.
 */
class LRUCache {
    private HashMap<Integer, Node> indexMap = null;
    private Integer capacity = 0;
    private Node head = null;
    private Node tail = null;

    /**
     * 初始化一个指定容量大小的缓存队列
     *
     * @param capacity
     */
    LRUCache(Integer capacity) {
        this.indexMap = new HashMap<>();
        this.capacity = capacity;
        head = new Node();
        tail = new Node();

        head.next = tail;
        head.pre = null;

        tail.pre = head;
        tail.next = null;
    }

    /**
     * 向缓存中放一个指定的key,value
     * 1.先从indexMap中查找key是否存在 若存在 则将indexMap.get(key)节点从原来位置删除掉 更新为最新的value值 并插入到队列末尾 返回
     * 2.若不存在 先判断是否已超限 不超限 直接在末尾插入新节点 返回
     * 3.若不存在 且已超限 则删除头节点 并在末尾插入新节点 返回
     *
     * @param key
     * @param value
     * @return
     */
    public void put(Integer key, Integer value) {
        if (capacity <= 0) {
            return ;
        }

        //1.当前key已经存在
        if (this.indexMap.containsKey(key)) {
            //先将此节点删除 再将此节点添加到队列末尾
            Node currNode = indexMap.get(key);
            //更新最新的value值
            currNode.setValue(value);
            //先删除节点
            boolean delRet = delete(null,currNode);
            if (!delRet) {
                return ;
            }

            //再在队列末尾添加此节点
            addLast(currNode);
        }

        //2.当前key不存在 则需要新建一个node
        Node aNewNode = new Node();
        indexMap.put(key, aNewNode);
        aNewNode.setKey(key);
        aNewNode.setValue(value);
        //2.1缓存容量未超限 直接在队列末尾添加当前节点
        if (this.indexMap.size() <= this.capacity) {
            addLast(aNewNode);
            return;
        }

        //2.2缓存容量已超限 先删除头节点 再插入到队列末尾
        Node currNode = indexMap.get(key);
        Node firstNode = head.next;
        boolean delRet = delete(firstNode.getKey(),firstNode);
        if (!delRet) {
            return;
        }

        addLast(currNode);
    }

    /**
     * 查询缓存值
     * 1.key不存在 直接返回-1
     * 2.key存在 将当前位置的node删除 并插入到队列的末尾(如果当前已经在末尾位置 则不做任何操作)
     *
     * @param key
     * @return
     */
    public Integer get(Integer key) {
        if (!indexMap.containsKey(key)) {
            return -1;
        }

        Node node = indexMap.get(key);
        //如果当前节点已经在队列末尾了 则直接返回value
        if (tail.pre == node) {
            return node.getValue();
        }

        //get的时候 只是改变节点的位置 而不做节点真正的删除
        boolean delRet = this.delete(null,node);
        if (!delRet) {
            return -1;
        }

        this.addLast(node);
        return node.getValue();
    }

    class Node {
        public Integer key;
        public Integer value;
        public Node pre;
        public Node next;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
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

    /**
     * 删除指定的节点 同时从hashMap中删除
     * @param key
     * @param node
     * @return
     */
    private boolean delete(Integer key,Node node) {
        if (null == node) {
            return false;
        }

        //删除节点的同时 从索引map中删除
        if(null != key){
            indexMap.remove(key);
        }

        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.pre = null;
        node.next = null;

        return true;
    }

    /**
     * 将指定的节点添加到队列末尾
     *
     * @param node
     */
    private boolean addLast(Node node) {
        node.pre = this.tail.pre;
        tail.pre.next = node;
        node.next = tail;
        tail.pre = node;

        return true;
    }

    public static void main(String[] args){
        try{
            LRUCache cache = new LRUCache(2);
            cache.put(1,1);
            cache.put(2,2);
            System.out.println(cache.get(1));
            cache.put(3,3);
            System.out.println(cache.get(2));
            cache.put(4,4);
            System.out.println(cache.get(1));
            System.out.println(cache.get(3));
            System.out.println(cache.get(4));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
