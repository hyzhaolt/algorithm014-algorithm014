package mile.com.week9;

/**
 * https://leetcode-cn.com/problems/reverse-string/submissions/
 */
public class ReverseString {
    public void reverseString(char[] s) {
        if(null == s || s.length <= 0){
            return;
        }

        int pivot = s.length / 2,i=0;
        while(i < pivot){
            char temp = s[i];
            s[i] = s[s.length - 1 - i ];
            s[s.length - 1 - i] = temp;
            i++;
        }
    }

    public void reverseString2(char[] s){
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    public static void main(String[] args){
//        char[] s = {'h','e','l','l','o','n'};
        char[] s = {'A',' ','m','a','n',',',' ','a',' ','p','l','a','n',',',' ','a',' ','c','a','n','a','l',':',' ','P','a','n','a','m','a'};
        char[] s2 = {'A',' ','m','a','n',',',' ','a',' ','p','l','a','n',',',' ','a',' ','c','a','n','a','l',':',' ','P','a','n','a','m','a'};
        print(s,"未反转前的原始字符串数组：");
        System.out.println("原始字符串长度：" + s.length);
        new ReverseString().reverseString(s);
        print(s,"反转之后字符串：");
        new ReverseString().reverseString2(s2);
        print(s2,"s2反转之后：");
    }

    private static void print(char[] s,String title){
        for(char c : s){
            System.out.print(c);
        }
        System.out.println("");
    }
}
