package mile.com.week9;

import java.util.HashSet;

/**
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        if(null == J || null == S || J.length() <= 0 || S.length() <= 0){
            return 0;
        }

        HashSet<Character> keys = new HashSet<>();
        for(int i=0; i<J.length(); i++){
            keys.add(J.charAt(i));
        }

        int jewelsCnt = 0;
        for(int j = 0 ; j < S.length(); j++){
            if(keys.contains(S.charAt(j))) jewelsCnt ++;
        }

        return jewelsCnt;
    }
}
