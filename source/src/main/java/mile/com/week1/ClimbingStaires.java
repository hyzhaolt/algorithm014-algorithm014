package mile.com.week1;

/**
 * Created by zhaofengying on 2020/8/13.
 */
public class ClimbingStaires {
    /**
     * 1.原题目:有一个n层的楼梯 每次可以走一步 也可以走2步
     * 要爬上一个n层的楼梯 一共有多少种走法
     *
     * 2.审题
     * (1)假设 i=1 就1种走法 记作f(1)=1
     * (2)i=2 2种走法 1步一次 或 2步一次 记作f(2)=2
     * (3)i=3 含义:在走完第二阶之后 再走一步 此时共有 f(2)种走法
     *            或在走完第一阶之后  再走两步 此时共有f(1)走法
     *             即:f(3)=f(2) + f(1)
     *                   =2 + 1 = 3
     * (4)对于任意的n(n>3)时 可得 要爬到n层 要么从n-1层 再跨一步直接到
     * 要么从n-2层再跨2步 所以f(n)= f(n-1) + f(n-2)
     *          f(4)=f(3) + f(2) = 3 + 2 = 5
     *          f(5)=f(4)+f(3) = 5 + 3 = 8
     * @param n
     * @return
     */
    public int climbingStaires(int n){
        if (n  <= 2 ){
            return n;
        }

        int fn1 = 1;
        int fn2 = 2;
        int fn = 0;
        for(int i=3; i<=n; i++){
            fn = fn1 + fn2;
            fn1 = fn2;
            fn2 = fn;
        }

        return fn;
    }

    public static void main(String[] args){
        ClimbingStaires climbingStaires = new ClimbingStaires();

        int n = 5;
        System.out.println("f(" + n + ")=" + climbingStaires.climbingStaires(n));
    }

}
