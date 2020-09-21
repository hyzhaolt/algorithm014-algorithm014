package mile.com.week6;

public class MaximusProductSubarray {
    public int maxProduct(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return 0;
        }
        //初始化动态方程首元素
        int dpMax = nums[0];
        int dpMin = nums[0];
        //存放当前最大的连续子序列乘积
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int minF = dpMin,maxF = dpMax;
            dpMax = Math.max(nums[i], Math.max(maxF * nums[i],minF * nums[i]));
            dpMin = Math.min(nums[i],Math.min(minF * nums[i],maxF * nums[i]));
            max = Math.max(max,Math.max(dpMax,dpMin));
        }

        return max;
    }

    public static void main(String[] args){
        MaximusProductSubarray maximumProductSubArray = new MaximusProductSubarray();
//        int[] nums = {5,6,-3,4,-3};
        int[] nums = {2,3,-2,4};
        int maxSum = maximumProductSubArray.maxProduct(nums);
        System.out.println("最大子序列乘积为：" + maxSum);
    }
}
