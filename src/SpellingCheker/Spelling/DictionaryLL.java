package SpellingCheker.Spelling;

import java.util.LinkedList;

public class DictionaryLL implements Dictionary{

    private LinkedList dict ;

    public DictionaryLL() {
        this.dict = new LinkedList<>();
    }
    
    
    
    @Override
    public boolean addWords(String word) {
        
        word = word.toLowerCase();
        if (!dict.contains(word))
            return dict.add(word);
        return false;
        
        
    }

    @Override
    public boolean isWord(String S) {
    
        return dict.contains(S.toLowerCase());
    }

    @Override
    public int size() {
    
        return dict.size();
    }
    
    
    
}
