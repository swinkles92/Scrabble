import java.util.ArrayList;
import java.util.Collections;

public class ParseDictionary {
    public ParseDictionary() {

    }
    public ArrayList sortDictionary(String[] words) {
        ArrayList<String> wordList = new ArrayList<>();
        for(String word : words) {
            if(!wordList.contains(word)) {
                word = word.toLowerCase();
                wordList.add(word);
            }
        }
        Collections.sort(wordList);
        return wordList;
    }
}
