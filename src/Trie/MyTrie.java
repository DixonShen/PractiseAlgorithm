package Trie;

import java.util.HashMap;

/**
 * Created by DixonShen on 2016/7/8.
 * Implement Trie
 * 对于insert, 如果被插入的String长度是 k,
 * 每对一个字符进行查询，我们最多在child linkedlist里面查询26次（最多26个字母），
 * 所以，复杂度为O(26*k) = O(k). 对于 search, 复杂度是一样的。
 */
public class MyTrie {

    private Node root;

    public MyTrie(){
        root = new Node(' ');
    }

    /**
     * 插入字符串word
     * @param word
     */
    public void insert(String word){
        if (search(word) == true) return ;

        Node current = root;
        for (int i=0; i<word.length(); i++){
            Node child = current.subNode(word.charAt(i));
            if (child != null)
                current = child;
            else {
                current.childList.add(new Node(word.charAt(i)));
                current = current.subNode(word.charAt(i));
            }
            current.count++;
        }
        //set isEnd to indicate end of the word
        current.isEnd = true;
    }

    /**
     * 查询字符串word是否在trie树中
     * @param word
     * @return
     */
    public boolean search(String word){
        Node current = root;

        for (int i = 0; i<word.length(); i++){
            if (current.subNode(word.charAt(i)) == null)
                return false;
            else
                current = current.subNode(word.charAt(i));
        }
        /*
        *This means that a string exists, but make sure it's
        * a word by checking its 'isEnd' flag
         */
        if (current.isEnd == true)
            return true;
        else
            return false;
    }

    /**
     * 删除指定单词
     * @param word
     */
    public void deleteWord(String word){
        if (search(word) == false) return;

        Node current = root;
        for (char c : word.toCharArray()){
            Node child = current.subNode(c);
            if (child.count == 1){
                current.childList.remove(child);
                return;
            }
            else {
                child.count--;
                current = child;
            }
        }

        current.isEnd = false;

    }

    public HashMap<String,Integer> getAllWords(){
        return preTraversal(this.root,"");
    }

    private HashMap<String, Integer> preTraversal(Node root, String prefixs){
        HashMap<String,Integer> map = new HashMap<String,Integer>();

        if (root != null){
            if (root.isEnd == true){
                map.put(prefixs, root.dumpli_num);
            }

            for (int i=0, length=root.childList.size(); i<length; i++){
                if (root.childList[i] != null)
            }
        }
    }

    public static void main(String[] args) {
        MyTrie trie = new MyTrie();
        trie.insert("ball");
        trie.insert("balls");
        trie.insert("sense");

        System.out.println(trie.search("balls"));
        System.out.println(trie.search("ba"));
        trie.deleteWord("balls");
        System.out.println(trie.search("balls"));
        System.out.println(trie.search("ball"));
    }
}
