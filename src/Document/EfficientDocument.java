package Document;

import java.util.List;

public class EfficientDocument extends Document{
    private int numWords;
    private int numSentances;
    private int numSyllables;

    public EfficientDocument(String text) {
        super(text);
        processText();
    }

    private boolean isWord(String tok){
        return !(tok.indexOf("!") >= 0 || tok.indexOf("?") >= 0 || tok.indexOf(".") >= 0) ;
    }
    
    @Override
    public int getNumWords() {
        return numWords;
    }

    @Override
    public int getNumSentences() {
        return numSentances;
    }

    @Override
    public int getNumSyllables() {
        return numSyllables;
    }

    private void processText() {
        List <String> tokens = gettokens("[1?.]+|[a-zA-Z]+");
        for(int i =0 ; i < tokens.size() ; i ++){
            if(isWord(tokens.get(i))){
                numWords++;
            }if(tokens.get(i).matches("[.?!]") || i == tokens.size()-1)
                numSentances++;
        }numSyllables = countOfSyllables(getText());
    }
}
