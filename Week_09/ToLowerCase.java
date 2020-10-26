package mile.com.week9;

import java.util.Arrays;

public class ToLowerCase {
    public String toLowerCase(String str) {
        if(null == str || str.length() <= 0){
            return "";
        }

        char[] charArr = str.toCharArray();
        for(int i=0; i<charArr.length; i++){
            Character c = charArr[i];
            if(c >= 'A' && c <= 'Z'){
                charArr[i] = (char) (c + 32);
            }
        }

        return String.valueOf(charArr);
    }

    public static void main(String[] args){
        String str = "ABCDEFHIHYZHAOLT";
        char[] chars = str.toCharArray();
        System.out.println('A' - 'a');
        for(int i=0; i<chars.length; i++){
            Character c = chars[i];
            if(c >= 'A' && c <= 'Z'){
                c = (char) (c + 32);
                chars[i] = c;
            }
        }

        System.out.println(Arrays.toString(chars));

        System.out.println(new ToLowerCase().toLowerCase(str));
    }
}
