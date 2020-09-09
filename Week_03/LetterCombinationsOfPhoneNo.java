package mile.com.week3;

import java.util.HashMap;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * Created by zhaofengying on 2020/9/7.
 */
public class LetterCombinationsOfPhoneNo {
    /**
     * 电话本哈希表
     **/
    private Map<Character, String> phoneMap = new HashMap<>();
    /**
     * 最终要返回的结果
     **/
    List<String> results = new ArrayList<>();

    public List<String> letterCombinations(String digits) {

        if (null == digits || digits.length() <= 0) {
            return results;
        }

        //电话本哈希初始化
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        this.dfsCombination(digits, 0, new StringBuffer(""));
        return results;
    }

    /**
     * 深度优先
     *
     * @param digits
     * @param index
     * @param combination
     */
    private void dfsCombination(String digits, int index, StringBuffer combination) {
        //terminator:digit有几位 就代表有几层 即:已经到叶子节点了  不会再继续向下扩散了
        if (index == digits.length()) {
            this.results.add(combination.toString());
            return;
        }

        //current logic pro
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);

        //drill down
        for (int i=0; i<letters.length(); i++ ){
            combination.append(letters.charAt(i));
            this.dfsCombination(digits,index + 1,combination);
            //清空当前层添加到末尾的字母 便于与下一个i位置上的char组合成新的单词
            combination.deleteCharAt(index);
        }
    }

    public static void main(String[] args){
        LetterCombinationsOfPhoneNo combinationsOfPhoneNo = new LetterCombinationsOfPhoneNo();
        String digits = "23";
        List<String> results = combinationsOfPhoneNo.letterCombinations(digits);

        System.out.println(results);
    }

}
