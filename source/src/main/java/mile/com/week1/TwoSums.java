package mile.com.week1;

import java.util.HashMap;

/**
 * 在一个给定的数组中查找是否存在a+b=target 如果存在返回a和b的下标 否则返回空
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

 示例:
 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by zhaofengying on 2020/8/13.
 */
public class TwoSums {
    /**
     * 双重循环法 枚举法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumSolution1(int[] nums,int target){
        if(null == nums || nums.length < 2 ){
            return null;
        }

        int[] result = new int[2];

        for (int i=0; i<nums.length - 1; i++){
            for (int j=i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return null;
    }

    /**
     * 哈希法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if (null == nums || nums.length < 2){
            return null;
        }

        HashMap<Integer,Integer> numsHash = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int findNum = target - nums[i];
            if(numsHash.containsKey(findNum)){
                return new int[] {numsHash.get(findNum),i};
            }

            numsHash.put(nums[i],i);
        }

        return null;
    }


}
