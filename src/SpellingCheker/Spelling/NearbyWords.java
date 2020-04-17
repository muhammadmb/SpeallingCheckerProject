package SpellingCheker.Spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
    
        List <String> queue = new LinkedList<>(); //String to explor
        HashSet <String> visited = new HashSet <String>(); //to avoid exploring the same string multiple times
        List <String> retList = new LinkedList<>(); //to return
        
        queue.add(word);
        visited.add(word);
        
        while(!queue.isEmpty() && queue.size() != numSuggestions){
            for(String s : distanceOne(queue.remove(0), true)){
                if(!visited.contains(s)){
                    visited.add(s);
                    queue.add(s);
                        if(dic.isWord(s)){
                            retList.add(s);
                        }
                }
            }
        }
        
        
        return retList;
    }
    
    public static void main(String[] args) {
	   // basic testing code to get started
	   String word = "love";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "love";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
	   
	   
	// basic testing code to get started
	   
   }

    
    
}

    
