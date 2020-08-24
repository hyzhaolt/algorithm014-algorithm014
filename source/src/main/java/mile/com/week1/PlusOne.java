package mile.com.week1;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/plus-one/
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by zhaofengying on 2020/8/15.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int tail = digits.length - 1;

        //最末尾为数字9
        for(int i=tail; i>=0; i--){
            if(digits[i] < 9){
                digits[i]++;
                break;
            }

            digits[i] = 0;
        }

        //最高位未产生进位 则直接返回
        if(digits[0] != 0){
            return digits;
        }

        //最高位产生了进位 且为1
        int[] results = new int[digits.length + 1];
        for(int i=0; i<digits.length + 1; i++){
            results[i] = i > 0 ? 0 : 1;
        }

        return results;
    }

    public static void main(String[] args){
        PlusOne plusOne = new PlusOne();
        int[] nums = {1,9,9,3,9};
        nums = plusOne.plusOne(nums);

        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i] + ",");
        }
    }
}
