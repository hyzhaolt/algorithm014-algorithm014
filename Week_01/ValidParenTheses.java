package mile.com.week1;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。

 注意空字符串可被认为是有效字符串。
 * Created by zhaofengying on 2020/8/15.
 */
public class ValidParenTheses {
    public boolean isValid(String s){
        if(null == s || s.isEmpty()){
            return true;
        }

        if(s.length() % 2 != 0){
            return false;
        }

        Map<Character,Character> parentThesesMap = new HashMap<>();
        parentThesesMap.put('}','{');
        parentThesesMap.put(']','[');
        parentThesesMap.put(')','(');

        Deque<Character> stack = new LinkedList<Character>();
        for(int i=0; i<s.length(); i++){
            Character currChar = s.charAt(i);
            if(!parentThesesMap.containsKey(currChar)){
                stack.addLast(currChar);
                continue;
            }

            Character topCh = stack.peekLast();
            if(!parentThesesMap.get(currChar).equals(topCh)){
                return false;
            }

            stack.removeLast();
        }

        //如果栈内是空的 则括号是匹配的
        if(stack.isEmpty()){
            return true;
        }

        //栈内还有元素  一定是不匹配的
        return false;
    }

    public static void main(String[] args){
        String parentTheses = "{[()}";
        System.out.println(new ValidParenTheses().isValid(parentTheses));
    }
}
