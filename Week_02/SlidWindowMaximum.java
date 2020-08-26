package mile.com.week2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * Created by zhaofengying on 2020/8/26.
 */
public class SlidWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] results = new int[nums.length - k + 1];

        if(null == nums || nums.length <= 0 || k <= 0){
            return results;
        }

        //1.初始化一个大小为k的 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for(int i=0; i<=nums.length - k; i++){
            //将当前滑动窗口的前一个窗口内的最后一个数字移除掉
            if(i > 0 && !queue.isEmpty()){
                queue.remove(nums[i - 1]);
            }

            for(int j=i; j<(i + k); j++){
                queue.add(nums[j]);
            }

            //堆顶元素就是当前最大的
            results[i] = queue.peek();
        }

        return results;
    }

    public static void main(String[] args){
        SlidWindowMaximum slidWindowMaximum = new SlidWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] results = slidWindowMaximum.maxSlidingWindow(nums, k);
        for(int num : results){
            System.out.println(num);
        }
    }

}
