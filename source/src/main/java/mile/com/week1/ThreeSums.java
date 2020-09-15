package mile.com.week1;

import java.util.*;

/**
 * 三数之和问题
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by zhaofengying on 2020/8/13.
 */
public class ThreeSums {
    /**
     * 暴力求解法一 leetcode提交会报出执行时间
     * 注:因为要返回非重复的三元组  所以需要对所有符合条件的三元组做排序
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSums(int[] nums) {
        if (null == nums || nums.length < 3) {
            return new ArrayList<>();
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        for(int i=0; i<nums.length - 2; i++){
            for(int j=i+1; j<nums.length -1; j++){
                for(int k=j+1; k<nums.length ; k++){
                    if(nums[i] + nums[j] + nums[k] == 0){
                        //只对三元组进行排序即可
                        int[] arr = {nums[i], nums[j], nums[k]};
                        Arrays.sort(arr);
                        List<Integer> currThreeSum = Arrays.asList(arr[0],arr[1],arr[2]);

                        result.add(currThreeSum);
                    }
                }
            }
        }

        return new ArrayList(result);
    }

    /**
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSums2(int[] nums) {
        if (null == nums || nums.length < 3) {
            return new ArrayList<>();
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length - 2; i++){
            int target = -1 * nums[i];
            //再找一个target = nums[j] + nums[k] 如果当前<
            for(int j=i+1,k=nums.length -1; j < k ; ){
                if(nums[j] + nums[k] == target){
                    List<Integer> currThreeSumList = Arrays.asList(nums[i],nums[j],nums[k]);
                    result.add(currThreeSumList);
                    //当前三元组符合要求 继续寻找下一个三元组 直至j>=k
                    j ++;
                    k --;
                }
                //当前两数之和<target 则向右移动 使用nums[j]变大一些
                else if(nums[j] + nums[k] < target){
                    j++;
                }
                //当前两数之和>target 则向左移动 使得nums[k]变小些
                else if(nums[j] + nums[k] > target){
                    k--;
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 四元组
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (null == nums || nums.length < 4) {
            return new ArrayList<>();
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        Arrays.sort(nums);

        for(int i=0; i<nums.length - 3; i++){
            for(int j=i+1; j<nums.length - 2; j++){
                int tmpTarget = target - nums[i] - nums[j];
                for(int k=j+1,h=nums.length -1; k<h; ){
                    if(nums[k] + nums[h] == tmpTarget){
                        List<Integer> currThreeSumList = Arrays.asList(nums[i],nums[j],nums[k],nums[h]);
                        result.add(currThreeSumList);
                        //当前四元组符合要求 继续寻找下一个四元组 直至j>=k
                         k++;
                         h--;
                    }
                    else if(nums[k] + nums[h] < tmpTarget){
                        k++;
                    }
                    else if(nums[k] + nums[h] > tmpTarget){
                        h--;
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }


    public static void main(String[] args){
//        Set<List<Integer>> result = new HashSet<List<Integer>>();
//        result.add(Arrays.asList(0,0,1));
//        result.add(Arrays.asList(0,0,1));
//        result.add(Arrays.asList(0,1,0));
//        System.out.println(result);
//
//        System.out.println(threeSums2(new int[]{-2,0,1,1,2}));

        ThreeSums threeSums = new ThreeSums();
        System.out.println(threeSums.fourSum(new int[]{1,0,-1,0,-2,2},0));


    }
}
