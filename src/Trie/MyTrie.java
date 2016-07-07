package Trie;

/**
 * Created by DixonShen on 2016/7/8.
 * Implement Trie
 */
public class MyTrie {

    private Node root;

    public MyTrie(){
        root = new Node(' ');
    }

    public void insert(String word){
        if (search(word) == true) return ;
    }

    public boolean search(String word){
        return false;
    }

    public void deleteWord(String word){

    }
}
