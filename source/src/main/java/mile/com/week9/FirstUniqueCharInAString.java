package mile.com.week9;

import java.util.HashMap;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 */
public class FirstUniqueCharInAString {
    public int firstUniqChar(String s) {
        //字符串本身不符合规则
        if(null == s || s.length() <= 0){
            return -1;
        }

        HashMap<Character,Integer> charsCntMap = new HashMap<>();
        //统计字符串s中每个字符出现的频次
        for(int i = 0; i < s.length(); i++){
            Character tmpChar = s.charAt(i);
            if(!charsCntMap.containsKey(tmpChar)){
                charsCntMap.put(tmpChar,0);
            }
            charsCntMap.put(tmpChar,charsCntMap.get(tmpChar) + 1);
        }

        //再查找字符串第一个频次为1的字符所在的索引
        for(int j=0; j<s.length(); j++){
            if(charsCntMap.get(s.charAt(j)).equals(1)) return j;
        }

        //字符串s中不存在只出现一次的字符
        return -1;
    }
}
