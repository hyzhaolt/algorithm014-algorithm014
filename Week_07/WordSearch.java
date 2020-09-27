package mile.com.week7;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * java比较好的一个题解
 * https://leetcode-cn.com/problems/word-search-ii/solution/java-zi-dian-shu-hui-su-ji-bai-liao-9969-de-yong-h/
 */
public class WordSearch {
    //坐标变换（四连通）
    private int[] dx = {-1,1,0,0};
    private int[] dy = {0,0,-1,1};
    //最终相同的单词列表 避免重复
    Set<String> results = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        if(null == board || board.length <= 0 || board[0].length <= 0
                || null == words || words.length <= 0){
            return null;
        }

        //1.先用字典中的单词创建一个字典树Trie
        Trie rootTrie = new Trie();
        for(String word : words){
            rootTrie.insert(word);
        }

        //2.再用dfs遍历网格中的单词 每遍历到一个单词的时候就做剪枝（如果当前不是字典树的前缀 则直接暂停）
        //如果是一个完整的单词 则直接添加到返回结果results中
        boolean[][] visited = new boolean[board.length][board[0].length];
        //2.1遍历整个数组
        for(int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                dfsFind(board,visited,i,j,rootTrie);
            }
        }
        System.out.println("共同出现的单词有：" + results);
        return new ArrayList<>(results);
    }

    /**
     * 递归遍历风格中指定一个开始位置上是否存在一个单词在字典树中
     * @param board
     * @param visited
     * @param i
     * @param j
     * @param currTrieNode
     */
    private void dfsFind(char[][] board,boolean[][] visited,int i ,int j,Trie currTrieNode){
        //1.先判断i和j下标是否越界
        if(i < 0 || i >= board.length
                || j < 0 || j >= board[0].length){
            return;
        }
        //从字典树中搜索当前字符
        currTrieNode = currTrieNode.next[board[i][j] - 'a'];
        //无此字符 一定不匹配 直接返回
        if(null == currTrieNode){
            return;
        }

        if(currTrieNode.isEnd){
            results.add(currTrieNode.val);
        }
        visited[i][j] = true;

        //2.从当前字典树中继续dfs搜索
        for(int x=0; x<dx.length; x++){
            dfsFind(board,visited,i + dx[x],j+dy[x],currTrieNode);
        }
    }

    public static void main(String[] args){
        String[] words = {"oath","pea","eat","rain"};
        char[][] board = {{'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        WordSearch wordSearch = new WordSearch();
        wordSearch.findWords(board,words);
    }
}
