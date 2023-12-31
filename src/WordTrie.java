import java.util.ArrayList;
import java.util.HashMap;

public class WordTrie {
    public WordTNode root;

    public WordTrie() {
        root = new WordTNode();
    }
    public void add(String word) {
        HashMap<Character, WordTNode> children = root.getChildren();
        for(int i = 0; i < word.length(); i ++) {
            WordTNode letterNode;
            char c = word.charAt(i);
            if(children.containsKey(c)) {
                letterNode = children.get(c);
            }
            else {
                letterNode = new WordTNode(c);
                children.put(c, letterNode);
            }
            children = letterNode.getChildren();
            if(i == word.length() - 1) {
                letterNode.flagCompleteWord(true);
            }
        }
    }
    public boolean contains(String word) {
        HashMap<Character, WordTNode> children = root.getChildren();
        WordTNode letterNode = null;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(children.containsKey(c)) {
                letterNode = children.get(c);
                children = letterNode.getChildren();
            }
            else {
                letterNode = null;
                break;
            }
        }
        if(letterNode != null && letterNode.isCompleteWord()) {
            return true;
        }
        else return false;
    }
    public ArrayList<String> findWords(char c) {
        ArrayList<String> possWords = new ArrayList<>();
        HashMap<Character, WordTNode> children = root.getChildren();
        for(char a : children.keySet()) {

        }
        return possWords;
    }
}
