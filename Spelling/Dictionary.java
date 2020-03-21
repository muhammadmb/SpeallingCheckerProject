package SpellingCheker.Spelling;

public interface Dictionary {
    
    // dd words to dictionary
    public abstract boolean addWords(String word);
    
    // is this a word accourding to the dictionary
    public abstract boolean isWord(String S);
    
    // return the size of the dictionary
    public abstract int size();
    
    
    
}
