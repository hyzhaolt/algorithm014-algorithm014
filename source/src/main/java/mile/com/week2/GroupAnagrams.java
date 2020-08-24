package mile.com.week2;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * https://leetcode-cn.com/problems/group-anagrams/
 * Created by zhaofengying on 2020/8/22.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<List<String>>();

        if(null == strs || strs.length <= 0){
            return results;
        }

        Map<String,List<String>> keyMap = new HashMap<>();
        for(String str : strs){
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String key = "";
            for(int i=0; i<strArr.length ; i++){
                key += strArr[i];
            }

            if(!keyMap.containsKey(key)){
                List<String> list = new ArrayList<>();
                keyMap.put(key,list);
            }

            keyMap.get(key).add(str);
        }

        Iterator<String> it = keyMap.keySet().iterator();
        while(it.hasNext()){
            results.add(keyMap.get(it.next()));
        }

        return results;
    }

    /**
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsV2(String[] strs) {
        if(null == strs || strs.length <= 0){
            return new ArrayList<>();
        }

        Map<String,List<String>> keyMap = new HashMap<>();
        for(String str : strs){
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            //将字符数组转换为字符串
            String key = String.valueOf(strArr);

            if(!keyMap.containsKey(key)){
                List<String> list = new ArrayList<>();
                keyMap.put(key,list);
            }

            keyMap.get(key).add(str);
        }

        //将map中的value全部转换为数组
        return new ArrayList<>(keyMap.values());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        GroupAnagrams groupAnagrams = new GroupAnagrams();

        String[] strArr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams.groupAnagrams(strArr));
    }
}
