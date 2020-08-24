package mile.com.week2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/description/
 * Created by zhaofengying on 2020/8/19.
 */
public class ValidAnagram {
    /**
     * 用哈希表统计每个字母出现的次数 并比较此次数是否一致
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();

        this.statChar(sMap,s);
        this.statChar(tMap,t);

        if(sMap.size() != tMap.size()){
            return false;
        }

        Iterator<Character> iterator = sMap.keySet().iterator();
        while(iterator.hasNext()){
            Character key = iterator.next();
            if(!tMap.containsKey(key)
                    || !tMap.get(key).equals(sMap.get(key))){
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param map
     * @param s
     */
    private void statChar(Map<Character,Integer> map,String s){
        if(null != s && s.length() > 0){
            for(int i=0; i<s.length(); i++){
                Character c = s.charAt(i);
                if(!map.containsKey(c)){
                    map.put(c,0);
                }

                map.put(c,map.get(c) + 1 );
            }
        }
    }

    /**
     * 先对s和t进行排序 然后比较排序之后的字符串是否相等
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramV2(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return Arrays.equals(sArr,tArr);
    }

    public static void main(String[] args){
        String s = "abchello";
        String t = "hellobca";

        ValidAnagram anagram = new ValidAnagram();
        System.out.println(anagram.isAnagramV2(s, t));

        Map<String,Integer> map = new HashMap<>();
        map.put("hyzhaolt",316);
        map.put("liangtao",316);
        System.out.println(map.get("hyzhaolt").equals(map.get("liangtao")) );

        //Integer的几个注意事项
        //1.equals:类型不一致 先转类型 ; 再判断类型是否一致(no:false);再比较值是否一致
        //2.== 1)基本数据类型:以值进行比较 2)不同类型之间 自动拆箱 且向上转型再比值 3)两边都是包装类型:直接比较引用地址
        //参考链接:https://www.cnblogs.com/mrhgw/p/10449391.html
        Integer i0 = 59;
        Integer i1 = Integer.valueOf(59);
        Integer i3 = new Integer(59);

        Integer i4 = 200;
        int i5 = 200;

        System.out.println("i0 == i1?" + (i0 == i1) + ",i0.equals(i1):" + (i0.equals(i1)));
        System.out.println("i0 == i3?" + (i0 == i3) + ",i0.equals(i3):" + (i0.equals(i3)));

        //同类型 == 一边是包装类型 一边是基本类型 自动拆箱 比值;equals:先比较类型 如果有基本类型 自动封装为包装类型
        //如果类型不一致 则直接返回false 否则进行值比较
        System.out.println("i4 == i5?" + (i4 == i5) + ",i4.equals(i5):" + i4.equals(i5));

        //2.== 1)基本数据类型:以值进行比较 2)不同类型之间 自动拆箱 且向上转型再比值 3)两边都是包装类型:直接比较引用地址
        System.out.println("0L == 0?" + (0L == 0));

        Integer i6 = 10;
        long i7 = 10;
        System.out.println("i6 == i7?" + (i6 == i7));

    }
}
