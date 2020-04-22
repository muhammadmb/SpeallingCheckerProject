package textGeneration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TextGenerator implements TextGeneratorIN{

    private List <String> WordsList;
    
    private String starter;
    
    private Random RNGenerator;

    public TextGenerator(Random Generator) {
        starter = "";
        WordsList = new LinkedList<String>();
        this.RNGenerator = Generator;
    }
    
    
    
    @Override
    public String generateText(int numWords) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void train(String sourceText) {
    
        
        
    }

    @Override
    public void retrain(String sourceText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
    
}
class ListNode{

    private String word;
    private List <String> nextWord; // list of the all next words

    public ListNode(String word) {
        this.word = word;
        this.nextWord = new LinkedList<String>();
    }
    
    public void addNextWord(String nextWord){
    
        this.nextWord.add(nextWord);
    }

    public String getWord() {
        return word;
    }
    
    public String getRandomNextWord (Random generator){
        
        int random = generator.nextInt(nextWord.size()); // generate a random number and get random wod from the next words list
        return nextWord.get(random);
    }
    
}