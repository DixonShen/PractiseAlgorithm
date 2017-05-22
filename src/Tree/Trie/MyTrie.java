package Tree.Trie;

import java.util.HashMap;

/**
 * Created by DixonShen on 2016/7/8.
 * Implement Tree.Trie
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
//    public void insert(String word){
//        word = word.toLowerCase();
//        char[] chs = word.toCharArray();
//        for (int i=0; i<chs.length; i++){
//            if (root.subNode(chs[i]) == null){
//                //不存在
//                Node tempNode = new Node(chs[i]);
//                tempNode.count++;
//                root.childList.add(tempNode);
//            } else {
//                //存在
//                root.subNode(chs[i]).count++;
//            }
//
////            System.out.println(root.content);
//
//            if (i == chs.length-1){
//                root.subNode(chs[i]).isEnd = true;
//                root.subNode(chs[i]).dumpli_num++;
//            }
//
//
//            root = root.subNode(chs[i]);
//        }
//    }

    public void insert(String word){
        word = word.toLowerCase();
        Node current = root;
        for (int i=0; i<word.length(); i++){
            Node child = current.subNode(word.charAt(i));
            if (child != null) {
                current = child;
            }
            else {
                current.childList.add(new Node(word.charAt(i)));
                current = current.subNode(word.charAt(i));
            }
            current.count++;
        }

        current.isEnd = true;
        current.dumpli_num++;
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

//    /**
//     * 删除指定单词
//     * @param word
//     */
//    public void deleteWord(String word){
//        if (search(word) == false) return;
//
//        Node current = root;
//        for (char c : word.toCharArray()){
//            Node child = current.subNode(c);
//            if (child.count == 1){
//                current.childList.remove(child);
//                return;
//            }
//            else {
//                child.count--;
//                current = child;
//            }
//        }
//
//        current.isEnd = false;
//
//    }

    /**
     * 遍历Trie树，查找所有的words以及出现次数
     * @return
     */
    public HashMap<String,Integer> getAllWords(){
        return preTraversal(this.root,"");
    }

    /**
     * 前序遍历
     * @param root  子树根节点
     * @param prefixs  查询到该节点前所遍历过的前缀
     * @return
     */
    private HashMap<String, Integer> preTraversal(Node root, String prefixs){
        HashMap<String,Integer> map = new HashMap<String,Integer>();

        if (root != null){
            if (root.isEnd == true){
                //当前即为一个单词
                map.put(prefixs, root.dumpli_num);
            }


            for (Node eachChild : root.childList){
                String tempStr = prefixs + eachChild.content;
                map.putAll(preTraversal(eachChild,tempStr));   //递归调用前序遍历
            }
        }

        return map;
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身，类似输入法联想功能
     * @param prefix
     * @return 字串以及出现次数，如不存在则返回null
     */
    public HashMap<String, Integer> getWordsForPrefix(String prefix){
        return getWordsForPrefix(root,prefix);
    }

    /**
     * 得到以该字串为前缀的字串集以及出现次数
     * @param root
     * @param prefix
     * @return
     */
    private HashMap<String, Integer> getWordsForPrefix(Node root, String prefix){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (char ch : prefix.toCharArray()){
            if (root.subNode(ch) == null)
                return null;
            else
                root = root.subNode(ch);
        }

        return preTraversal(root,prefix);
    }


    public static void main(String[] args) {

        MyTrie trie = new MyTrie();
        trie.insert("I");
        trie.insert("Love");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("xiaoliang");
        trie.insert("xiaoliang");
        trie.insert("man");
        trie.insert("handsome");
        trie.insert("love");
        trie.insert("chinaha");
        trie.insert("her");
        trie.insert("know");


        HashMap<String, Integer> map = trie.getAllWords();
        for (String key : map.keySet()){
            System.out.println(key + "出现：" + map.get(key) + "次");
        }

        map = trie.getWordsForPrefix("chin");
        System.out.println("\n\n包含chin（包括本身）前缀的单词及出现次数：");
        for(String key : map.keySet()){
            System.out.println(key+" 出现: "+ map.get(key)+"次");
        }

        if(trie.search("xiaoming")==false){
            System.out.println("\n\n字典树中不存在：xiaoming ");
        }

    }
}
