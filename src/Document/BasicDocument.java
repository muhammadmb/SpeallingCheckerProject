package Document;

public class BasicDocument extends Document{

    public BasicDocument(String text) {
        super(text);
    }

    @Override
    public int getNumWords() {
        if(getText().length() == 0){
            return 0;
        }
        return getText().replaceAll("[0-9]", "").split("\\W+").length;
    }

    @Override
    public int getNumSentences() {
        if(getText().length() == 0){
            return 0;
        }
        return getText().split("[.?!]+").length;
    }

    @Override
    public int getNumSyllables() {
        return countOfSyllables(getText());
    }
    
}
