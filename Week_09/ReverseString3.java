package mile.com.week9;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 */
public class ReverseString3 {
    public String reverseWords(String s) {
        if (null == s || s.length() <= 0) {
            return s;
        }

        s = s.trim();
        String[] strArr = s.split(" ");
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            strBuilder.append(this.reverseStr(strArr[i])).append(" ");
        }

        return strBuilder.toString().substring(0,strBuilder.length() - 1);
    }

    private String reverseStr(String str) {
        char[] strArr = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;
        }

        return String.valueOf(strArr);
    }

    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        System.out.println("原始字符串：" + str);
        String results = new ReverseString3().reverseWords(str);
        System.out.println("反转之后：" + results);
    }
}
