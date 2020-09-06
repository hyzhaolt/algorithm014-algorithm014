package mile.com.week3;

/**
 * https://leetcode-cn.com/problems/powx-n/
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * Created by zhaofengying on 2020/8/31.
 */
public class Powxn {
    public double myPow(double x, int n) {
        int N = n;
        return n > 0 ? multiply(x,N) : 1 / multiply(x,-N);
    }

    private double multiply(double x,int n){
        if(0 == n){
            return 1.00;
        }

        double y = multiply(x, n / 2);

        return n % 2 == 0 ? y * y : y * y * x;
    }


    public static void main(String[] args){
        Powxn powxn = new Powxn();
        System.out.println(powxn.myPow(2,10));
    }
}
