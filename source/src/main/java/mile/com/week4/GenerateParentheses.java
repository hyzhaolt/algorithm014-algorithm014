package mile.com.week4;

import mile.com.week1.ValidParenTheses;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成n对有效的括号
 * https://leetcode-cn.com/problems/generate-parentheses/
 * Created by zhaofengying on 2020/8/26.
 */
public class GenerateParentheses {
    private ValidParenTheses validParenTheses = new ValidParenTheses();

    /**
     * 方法一 利用week2中的ValidParenTheses判断一个字符串是否是有效的括号进行判断 只有符合条件的才添加到
     * 最终的返回结果中
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        this.genRecur("", 2 * n, 0, results);
        return results;
    }

    private void genRecur(String s,Integer maxLen,int level,List<String> results){
        if(s.length() == maxLen){
            if(validParenTheses.isValid(s)){
                results.add(s);
            }
            return;
        }

        genRecur(s + "(", maxLen, level + 1,results);
        genRecur(s + ")", maxLen, level + 1, results);

        return;
    }

    /**
     * 优化了的生成n对()
     * @param n
     * @return
     */
    public List<String> generateParenthesisV2(int n){
        List<String> results = new ArrayList<String>();

        this.genRecurV2("",0,0,n,results);
        return results;
    }

    private void genRecurV2(String s,Integer left,int right,int maxLen,List<String> results){
        if(s.length() == 2 * maxLen){
            results.add(s);
            return;
        }

        if(left < maxLen){
            genRecurV2(s + "(", left + 1, right, maxLen, results);
        }

        if(right < left){
            genRecurV2(s + ")",left,right + 1,maxLen,results);
        }

        return;
    }

    public static void main(String[] args){
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> results = generateParentheses.generateParenthesis(2);
        System.out.println("solution1 results:" + results);

        List<String> results2 = generateParentheses.generateParenthesisV2(2);
        System.out.println("solution2 results:" + results2);
    }
}
