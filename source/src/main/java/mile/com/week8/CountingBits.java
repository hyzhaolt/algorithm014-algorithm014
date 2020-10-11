package mile.com.week8;

/**
 * https://leetcode-cn.com/problems/counting-bits/description/
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 比特位计数
 */
public class CountingBits {
    public int[] countBits(int num) {
        if(num < 0){
            return new int[]{};
        }

        int [] results = new int[num + 1];
        results[0] = 0;
        if(num < 1){
            return results;
        }

        results[1] = 1;

        for(int i=2; i<=num; i++){
            //奇数
            if((i & 1) == 1) {
                results[i] = results[i - 1] + 1;
            }
            //偶数
            else{
                results[i] = results[i / 2];
            }
        }

        return results;
    }

    public static void main(String[] args){
        int num = 5;
        int[] results = new CountingBits().countBits(num);
        for(int i=0; i<results.length; i++){
            System.out.print(results[i]);
        }
    }
}
