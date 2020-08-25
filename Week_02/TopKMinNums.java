package mile.com.week2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最小的K个数
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * Created by zhaofengying on 2020/8/24.
 */
public class TopKMinNums {

    /**
     * 解法一:先排序(升序) 再返回数组的前k个元素
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if(null == arr || arr.length <= 0 || k <= 0){
            return new int[]{};
        }

        //时间复杂度:O(nlongn) n为数组arr的长度
        Arrays.sort(arr);
        //再将数组的前k个元素返回 空间复杂度:O(k) 注:数组的拷贝 [from,to):即 左半闭空间
        return Arrays.copyOfRange(arr,0,k - 1);
    }

    /**
     * 利用堆排序
     * 从小到大排序:建立大顶堆 最先出来的是最大的 次大的  依此类推 最后出来的是最小的
     * 且每得到一个当前最大的元素就放到当前最末尾的位置
     * 从大到小排序:建立小顶堆 与大顶堆类似
     * 本题目:要求只输出前k个最小的-->建立一个大小为K的大顶堆 当堆大小<k时 直接入队
     * 当堆大小>=k时 比较堆顶元素与当前元素  如果当前元素>=堆顶元素 则不做任何处理
     * 否则删除堆顶元素 插入当前元素重新得到一个大顶堆 当一个数组中的全部元素依次
     * 做一次比较 那么将得到topK个小的元素在堆中 输出即可
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbersByHeap(int[] arr,int k){
        if(null == arr || arr.length <= 0 || k <= 0){
            return new int[]{};
        }

        //1.初始化一个大小为k的 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        //2.n个元素依次入队 一直保持 一个大小为k的大顶堆
        for(int i=0; i<arr.length; i++){
            //如果队列size < k 直接入队
            if(queue.size() < k){
                queue.add(arr[i]);
                continue;
            }

            //队列size >= k时 先判断当前元素是否比堆顶元素大 如果大于等于 则直接忽略
            if(arr[i] >= queue.peek()){
                continue;
            }

            //当前元素比堆顶元素小 则将堆顶元素删除(出队) 当前元素入队 因为堆顶元素是最大的 所以要排除的话首先
            queue.poll();
            queue.add(arr[i]);
        }

        //3.输出结果:将堆中的k个元素输出 堆中比堆顶元素大的全部已经过滤掉了 那么剩下的将是k个最小的
        int[] results = new int[k];
        int i=0;
        while(!queue.isEmpty()){
            results[i++] = queue.poll();
        }

        return results;
    }

    /**
     * 利用快排的思想进行排序
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbersByQuickSort(int[] arr,int k){
        return null;
    }

    public static void main(String[] args){
        TopKMinNums topKMinNums = new TopKMinNums();
        int[] nums = {0,0,1,2,4,2,2,3,1,4};
        int k = 8;
        int[] results = topKMinNums.getLeastNumbersByHeap(nums,k);
        for(int i=0; i<results.length; i++){
            System.out.print(results[i] + ",");
        }
    }
}
