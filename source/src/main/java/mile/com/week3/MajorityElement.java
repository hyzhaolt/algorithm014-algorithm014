package mile.com.week3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

 你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/majority-element
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by zhaofengying on 2020/9/9.
 *
 * 解题思路:
 * 实质:求一组数据中的众数 即:出现次数最多的元素
 * 解法一:暴力求解法 时间复杂度:O(n) 空间复杂度:O(n)
 * 1.哈希法 统计每一个元素出现的次数 以元素为key 次数为value放入一个map
 * 2.再遍历一遍map 枚举出频次最大的数返回即可
 *
 * 解法二:投票选举法 时间复杂度:O(n) 空间复杂度:O(1)
 *
 * 解法三:排序 + 直接返回nums[n/2] 时间复杂度:O(nLogn) 空间复杂度:O(
 */
public class MajorityElement {

    /**
     * 解法一
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> frequencyMap = new HashMap<>();

        for (int num : nums){
            if(!frequencyMap.containsKey(num)){
                frequencyMap.put(num,0);
            }

            frequencyMap.put(num,frequencyMap.get(num) + 1);
        }

        int maxFrequency = 0;
        int majorityElement = 0;
        Iterator<Integer> it = frequencyMap.keySet().iterator();
        while(it.hasNext()){
            Integer key = it.next();
            Integer frequency = frequencyMap.get(key);
            if(maxFrequency < frequency){
                maxFrequency = frequency;
                majorityElement = key;
            }
        }

        return majorityElement;
    }

    /**
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums){
        int candidate = nums[0];
        int count = 0;

        for (int i=0; i<nums.length; i++){
            if(count == 0){
                candidate = nums[i];
            }

            if(candidate == nums[i]){
                count ++;
            }
            else{
                count --;
            }
        }

        return candidate;
    }

    /**
     * 排序 + 直接返回
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums){
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }




    public static void main(String[] args){
        int[] nums = {3,2,3,4,5,6,3,3,3,2,1,5};
        MajorityElement majorityElement = new MajorityElement();
        System.out.println("解法一返回结果:" + majorityElement.majorityElement(nums));
        System.out.println("解法二返回结果:" + majorityElement.majorityElement2(nums));
        System.out.println("解法三返回结果:" + majorityElement.majorityElement3(nums));
    }
}
