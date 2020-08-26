package mile.com.week2;

import java.util.*;

/**
 * Created by zhaofengying on 2020/8/26.
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums,int k){
        int[] results = new int[k];
        if(null == nums || nums.length <= 0 || k <= 0){
            return results;
        }

        Map<Integer,Integer> freMap = new HashMap<>();
        Queue<ElementFrequntNode> queue = new PriorityQueue<ElementFrequntNode>(k, new Comparator<ElementFrequntNode>() {
            @Override
            public int compare(ElementFrequntNode o1, ElementFrequntNode o2) {
                return o1.frequent - o2.frequent;
            }
        });

        //1.先统计每一个元素出现的频次
        for(int i=0; i<nums.length; i++){
            if(!freMap.containsKey(nums[i])){
                freMap.put(nums[i],0);
            }

            freMap.put(nums[i],freMap.get(nums[i]) + 1);
        }

        //2.再创建一个小顶堆 比堆顶小的数都不入队 最后留在堆中的k个数 将是最大的k个
        Iterator<Integer> it = freMap.keySet().iterator();
        while(it.hasNext()){
            Integer key = it.next();
            Integer fre = freMap.get(key);

            ElementFrequntNode node = new ElementFrequntNode();
            node.val = key;
            node.frequent = fre;

            //2.1队列未满 则直接入队
            if(queue.size() < k){
                queue.add(node);
                continue;
            }

            //2.2队列已满 则比较堆顶元素
            //(1)若当前fre比堆顶元素<= 则直接忽略  因为堆中已经存在k个不小于当前fre的数了
            if(fre <= queue.peek().frequent){
                continue;
            }

            //(2)删除堆顶元素 插入新元素
            queue.poll();
            queue.add(node);
        }

        int i=0;
        while(!queue.isEmpty()){
            results[i++] = queue.poll().val;
        }
        return results;
    }

    /**
     * 统计元素频次的数据结构
     */
    class ElementFrequntNode{
        int val;
        int frequent;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 5, 4, 4, 6, 4, 5};
        int k = 3;

        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        int[] results = topKFrequentElements.topKFrequent(nums, k);
        for(int num : results){
            System.out.println(num);
        }
    }
}
