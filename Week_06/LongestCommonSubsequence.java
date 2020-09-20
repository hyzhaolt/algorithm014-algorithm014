package mile.com.week6;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * dp方程：
 * dp[i][j]=
 * （1）text1.charAt(i) == text2.charAt(j)时
 * dp[i][j]=dp[i-1][j-1] + 1
 * (2)text1.charAt(i) != text2.charAt(j)时
 * dp[i][j]=Max(dp[i-1][j],dp[i][j-1])
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        //任何一个字符串为空串 则返回0
        if (null == text1 || null == text2
                || text1.length() <= 0 || text2.length() <= 0) {
            return 0;
        }

        //两个字符串 用dp[][]二维数组来存放最终结果 注：i=0行和j=0列作为哨兵 默认值为0
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < text1.length() + 1; i++) {
            char currCharOfText1 = text1.charAt(i - 1);
            for (int j = 1; j < text2.length() + 1; j++) {
                char currCharOfText2 = text2.charAt(j - 1);
                boolean lastCharEquals = currCharOfText1 == currCharOfText2;
                //最上面的边界
                if (i == 1) {
                    dp[i][j] = lastCharEquals ? 1 : dp[i][j - 1];
                }
                //最左边的边界
                else if (j == 1) {
                    dp[i][j] = lastCharEquals ? 1 : dp[i - 1][j];
                }
                //边界之内（不含边界）的其它格子
                else {
                    dp[i][j] = lastCharEquals ? dp[i - 1][j - 1] + 1 : Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
//        String text1 = "abcde";
//        String text2 = "ace";
        String text1 = "abazdc";
        String text2 = "bacbad";
        int longestCommonSeqSize = longestCommonSubsequence.longestCommonSubsequence(text1, text2);
        System.out.println("text1:" + text1 + ",text2:" + text2
                + ",longestCommonSeqSize:" + longestCommonSeqSize
        );
    }
}
