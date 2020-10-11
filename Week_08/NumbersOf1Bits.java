package mile.com.week8;

/**
 * https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class NumbersOf1Bits {
    // you need to treat n as an unsigned value

    /**
     * Solution1：使用位运算 每次将n当前最低位置上1置0 直到n中不再有1
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            count ++;
            //n&(n-1)：一次运算之后 会将n中最低位置上的1置为0
            n = n & (n - 1);
        }

        return count;
    }

    /**
     * 每次与1做与操作 判断结果是否为1  是的话 说明最后一位一定是1 再将当前的n右移一位 一直移动32次
     * （因为题目中说明最多是32位）
     * @param n
     * @return
     */
    public int oneBitsCount(int n){
        int count = 0;
        for(int i=0; i<32; i++){
            if((n & 1) == 1 ){
                count ++;
            }

            n = n >> 1;
        }

        return count;
    }

    public static void main(String[] args){
        NumbersOf1Bits numbersOf1Bits = new NumbersOf1Bits();
        int n = -2;
        System.out.println("n:" + n + " has 1 bit count:" + numbersOf1Bits.hammingWeight(n));
        System.out.println("n:" + n + " has 1 bit count:" + numbersOf1Bits.oneBitsCount(n));
    }
}
