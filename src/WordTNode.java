import java.util.HashMap;

public class WordTNode {
    private char c;
    private HashMap<Character, WordTNode> children = new HashMap<Character, WordTNode>();
    private boolean isCompleteWord;

    public WordTNode() {}
    public WordTNode(char c) {
        this.c = c;
    }
    public HashMap<Character, WordTNode> getChildren() {
        return children;
    }
    public boolean isCompleteWord() {
        return isCompleteWord;
    }
    public void flagCompleteWord(boolean isCompleteWord) {
        this.isCompleteWord = isCompleteWord;
    }
}
