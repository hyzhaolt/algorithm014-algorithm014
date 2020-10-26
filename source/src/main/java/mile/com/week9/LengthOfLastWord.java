package mile.com.week9;

public class LengthOfLastWord {
    /**
     * 第一遍
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (null == s || s.length() <= 0) {
            return 0;
        }

        String[] words = s.split(" ");
        return null == words || words.length <= 0 ? 0 : words[words.length - 1].length();
    }

    /**
     * 第二遍
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord2(String s) {
        if (null == s || s.length() <= 0 || s.trim().length() <= 0) {
            return 0;
        }

        s = s.trim();
        int i = s.length() - 1;
        char tempChar = s.charAt(s.length() - 1);
        while (i >= 0 && tempChar != ' ') {
            tempChar = s.charAt(i--);
        }

        return i < 0 ? s.length() : s.length() - (i + 2);
    }


    public static void main(String[] args) {
        String str = "hello world";
        System.out.println(str + " 's last word length:" + new LengthOfLastWord().lengthOfLastWord(str));
        System.out.println(str + " 's last word length:" + new LengthOfLastWord().lengthOfLastWord2(str));
    }
}
