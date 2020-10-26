package mile.com.week9;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        if(null == s || s.length() <= 0){
            return s;
        }

        //去除两端的空格
        s = s.trim();

        if(s.length() <= 0){
            return s;
        }

        String[] sArr = s.split(" ");
        StringBuilder str = new StringBuilder();
        for(int i=sArr.length - 1; i>=0; i--){
            if(sArr[i].trim().equals("")) continue;
            str.append(sArr[i].trim()).append(" ");
        }
        return str.substring(0,str.length() - 1).toString();
    }

    public static void main(String[] args){
        String str1 = "the sky is blue";
        System.out.println("原始字符串：" + str1);
        System.out.println("反转之后的字符串：" + new ReverseWordsInAString().reverseWords(str1));
        String str2 = "  hello world!  ";
        System.out.println("原始字符串：" + str2);
        System.out.println("反转之后的字符串：" + new ReverseWordsInAString().reverseWords(str2));

        String str3 = "a good   example";
        System.out.println("原始字符串：" + str3);
        System.out.println("反转之后的字符串：" + new ReverseWordsInAString().reverseWords(str3));


    }
}
