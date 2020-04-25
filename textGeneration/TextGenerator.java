package textGeneration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TextGenerator implements TextGeneratorIN{

    private List <ListNode> WordsList;
    
    private String starter;
    
    private Random RNGenerator;

    public TextGenerator(Random Generator) {
        starter = "";
        WordsList = new LinkedList<ListNode>();
        this.RNGenerator = Generator;
    }
    
    @Override
    public void train(String sourceText) {
    
        String [] words = sourceText.split("\\s+");
        starter = words[0];
        ListNode prevWord = new ListNode(starter);
        
        for (int i = 1; i < words.length; i++) {
            
            boolean isIn = false;
            String word = words[i];
                for(ListNode n : WordsList){
                    if(n.getWord().equals(prevWord.getWord())){
                        n.addNextWord(word);
                        isIn = true; 
                        break;}
                    }
                    if(!isIn){
                        WordsList.add(prevWord);
                        prevWord.addNextWord(word);
                    }
                    prevWord = new ListNode(word);
                    
                    if(i == words.length-1){
                        if(WordsList.contains(prevWord)){
                            WordsList.get(WordsList.size()-1).addNextWord(prevWord.getWord());return;
                        }WordsList.add(prevWord);
                        prevWord.addNextWord(starter);
                    }
                }
            }
    @Override
    public String generateText(int numWords) {
        if(WordsList.size() == 0 || numWords == 0){return "";}
        
        String currWord = starter;
        String output = currWord;
        
        int i = 1;
        while(i<numWords){
        
            for (ListNode n : WordsList) {
                if(n.getWord().equals(currWord)){
                    currWord = n.getRandomNextWord(RNGenerator);
                    output = output + " " + currWord;
                    break;
                }
            }i++;
        }return output;
    }

    @Override
    public void retrain(String sourceText) {
        WordsList = new LinkedList<>();
        starter="";
        train(sourceText);
    }
    @Override
    public String toString()
    {
            String toReturn = "";
            for (ListNode n : WordsList)
            {
                    toReturn += n.toString();
            }
            return toReturn;
    }
    public static void main(String[] args)
	{
            TextGenerator gen = new TextGenerator(new Random(42));
            gen.train("test 1");
            System.out.println(gen.generateText(20));
	}
}
class ListNode{

    private String word;
    private List <String> nextWord; // list of the all next words

    ListNode(String word) {
        this.word = word;
        nextWord = new LinkedList<String>();
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
    public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWord) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
}