package SpellingCheker.Spelling;

import java.util.TreeSet;
public class DictionaryBST implements Dictionary{
    
    private TreeSet<String> dict ;

    public DictionaryBST() {
        this.dict = new TreeSet<>();
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
