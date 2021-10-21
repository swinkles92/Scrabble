import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseDictionary {
    public static ArrayList parseDictionary(List<String> words) {
        ArrayList<String> newDictionary = new ArrayList<>();
        for(String word : words) {
            if(!newDictionary.contains(word)) {
                word = word.toLowerCase();
                newDictionary.add(word);
            }
        }
        Collections.sort(newDictionary);
        return newDictionary;
    }
}
