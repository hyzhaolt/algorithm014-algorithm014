package mile.com.week6;

/**
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * （1）分治：将第i个位置的最大子序列和问题转换为求第i-1个位置的最大子序列和问题
 * （2）状态数组定义：dp[i]表示在i位置所形成的最大子序列和的值
 * （3）dp方程：
 * a)dp[i]=Max(nums[i],dp[i-1] + nums[i])
 * b)含义：最大子序列和=当前位置元素本身最大，或者包含之前+本身之后最大
 */
public class MaximumSumSubArray {
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return 0;
        }
        //初始化动态方程首元素
        int dp = nums[0];
        //存放当前最大的连续子序列和
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(nums[i], dp + nums[i]);
            max = Math.max(max,dp);
        }

        return max;
    }

    public static void main(String[] args){
        MaximumSumSubArray maximumSumSubArray = new MaximumSumSubArray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = maximumSumSubArray.maxSubArray(nums);
        System.out.println("最大子序列和为：" + maxSum);
    }
}
