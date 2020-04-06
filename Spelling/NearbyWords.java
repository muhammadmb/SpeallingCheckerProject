package SpellingCheker.Spelling;

import java.util.ArrayList;
import java.util.List;

public class NearbyWords implements SpellingSuggest{

    private final static int THRESHOLD = 1000;
    
    Dictionary dic;

    public NearbyWords(Dictionary dic) {
        this.dic = dic;
    }
    
    /** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
    
         public List <String> distanceOne(String s,boolean wordsOnly){
         
             List <String> retList = new ArrayList<>();
             
            insertions(s, retList, wordsOnly);
            substitution(s, retList, wordsOnly);
            deletions(s, retList, wordsOnly);
            
             return retList;
         }
    
    private void insertions(String s, List<String> CList, boolean wordsOnly) {
        
        for(int index = 0 ; index < s.length() ; index++ ){
            for(int charCode = (int)'a' ; charCode < (int)'z' ; charCode++){
            
                StringBuffer sb = new StringBuffer(s);
                sb.insert(index,(char)charCode);
                
                if (!CList.contains(sb.toString()) && (!wordsOnly || dic.isWord(sb.toString())) && !s.equals(sb.toString())) {
                    CList.add(sb.toString());
                }
            }
        }
        
    }

    private void substitution(String s, List<String> CList, boolean wordsOnly) {
    
        for(int index = 0 ; index < s.length() ; index++ ){
            for(int charCode = (int)'a' ; charCode < (int)'z' ; charCode++){
            
                StringBuffer sb = new StringBuffer(s);
                sb.setCharAt(index,(char)charCode);
                
                if (!CList.contains(sb.toString()) && (!wordsOnly || dic.isWord(sb.toString())) && !s.equals(sb.toString())) {
                    CList.add(sb.toString());
                }
            }
        }
    }

    private void deletions(String s, List<String> CList, boolean wordsOnly) {
        for(int index = 0 ; index < s.length() ; index++ ){
            for(int charCode = (int)'a' ; charCode < (int)'z' ; charCode++){
            
                StringBuffer sb = new StringBuffer(s);
                sb.deleteCharAt(index);
                
                if (!CList.contains(sb.toString()) && (!wordsOnly || dic.isWord(sb.toString())) && !s.equals(sb.toString())) {
                    CList.add(sb.toString());
                }
            }
        }
    }

    
    @Override
    public List<String> suggestions(String word, int numSuggestions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
