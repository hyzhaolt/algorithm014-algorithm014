package mile.com.week7;

import java.util.Timer;

/**
 *Trie树：一个26叉树
 */
public class Trie {
    //mark the end of a word in dictionary
    public boolean isEnd ;
    //next[0]~next[25]：a~z
    public Trie[] next;
    //走到字典树的最后所形成的一个完整的单词
    public String val;

    /** Initialize your data structure here. */
    public Trie() {
        this.isEnd = false;
        next = new Trie[26];
        val = "";
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(null == word || word.length() <= 0){
            return;
        }

        Trie currTrie = this;
        char[] wodrs = word.toCharArray();
        for(int i=0; i<wodrs.length; i++){
            int n = wodrs[i] - 'a';
            //每插入一个新的字符 都会生成一个新的Trie节点 并且其父节点指向该新节点 父节点放在char - 'a'对应的位置上
            if(currTrie.next[n] == null) currTrie.next[n] = new Trie();
            currTrie = currTrie.next[n];
        }

        currTrie.isEnd = true;
        currTrie.val = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie trie = this.searchPrefix(word);
        return null != trie && trie.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie trie = this.searchPrefix(prefix);
        return null != trie;
    }

    private Trie searchPrefix(String word){
        Trie node = this;
        if(null == word || word.length() <= 0){
            return null;
        }

        char[] words = word.toCharArray();
        for(int i=0 ; i<word.length(); i++){
            node = node.next[words[i] - 'a'];
            if(null == node){
                return null;
            }
        }
        return node;
    }

    public static void main(String[] args){
        Trie root = new Trie();
        root.insert("hello");
        root.insert("hi");
        root.insert("she");
        root.insert("james");
        root.insert("jane");

        System.out.println(root.search("hellen"));
        System.out.println(root.search("hello"));
        System.out.println(root.startsWith("hel"));
        System.out.println(root.search("jane"));
        System.out.println(root.startsWith("ja"));
    }
}
