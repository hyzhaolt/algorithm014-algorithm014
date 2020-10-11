package mile.com.week8;

/**
 * 判断一个数是否是2的n次幂
 * https://leetcode-cn.com/problems/power-of-two/submissions/
 */
public class PowerOfTwo {
    /**
     * 如果是2的n次幂的话 那么这个数一定是一个正整数 且这个整数中1的个数有且仅有一个
     * 而n & (n-1) 是打掉最低位位置上的1 因为就只有一个1 所以打掉之后就是没有任何位置上是1了 这样的数就是2的n次幂
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n >= 1 && (n & (n - 1 )) == 0;
    }
}
