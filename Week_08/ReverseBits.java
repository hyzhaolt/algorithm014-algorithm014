package mile.com.week8;

public class ReverseBits {
    /**
     * 将n从右至左的每一位累加到m上
     * 每次取n的末位 添加到当前m上 然后m左移 n右移
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int m = 0;
        for(int i=0; i<32; i++){
            m = m << 1;
            m += n & 1 ;
            n = n >> 1;
        }

        return m;
    }


}
