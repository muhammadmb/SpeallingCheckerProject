package SpellingCheker.Spelling;

import java.util.List;

public class WPTree implements WordPath{

    private WPTreeNode root;
    private NearbyWords NW;

    public WPTree() {
        this.root = null;
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        this.NW = new NearbyWords(d);
}

    public WPTree(NearbyWords NW) {
        this.root = null;
        this.NW = NW;
    }
    
    
    
    
    
    @Override
    public List<String> findPath(String word1, String word2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    class WPTreeNode{}
}
