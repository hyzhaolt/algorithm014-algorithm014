package mile.com.week9;

/**
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 示例:
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 */
public class ReverseString2 {
    public String reverseStr(String s, int k) {
        char[] sArr = s.toCharArray();
        //1. k > length
        if(k > s.length()) {
            reverse(sArr,0,s.length() - 1);
        }
        //2.  k < length && 2 * k < length
        else if(k < s.length() && 2 * k > s.length()){
            reverse(sArr,0,k-1);
        }
        //3.  2 * k > length
        else{
            //共分成多少组
            int group = s.length() % (2 * k) == 0 ? s.length() / (2 * k) : s.length() / (2 * k) + 1;
            for(int g = 0; g < group; g ++){
                int start = g * 2 * k;
                int end = start + k - 1;
                if(end >= s.length()) end = s.length() - 1;

                reverse(sArr,start,end);
            }
        }

        return String.valueOf(sArr);
    }

    /**
     * 将指定字符串从指定的开始位置left到结束位置right间的字符全部反转
     * @param s
     * @param left
     * @param right
     */
    public void reverse(char[] s,int left,int right){
        if(null == s || s.length <=0 ){
            return;
        }

        System.out.println("reverse left:" + left + ",right:" + right);
        int i=left,j=right;
        while( i < j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            i++;
            j--;
        }
    }

    public static void main(String[] args){
        String str = "abcdefg";
        System.out.println("原始字符串：" + str);
        System.out.println("反转之后的字符串：" + new ReverseString2().reverseStr(str,2));
    }
}
