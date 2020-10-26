package mile.com.week9;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length <= 0) {
            return "";
        }

        //只有一个单词就返回单词本身
        if(strs.length == 1){
            return strs[0];
        }

        String result = "";
        int row = 0, col = 0;
        while(true){
            while (row < strs.length - 1
                    && strs[row].length() > col
                    && strs[row + 1].length() > col
                    && strs[row].charAt(col) == strs[row + 1].charAt(col)) {
                row++;
            }

            //当前col这一列中所有的行都相等 则将当前列的字母拼接到result中
            if(row >= strs.length - 1 && row > 0){
                result += strs[0].charAt(col);
                //row重新初始化从0开始
                row = 0;
                //col自增1从下一列再重新进行比较
                col ++;
                //继续进入下一次循环
                continue;
            }
            //当前col这一列一定有不相等的字符
            break;
        }

        return result;
    }

    public static void main(String[] args){
//        String[] strs = {"flower","flow","flowht"};
//        String[] strs = {"dog","racecar","car"};
        String[] strs = {""};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println("longest common prefix:" + longestCommonPrefix.longestCommonPrefix(strs));

    }
}
