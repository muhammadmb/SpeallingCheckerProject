package textGeneration;

public interface TextGeneratorIN {
    public String generateText(int numWords); // Generate the text with the specified number of words
    
    public void train(String sourceText); // Train the generator by adding the sourceText
    
    public void retrain(String sourceText); // Retrain the generator from scratch on the source text
}
