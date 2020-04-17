package Document;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {
    private String text;

    protected Document(String text) {
        this.text = text;
    }
    
    //return list of the tokens that match the regex pattern from the document
    protected List <String> gettokens(String pattern){
    
        ArrayList<String> tokens = new ArrayList<>();
        Pattern pat = Pattern.compile(pattern);
        Matcher m = pat.matcher(text);
        while(m.find()){
            tokens.add(m.group());
        }
        return tokens;
    }
    
    protected static int countOfSyllables(String word){
    
        int numSyllables = 0;
        boolean newSyllable = true;
        String vowels = "aeiouy";
        char [] cArray = word.toCharArray();
        
        for (int i = 0; i < cArray.length; i++) {
            
            if(i == cArray.length -1 && Character.toLowerCase(cArray[i]) == 'e'
                    && newSyllable && numSyllables > 0){
                
                numSyllables --;
            }
            
            if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0){
            
                newSyllable = false;
                numSyllables ++;
            }else if(vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
                newSyllable = true;
            }
        }
        return numSyllables;
    }
    
        /** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}

}
     