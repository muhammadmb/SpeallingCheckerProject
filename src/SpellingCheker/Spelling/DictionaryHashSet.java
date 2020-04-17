package SpellingCheker.Spelling;

import java.util.HashSet;

public class DictionaryHashSet implements Dictionary{

    
    private HashSet <String> words;

    public DictionaryHashSet() {
    words = new HashSet<>();
    }
    
    
    
    @Override
    public boolean addWords(String word) {
    
        return words.add(word);
    }

    @Override
    public boolean isWord(String S) {
    
        return words.contains(S);
    }

    @Override
    public int size() {
    
        return words.size();
    }
    
}
