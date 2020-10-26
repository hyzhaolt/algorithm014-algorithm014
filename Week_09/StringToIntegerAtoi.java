package mile.com.week9;

public class StringToIntegerAtoi {
    public int myAtoi(String s) {
        if(null == s || s.length() <= 0){
            return 0;
        }
        //自动去除双端的空格
        s = s.trim();
        //排除极端情况"      "类似这种一串连续的空格
        if(s.length() <= 0){
            return 0;
        }
        int sign = 1;
        char firstChar = s.charAt(0);
        //如果字符串带有符号 先给sign符号位赋值 再把符号位去掉 用于后面统一的数字转换
        if(firstChar == '-' || firstChar == '+'){
            sign = firstChar == '-' ? -1 : 1;
            s = s.substring(1);
        }

        int result = 0;
        for(int i=0; i<s.length(); i++){
            //只要遇到第一个非法数字 停止转换 直接返回
            if(s.charAt(i) < '0' || s.charAt(i) > '9') {
                //如果第一个有效字符为非数字 则无法转换
                return result;
            }

            //如果当前结果已经超过最大范围 则返回 INT_MAX (231 − 1) 或 INT_MIN (−231)
            if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (s.charAt(i) - '0') > Integer.MAX_VALUE % 10)){
                return Integer.MAX_VALUE;
            }

            if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && (s.charAt(i) - '0') > - (Integer.MIN_VALUE % 10))){
                return Integer.MIN_VALUE;
            }

            //正常情况：result = result * 10 + 当前位置上的数字（当作个位）
            result = result * 10 + sign * Integer.parseInt("" + s.charAt(i));
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println("Max:" + Integer.MAX_VALUE + ",Min:" + Integer.MIN_VALUE);
        StringToIntegerAtoi atoi = new StringToIntegerAtoi();
//        String str = "4193 with words";
//        String str = "words and 987";
//        String str = "2147483646";
//        String str = "2147483647";
        String str = "2147483659";
        System.out.println("origin str:" + str + ",integer:" + atoi.myAtoi(str));
    }
}
